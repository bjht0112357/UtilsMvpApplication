package com.utils.library.network;

import rx.Subscriber;

/**
 * Class Des:自定义Subscriber捕获异常及返回方法封装{@link #onError}，{@link #onResponse}
 * Created by bjh on 2018/3/19.
 */

public abstract class ResponseSubscriber<T> extends Subscriber<T> {

    public ResponseSubscriber() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof Exception){
            //访问获得对应的Exception
            onError(ExceptionHandle.handleException(e));
        }else {
            //将Throwable 和 未知错误的status code返回
            onError(new ExceptionHandle.ResponeThrowable(e,ExceptionHandle.UNKNOWN));
        }
    }
    public abstract void onError(ExceptionHandle.ResponeThrowable responeThrowable);
    public abstract void onResponse(T t);

    @Override
    public void onCompleted() {
    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }
}
