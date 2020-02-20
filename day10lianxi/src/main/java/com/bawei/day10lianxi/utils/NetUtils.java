package com.bawei.day10lianxi.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtils {

    private static NetUtils netUtils = new NetUtils();

    //2，私有的构造方法
    private NetUtils() {

    }

    //3,提供公共的静态的回返实例的方法
    public static NetUtils getInstance() {
        return netUtils;
    }


    //二，网络状态判断

    public boolean isNetWorkActive(Context context) {
        //获取网络连接管理器
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //从管理器获取网络信息
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }
        return false;
    }


    //三，网络状态判断 是否是wifi
    public boolean isWifi(Context context) {
        //获取网络连接管理器
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //从管理器获取网络信息
        NetworkInfo info = cm.getActiveNetworkInfo();
        //是否是wifi
        if (info!=null&&info.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    //四，网络状态判断 是否是手机流量
    public boolean isMobile(Context context) {
        //获取网络连接管理器
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //从管理器获取网络信息
        NetworkInfo info = cm.getActiveNetworkInfo();
        //是否是手机流量
        if (info!=null&&info.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    //五，网络网络图片 Thread+Handler 第一个参数图片地址，第二个参数 图片控件

    public void getPic(final String picUrl, final ImageView iv) {
        //1，开线程Thread做网络请求耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建URL 把图片地址包装成网络地址
                    URL url = new URL(picUrl);
                    //获取网络请求对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置请求方式
                    conn.setRequestMethod("GET");
                    //设置连接超时
                    conn.setConnectTimeout(5000);
                    //设置读取超时
                    conn.setReadTimeout(8000);
                    //获取响应码
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        //从连接中获取输入流
                        InputStream inputStream = conn.getInputStream();

                        //下面 二者作用一样的
                        // a,流转换成Bitmap
                        // Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        //b,封装字节流转换成Bitmap的方法
                        final Bitmap bitmap = io2Bitmap(inputStream);

                        //2,使用handler的post方式把数据发送到主线程 更新UI
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //已经回调到主线程，更新UI 显示图片
                                iv.setImageBitmap(bitmap);

                            }
                        });


                    } else {
                        //2,使用handler的post方式把数据发送到主线程 更新UI
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //已经回调到主线程，更新UI 显示图片
                                Log.i("xxx", "请求失败");

                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    //2,使用handler的post方式把数据发送到主线程 处理异常
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //已经回调到主线程，更新UI 显示图片
                            Log.i("xxx", e.toString());

                        }
                    });
                }


            }
        }).start();


    }

    //b,封装字节流转换成Bitmap的方法
    private Bitmap io2Bitmap(InputStream inputStream) throws IOException {

        //流转换成Bitmap
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        //返回bitmap
        return bitmap;


    }


    //五，获取网络json数据 Thread+Handler 第一个参数接口地址，第二个参数 接口

    public void getJson(final String jsonUrl, final ICallBack iCallBack) {
        //1，开线程Thread做网络请求耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建URL 把接口地址包装成网络地址
                    URL url = new URL(jsonUrl);
                    //获取网络请求对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置请求方式
                    conn.setRequestMethod("GET");
                    //设置连接超时
                    conn.setConnectTimeout(5000);
                    //设置读取超时
                    conn.setReadTimeout(8000);
                    //获取响应码
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        //从连接中获取输入流
                        InputStream inputStream = conn.getInputStream();

                        //封装字节流转换成字符串的方法io2String()

                        final String json = io2String(inputStream);


                        //2,使用handler的post方式把数据发送到主线程 更新UI
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //已经回调到主线程
                                if (iCallBack != null) {
                                    //通过接口回调，把主线程的json数据回调给调用者
                                    iCallBack.onSucess(json);
                                }


                            }
                        });


                    } else {
                        //2,使用handler的post方式把数据发送到主线程 更新UI
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //已经回调到主线程
                                // Log.i("xxx", "请求失败");
                                if (iCallBack != null) {
                                    //通过接口回调，把主线程的数据回调给调用者
                                    iCallBack.onError("请求失败");
                                }

                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    //2,使用handler的post方式把数据发送到主线程 处理异常
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //已经回调到主线程
                            if (iCallBack != null) {
                                //通过接口回调，把主线程的数据回调给调用者
                                iCallBack.onError(e.toString());
                            }


                        }
                    });
                }


            }
        }).start();


    }

    //封装字节流转换成字符串的方法
    private String io2String(InputStream inputStream) throws IOException {

        int len = 0;
        byte[] buffer = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(buffer)) != -1) {

            String s = new String(buffer, 0, len);
            sb.append(s);

        }
        inputStream.close();
        String json = sb.toString();
        return json;

    }


    //五，2-创建Handler
    private Handler handler = new Handler();

    //六，定义接口
    public interface ICallBack {
        void onSucess(String json);

        void onError(String msg);
    }

}
