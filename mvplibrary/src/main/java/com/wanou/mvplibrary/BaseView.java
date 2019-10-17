package com.wanou.mvplibrary;

import android.content.Context;

import com.uber.autodispose.AutoDisposeConverter;

/**
 *
 */
public interface BaseView {
    void showToast(String msg);
    void onError(String errorMsg);
    void onLoadIng(String tip);
    void loadCompleted();
    void loadError(Object errorMsg);
    Context getContext();
    <T> AutoDisposeConverter<T> bindLifecycle();
}
