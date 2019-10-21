package com.wanou.mvplibrary.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hjq.toast.ToastUtils;
import com.uber.autodispose.AutoDisposeConverter;
import com.wanou.mvplibrary.util.RxLifecycleUtils;
import com.wanou.mvplibrary.widgets.loading.dialog.LoadingDialog;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P mPresenter;
    protected Activity mActivity;
    private View rootView;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPresenter() != null) {
            mPresenter = getPresenter();
        }
        mActivity = getActivity();
        rootView = getView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 获取布局资源
     *
     * @return 资源Id
     */
    protected abstract int getLayoutId();

    /**
     * 持有presenter 对象
     *
     * @return Presenter 对象
     */
    protected abstract P getPresenter();

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
            loadingDialog = new LoadingDialog(mActivity);
        }
        loadingDialog.setLoadingTips(tip);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void loadCompleted() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void loadError(Object errorMsg) {
        ToastUtils.show("请求出错");
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    @Override
    public <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }
//    public Fragment getFragment(String path) {
//        Fragment routerFragment = (Fragment) ARouter.getInstance().build(path).navigation();
//        return routerFragment;
//    }

}
