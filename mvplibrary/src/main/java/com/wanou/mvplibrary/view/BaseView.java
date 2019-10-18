package com.wanou.mvplibrary.view;

import android.content.Context;

import com.uber.autodispose.AutoDisposeConverter;

/**
 * 常用方法位置, toast等提示信息
 */
public interface BaseView {
    /**
     * toast展示
     *
     * @param msg 展示内容
     */
    void showToast(String msg);

    /**
     * 错误状态提示
     *
     * @param errorMsg 提示内容
     */
    void onError(String errorMsg);

    /**
     * 加载中提示
     *
     * @param tip 提示内容
     */
    void onLoading(String tip);

    /**
     * 加载数据完成
     */
    void loadCompleted();

    /**
     * 加载错误提示
     *
     * @param errorMsg 提示内容
     */
    void loadError(Object errorMsg);

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context getContext();

    /**
     * 管理生命周期
     */

    <T> AutoDisposeConverter<T> bindLifecycle();
}
