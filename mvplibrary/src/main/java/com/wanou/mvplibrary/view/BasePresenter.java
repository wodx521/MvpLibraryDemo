package com.wanou.mvplibrary.view;

public class BasePresenter<V extends BaseView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    protected String formatUrl(String needFormatUrl, String... params) {
        if (needFormatUrl != null && params.length > 0) {
            return String.format(needFormatUrl, params);
        }
        return null;
    }
}
