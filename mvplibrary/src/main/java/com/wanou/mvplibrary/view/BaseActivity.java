package com.wanou.mvplibrary.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hjq.toast.ToastUtils;
import com.wanou.mvplibrary.widgets.loading.dialog.LoadingDialog;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected P mPresenter;
    private Context mContext;
    private LoadingDialog loadingDialog;
    private View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        rootView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        setContentView(rootView);
        if (getPresenter() != null) {
            mPresenter = getPresenter();
        }

        initView();
        initData();
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 持有presenter对象
     *
     * @return presenter对象
     */
    protected abstract P getPresenter();

    /**
     * 获取布局资源
     *
     * @return 布局资源ID
     */
    protected abstract int getLayoutId();

    @Override
    public void showToast(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void onError(String errorMsg) {
        ToastUtils.show(errorMsg);
    }

    @Override
    public void onLoading(String tip) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mContext);
        }
        loadingDialog.setLoadingTips(tip);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }

    }
}
