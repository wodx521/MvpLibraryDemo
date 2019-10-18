package com.wanou.mvplibrary.widgets.loading.dialog.iml;

/**
 * 创建时间:2019/8/7
 * 创建人：anthony.wang
 * 功能描述：
 */
public interface DialogInterface {
    /**
     * 展示
     */
    void show();

    /**
     * 消失
     */
    void dismiss();

    /**
     * 取消
     */
    void cancel();

    /**
     * 是否展示
     *
     * @return 展示状态 true 展示中 false 隐藏
     */
    boolean isShowing();
}
