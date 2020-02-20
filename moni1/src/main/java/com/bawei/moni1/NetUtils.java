package com.bawei.moni1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class NetUtils {

    private static NetUtils netUtils = new  NetUtils();

    private NetUtils() {

    }

    public static  NetUtils getInstance(){
        return netUtils;
    }

    public boolean isNetWorkActive(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }

    public boolean isWiFi(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null&&info.getType() == ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }

    public boolean isMobile(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null&&info.getType() == ConnectivityManager.TYPE_MOBILE){
            return true;
        }
        return false;
    }

    public  void  getPic(final String picUrl, final ImageView iv){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(picUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(8000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = conn.getInputStream();
                        final Bitmap bitmap = io2Bitmap(inputStream);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(bitmap);
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("xxx","请求失败");
                            }
                        });
                    }

                }catch (final Exception e){
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("xxxx",e.toString());
                        }
                    });
                }
            }
        }).start();
    }

    public void  getJson(final String jsonUrl,final ICallBack iCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(jsonUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(8000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = conn.getInputStream();
                        final  String json = io2String(inputStream);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (iCallBack!=null){
                                    iCallBack.onSucess(json);
                                }
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (iCallBack!=null){
                                    iCallBack.onError("请求失败");
                                }
                            }
                        });
                    }
                }catch (final Exception e){
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (iCallBack!=null){
                                iCallBack.onError(e.toString());
                            }
                        }
                    });
                }
            }
        }).start();
    }

    private Bitmap io2Bitmap(InputStream inputStream) throws IOException {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return bitmap;
    }


    private String io2String (InputStream inputStream) throws IOException {
        int len = 0;
        byte[] buffer = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(buffer))!=-1){
            String s = new String(buffer,0,len);
            sb.append(s);
        }
        inputStream.close();
        String json = sb.toString();
        return json;
    }
    //5
    private Handler handler = new Handler();
    //6
    public interface ICallBack{
        void onSucess(String json);
        void onError(String msg);
    }
}
