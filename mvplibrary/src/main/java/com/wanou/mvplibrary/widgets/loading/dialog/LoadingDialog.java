package com.wanou.mvplibrary.widgets.loading.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.wanou.mvplibrary.R;
import com.wanou.mvplibrary.widgets.loading.dialog.iml.DialogInterface;
import com.wanou.mvplibrary.widgets.loading.style.view.LVGhost;


/**
 * 创建时间:2019/8/7
 * 创建人：anthony.wang
 * 功能描述：展示loading效果的DIalog
 */
public class LoadingDialog implements DialogInterface {
    private LVGhost lvGhost;
    private AlertDialog loadingDialog;
    private View rootView;
    private TextView tvTips;
    public LoadingDialog(@NonNull Context context) {
        loadingDialog = new AlertDialog.Builder(context).create();
        Window window = loadingDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        rootView = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
        loadingDialog.setView(rootView);
        lvGhost = rootView.findViewById(R.id.lv_ghost);
        tvTips = rootView.findViewById(R.id.tv_tips);
        lvGhost.setViewColor(Color.WHITE);
        lvGhost.setHandColor(Color.BLACK);
    }

    public AlertDialog getLoadingDialog() {
        return loadingDialog;
    }

    public void setLoadingTips(String loadingTips) {
        if(loadingTips!=null){
            tvTips.setText(loadingTips);
        }
    }

    @Override
    public void show() {
        lvGhost.startAnim();
        loadingDialog.show();
    }

    @Override
    public void dismiss() {
        lvGhost.stopAnim();
        loadingDialog.dismiss();

    }

    @Override
    public void cancel() {
        lvGhost.stopAnim();
        loadingDialog.cancel();

    }

    @Override
    public boolean isShowing() {
        return loadingDialog.isShowing();
    }
}
