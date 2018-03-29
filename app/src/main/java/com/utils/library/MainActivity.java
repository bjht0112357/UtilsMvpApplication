package com.utils.library;

import android.os.Bundle;
import android.widget.Toast;

import com.utils.library.base.BaseActivity;
import com.utils.library.bean.Translation;
import com.utils.library.log.AppLogger;
import com.utils.library.presenter.MainPresenter;
import com.utils.library.view.MianView;

public class MainActivity extends BaseActivity<MianView<Translation>, MainPresenter>  implements MianView<Translation>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return  new MainPresenter();
    }

    @Override
    protected void iniViews() {

    }

    @Override
    protected void getRequestDatas() {
        getPresenter().requestNetDatas("fy","auto","auto","hello%20world");
    }
    @Override
    public void loadSuccess(Translation data) {
        AppLogger.e(data.getContent().getOut());
    }

    @Override
    public void loadFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {


    }
}
