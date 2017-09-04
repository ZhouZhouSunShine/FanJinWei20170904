package com.example.fanjinwei20170904.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 范晋炜 on 2017/9/4 0004.
 * com.example.fanjinwei20170904.view
 * RingView
 */


public class RingView extends View {

    private final Paint paint;
    private final Context context;

    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.paint = new Paint();
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
    }

    //重写onDraw方法


    @Override
    protected void onDraw(Canvas canvas) {

        int center = getWidth() / 2;
        int innerCircle  = dip2px(context,83);
        int ringWidth = dip2px(context,10);

        //绘制内圆
        this.paint.setARGB(155,167,190,206);
        this.paint.setStrokeWidth(10);  //绘制内圆的厚度
        canvas.drawCircle(center,center,innerCircle+1+ringWidth/2,this.paint);  //圆环宽度为中间圆

        this.paint.setARGB(255, 255, 0, 0);
        this.paint.setStrokeWidth(ringWidth);//设置圆环宽度
        canvas.drawCircle(center,center, innerCircle+1+ringWidth/2, this.paint);//圆环宽度为中间圆

        //绘制外圆
        this.paint.setARGB(155,167,190,206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center,center, innerCircle+ringWidth, this.paint);

        super.onDraw(canvas);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
