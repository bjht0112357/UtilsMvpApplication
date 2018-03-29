package com.utils.library;

import com.utils.library.bean.Translation;
import com.utils.library.bean.User;
import com.utils.library.model.Entity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public interface ApiService {
    //POST请求
    @FormUrlEncoded
    @POST("bjws/app.user/login")
    Observable<User> getUserInfo(@Field("tel") String tel, @Field("password") String pass);
    //GET请求
    @GET("ajax.php")
    Observable<Translation> getCall(@Query("a") String a, @Query("f") String f, @Query("t") String t, @Query("w") String w);
    @GET("ajax.php")
    Observable<Entity> getCall();
}
