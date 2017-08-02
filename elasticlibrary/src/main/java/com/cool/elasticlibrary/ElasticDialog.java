package com.cool.elasticlibrary;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by cool on 2017/8/2.
 */

public class ElasticDialog extends Dialog {
    private ElasticBackgroundView backgroundView;
    private Context mContext;
    private View view;
    private int mArcHight;//顶部遗留高度,默认40dp,白色圆弧形能够达到的高度相关连,经测试40效果较好
    private long mDuration;//动画执行时间
    private int arcColor;

    public ElasticDialog(@NonNull Context context) {
        super(context, R.style.stlyle_dialog_transparent_bg);
        this.mContext = context;
        mArcHight = dp2px(40);
        mDuration = 1000;
        arcColor = Color.WHITE;
    }

    /**
     * 设置dialog的布局
     * @param layoutId
     * @return
     */
    public ElasticDialog layout(int layoutId) {
        view = LayoutInflater.from(mContext).inflate(layoutId, null);
        setContentView(view);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.style_anim_bottom_in);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = getContext().getResources().getDisplayMetrics().widthPixels;
        window.setAttributes(params);
        return this;
    }


    @Override
    public void show() {
        super.show();
        addBackgroundView();
        doAnim();
    }

    /**
     * 添加背景动画view
     */
    private void addBackgroundView() {
        if (view instanceof FrameLayout) {
            FrameLayout fl = (FrameLayout) view;
            View backView = fl.getChildAt(0);
            if(backView instanceof ElasticBackgroundView){
                return;
            }
            fl.measure(0, 0);
            int measuredHeight = fl.getMeasuredHeight();
            int realHight = measuredHeight + mArcHight;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, realHight);
            backgroundView = new ElasticBackgroundView(mContext);
            backgroundView.setArcHight(mArcHight);
            backgroundView.setDuration(mDuration);
            backgroundView.setArcColor(arcColor);
            fl.addView(backgroundView, 0, layoutParams);
        }else {
            throw new IllegalArgumentException("dialog的根布局必须为FrameLayout");
        }
    }

    /**
     * 开始动画
     */
    private void doAnim() {
        if (backgroundView != null) {
            backgroundView.doStartAnimation();
        }
    }

    /**
     * 设置达到的弧高
     * @param arcHight
     * @return
     */
    public ElasticDialog arcHight(int arcHight) {
        this.mArcHight = dp2px(arcHight);
        return this;
    }

    /**
     * 设置背景动画时间
     * @param duration
     * @return
     */
    public ElasticDialog duration(long duration) {
        if (duration < 0) {
            this.mDuration = 1000;
        }
        this.mDuration = duration;
        return this;
    }

    /**
     * 弧形背景颜色
     * @param color
     * @return
     */
    public ElasticDialog arcColor(int color){
        this.arcColor = color;
        return this;
    }

    /**
     * 是否可取消
     * @param cancelable
     * @return
     */
    public ElasticDialog cancelable(boolean cancelable){
        setCancelable(cancelable);
        return this;
    }


    private int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }
}
