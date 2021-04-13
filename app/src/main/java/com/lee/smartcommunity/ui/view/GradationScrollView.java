package com.lee.smartcommunity.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 带滚动监听的scrollview
 *
 * @author Lee
 */
public class GradationScrollView extends ScrollView {

    private ScrollViewListener scrollViewListener;

    public GradationScrollView(Context context) {
        super(context);
    }

    public GradationScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GradationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldX, oldY);
        }
    }

    public interface ScrollViewListener {
        /**
         * 滚动监听
         *
         * @param scrollView scrollView
         * @param x          x
         * @param y          y
         * @param oldX       oldX
         * @param oldY       oldY
         */
        void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldX, int oldY);
    }
}