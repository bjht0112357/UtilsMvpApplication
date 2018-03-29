package com.utils.library.network;

import com.utils.library.log.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
//    private static File cacheDirectory = new File(MyApplication.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
//    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);


    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
//                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }
}
