package com.example.fanjinwei20170904.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.fanjinwei20170904.R;

/**
 * Created by 范晋炜 on 2017/9/4 0004.
 * com.example.fanjinwei20170904.view
 * MySelfCircleView
 * 自定义View  设置各属性及效果显示
 */


public class MySelfCircleView extends View{

    /*
  * 第一圈颜色
  */
    int firstColor;
    /*
     * 第二圈颜色
     */
    int secondColor;
    /*
     * 圆的宽度
     */
    int circleWidth;
    /*
     * 速率
     */
    int speed;
    /*
     * 画笔
     */
    Paint mPaint;
    /*
     * 进度
     */
    int mProgress;
    /*
     * 是否切换标志
     */
    boolean isNext;

    public MySelfCircleView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for(int i=0; i<n; i++){
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomView_firstColor:
                    firstColor = typedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomView_secondColor:
                    secondColor = typedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomView_circleWidth:
                    circleWidth = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomView_speed:
                    speed = typedArray.getInt(attr, 20);
                    break;
            }
        }
        typedArray.recycle();

        mPaint = new Paint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext)
                            isNext = true;
                        else
                            isNext = false;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public MySelfCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySelfCircleView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - circleWidth / 2;// 半径
        mPaint.setStrokeWidth(circleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(firstColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(secondColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(secondColor); // 设置圆环的颜色
            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaint.setColor(firstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
    }
}
