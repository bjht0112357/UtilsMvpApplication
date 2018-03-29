package com.utils.library.view;

/**
 * Class Des:
 * Created by bjh on 2018/3/27.
 */
public interface MianView<T> extends BaseMvpView {
        void loadSuccess(T t);
        void loadFailure(String msg);
}
