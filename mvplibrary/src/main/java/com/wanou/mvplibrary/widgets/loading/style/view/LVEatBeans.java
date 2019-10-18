package com.wanou.mvplibrary.widgets.loading.style.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.wanou.mvplibrary.widgets.loading.style.view.base.LVBase;


/**
 * Created by lumingmin on 16/6/20.
 */

public class LVEatBeans extends LVBase {

    private Paint mPaint, mPaintEye;

    private float mWidth = 0f;
    private float mHigh = 0f;
    private float mPadding = 5f;

    private float eatErWidth = 60f;
    private float eatErPositonX = 0f;
    int eatSpeed = 5;
    private float beansWidth = 10f;


    private float mAngle = 34;
    private float eatErStrtAngle = mAngle;
    private float eatErEndAngle = 360 - 2 * eatErStrtAngle;

    public LVEatBeans(Context context) {
        super(context);
    }

    public LVEatBeans(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVEatBeans(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHigh = getMeasuredHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float eatRightX = mPadding + eatErWidth + eatErPositonX;
        RectF rectF = new RectF(mPadding + eatErPositonX, mHigh / 2 - eatErWidth / 2, eatRightX, mHigh / 2 + eatErWidth / 2);
        canvas.drawArc(rectF, eatErStrtAngle, eatErEndAngle
                , true, mPaint);
        canvas.drawCircle(mPadding + eatErPositonX + eatErWidth / 2,
                mHigh / 2 - eatErWidth / 4,
                beansWidth / 2, mPaintEye);

        int beansCount = (int) ((mWidth - mPadding * 2 - eatErWidth) / beansWidth / 2);
        for (int i = 0; i < beansCount; i++) {

            float x = beansCount * i + beansWidth / 2 + mPadding + eatErWidth;
            if (x > eatRightX) {
                canvas.drawCircle(x,
                        mHigh / 2, beansWidth / 2, mPaint);
            }
        }


    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);

        mPaintEye = new Paint();
        mPaintEye.setAntiAlias(true);
        mPaintEye.setStyle(Paint.Style.FILL);
        mPaintEye.setColor(Color.BLACK);

    }


    public void setViewColor(int color)
    {
        mPaint.setColor(color);
        postInvalidate();
    }
    public void setEyeColor(int color)
    {
        mPaintEye.setColor(color);
        postInvalidate();
    }



    @Override
    protected void InitPaint() {
        initPaint();
    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        float mAnimatedValue = (float) valueAnimator.getAnimatedValue();
        eatErPositonX = (mWidth - 2 * mPadding - eatErWidth) * mAnimatedValue;
        eatErStrtAngle = mAngle * (1 - (mAnimatedValue * eatSpeed - (int) (mAnimatedValue * eatSpeed)));
        eatErEndAngle = 360 - eatErStrtAngle * 2;
        invalidate();
    }

    @Override
    protected void OnAnimationRepeat(Animator animation) {

    }

    @Override
    protected int OnStopAnim() {
        eatErPositonX = 0;
        postInvalidate();
        return 1;
    }
    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }
    @Override
    protected void AinmIsRunning() {

    }
    @Override
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }
}
