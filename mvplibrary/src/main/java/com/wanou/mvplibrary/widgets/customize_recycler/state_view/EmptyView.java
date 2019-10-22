package com.wanou.mvplibrary.widgets.customize_recycler.state_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanou.mvplibrary.R;

/**
 * 创建时间:2019/9/2
 * 创建人：anthony.wang
 * 功能描述：
 */
public class EmptyView extends FrameLayout {
    private EmptyType emptyType = EmptyType.NO_DATA;//默认空页面类型为无数据空页面
    private View emptyView;
    private ImageView ivEmptyIcon;
    private TextView tvEmptyTip;


    public EmptyView(Context context) {
        super(context);
        initView();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        emptyView = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_view, null);
        ivEmptyIcon = emptyView.findViewById(R.id.iv_icon);
        tvEmptyTip = emptyView.findViewById(R.id.tv_empty_tip);
        addView(emptyView);
        updateEmptyView();
    }

    public void updateEmptyView() {
        if (emptyType == null) {
            return;
        }
        int emptyIcon = R.drawable.ic_no_data_96dp;
        String emptyTip = "无数据";
        switch (emptyType) {
            case NO_DATA:
                emptyIcon = R.drawable.ic_no_data_96dp;
                emptyTip = "无数据";
                break;
            case NO_RESULT:
                emptyIcon = R.drawable.ic_no_result_96dp;
                emptyTip = "搜索无结果";
                break;
            case NO_DOWNLOAD:
                emptyIcon = R.drawable.ic_no_download_96dp;
                emptyTip = "无下载文件";
                break;
            default:
        }
        ivEmptyIcon.setImageResource(emptyIcon);
        tvEmptyTip.setText(emptyTip);
    }

    public EmptyType getEmptyType() {
        return emptyType;
    }

    public void setEmptyType(EmptyType emptyType) {
        this.emptyType = emptyType;
        updateEmptyView();
    }

    public enum EmptyType {
        /**
         * 这部分可以自定义状态, 更新不同的空状态
         * NO_DATA 无数据  NO_RESULT 搜索无结果 NO_DOWNLOAD 无下载内容
         */
        NO_DATA, NO_RESULT, NO_DOWNLOAD
    }
}
