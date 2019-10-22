package com.wanou.mvplibrary.widgets.customize_recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.hjq.toast.ToastUtils;
import com.wanou.mvplibrary.widgets.customize_recycler.state_view.EmptyView;
import com.wanou.mvplibrary.widgets.customize_recycler.state_view.ErrorView;

/**
 * 创建时间:2019/9/2
 * 创建人：anthony.wang
 * 功能描述：状态布局的父类，提供给需要带状态的view继承
 */
public abstract class BaseStateLayout extends FrameLayout {
    protected EmptyView emptyView;
    protected ErrorView errorView;
    protected EmptyView.EmptyType emptyType = EmptyView.EmptyType.NO_DATA;
    private View normalView;
    private StateType type = StateType.NORMAL;

    public BaseStateLayout(Context context) {
        super(context);
        init();
    }

    public BaseStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initView();
        normalView = getNormalView();
        emptyView = new EmptyView(getContext());
        errorView = new ErrorView(getContext());
        addView(errorView);
        addView(emptyView);
        addView(normalView);
        updateStateView();
    }

    protected abstract void initView();

    public void setErrorButtonOnclickListener(OnClickListener onclickListener) {
        errorView.setErrorButtonOnclickListener(onclickListener);
    }

    public void setStateType(StateType type) {
        // 如果新设置的状态和原状态一样 则显示隐藏状态不用重复设置
        if (this.type == type) {
            return;
        }
        this.type = type;
        updateStateView();
    }

    public void setEmptyType(EmptyView.EmptyType type) {
        this.emptyType = type;
        emptyView.setEmptyType(type);
    }

    public void updateStateView() {
        switch (type) {
            case EMPTY:
                normalView.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                break;
            case ERROR:
                normalView.setVisibility(View.GONE);
                errorView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                break;
            case NORMAL:
                normalView.setVisibility(View.VISIBLE);
                errorView.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                break;
            default:
        }
    }

    protected abstract View getNormalView();

}
