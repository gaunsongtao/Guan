package com.bawei.guanshongtao20200218.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {

    private static final String TAG = "NetUtil";
    private static NetUtil instance;
    private NetUtil(){}

    public static NetUtil getInstance() {
        if (instance == null){
            instance = new NetUtil();
        }
        return instance;
    }

    public void doGet(final String path,final CallBack callBack){
        boolean netStatus = newStatus();
        if (!netStatus) {
            Toast.makeText(App.context, "网络异常", Toast.LENGTH_SHORT).show();
            return;
        }
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(path);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        int len = -1;
                        byte[] bytes = new byte[1024];
                        StringBuilder builder = new StringBuilder();
                        while ((len = inputStream.read(bytes))!=-1){
                            builder.append(new String(bytes,0,len));
                        }
                        String json = builder.toString();
                        Log.d(TAG,json);
                        inputStream.close();;
                        return json;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (!TextUtils.isEmpty(s)){
                    callBack.onSuccess(s);
                }else {
                    callBack.onFeature("请求失败");
                }

            }
        }.execute(path);

    }
    public void doPoto(final String path,final ImageView imageView){
        boolean netStatus = newStatus();
        if (!netStatus) {
            Toast.makeText(App.context, "网络异常", Toast.LENGTH_SHORT).show();
            return;
        }
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    URL url = new URL(path);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();;
                        return bitmap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }.execute(path);

    }
    private boolean newStatus(){
        ConnectivityManager systemService = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = systemService.getActiveNetworkInfo();
        if (activeNetwork != null) {
            boolean available = activeNetwork.isAvailable();
            return available;
        }
        return false;
    }

    public interface CallBack{
        void onSuccess(String json);
        void onFeature(String msg);
    }

}
