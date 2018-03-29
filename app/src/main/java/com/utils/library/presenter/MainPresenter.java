package com.utils.library.presenter;

import com.utils.library.bean.Translation;
import com.utils.library.model.MainMvpModel;
import com.utils.library.network.ExceptionHandle;
import com.utils.library.network.ResponseSubscriber;
import com.utils.library.view.MianView;

/**
 * Class Des:
 * Created by bjh on 2018/3/27.
 */
public class MainPresenter extends BaseMvpPresenter<MianView<Translation>>  {
    private final MainMvpModel mvpModel;

    public MainPresenter(){
        this.mvpModel = new MainMvpModel();
    }
    public void requestNetDatas(String a, String f, String t,String w ){
        if (null != getMvpView()) {
            getMvpView().showProgressDialog();
            mvpModel.request(a,f,t,w, new ResponseSubscriber<Translation>() {
                @Override
                public void onError(ExceptionHandle.ResponeThrowable responeThrowable) {
                    getMvpView().loadFailure(responeThrowable.message);
                }

                @Override
                public void onResponse(Translation translation) {
                    getMvpView().loadSuccess(translation);
                }
            });
        }
    }
}
