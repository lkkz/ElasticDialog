package com.cool.elasticlibrary;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cool on 2017/8/2.
 */

public class ElasticBackgroundView extends View {
    private Paint mPaint;
    private Path path;
    private int mWidth;
    private int mHight;
    private int mArcHight;//顶部遗留高度,默认40dp,遗留为了画顶部白色弧形并且能够看到
    private float currentArcHight;
    private long mDuration;//动画执行时间

    public ElasticBackgroundView(Context context) {
        this(context,null);
    }

    public ElasticBackgroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ElasticBackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        path = new Path();

        mDuration = 1000;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        path.moveTo(0, mArcHight);
        path.quadTo(mWidth/2, currentArcHight,mWidth, mArcHight);
        path.lineTo(mWidth,mHight);
        path.lineTo(0,mHight);
        path.close();
        canvas.drawPath(path,mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHight = h;
    }

    public void doStartAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mArcHight,-mArcHight, mArcHight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentArcHight = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(mDuration);
        valueAnimator.start();
    }

    public void setArcHight(int leaveHight){
        this.mArcHight = leaveHight;
    }

    public void setDuration(long duration){
        this.mDuration = duration;
    }

    public void setArcColor(int color){
        if(mPaint != null){
            mPaint.setColor(color);
        }
    }
}
