package com.utils.library.model;

import com.utils.library.ApiService;
import com.utils.library.bean.Translation;
import com.utils.library.network.ResponseSubscriber;
import com.utils.library.network.RetrofitUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class Des:
 * Created by bjh on 2018/3/27.
 */
public class MainMvpModel  {
    public void request(String a, String f, String t,String w, ResponseSubscriber<Translation> callback){
        ApiService apiService = RetrofitUtils.getInstance("http://fy.iciba.com/").setCreate(ApiService.class);
        apiService.getCall(a,f,t,w).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }
}
