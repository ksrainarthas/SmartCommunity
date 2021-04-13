package com.lee.smartcommunity.ui.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 水平分割线
 *
 * @author Lee
 */
public class HorizontalDividerItemItemDecoration extends BaseDividerItemDecoration {

    private MarginProvider mMarginProvider;

    protected HorizontalDividerItemItemDecoration(Builder builder) {
        super(builder);
        mMarginProvider = builder.mMarginProvider;
    }

    @Override
    protected Rect getDividerBound(int position, RecyclerView view, View child) {
        Rect bounds = new Rect(0, 0, 0, 0);
        int transitionX = (int) child.getTranslationX();
        int transitionY = (int) child.getTranslationY();
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        bounds.left = view.getPaddingLeft() +
                mMarginProvider.dividerLeftMargin(position, view) + transitionX;
        bounds.right = view.getWidth() - view.getPaddingRight() -
                mMarginProvider.dividerRightMargin(position, view) + transitionX;

        int dividerSize = getDividerSize(position, view);
        boolean isReverseLayout = isReverseLayout(view);
        if (mDividerType == DividerType.DRAWABLE) {
            // set top and bottom position of divider
            if (isReverseLayout) {
                bounds.bottom = child.getTop() - params.topMargin + transitionY;
                bounds.top = bounds.bottom - dividerSize;
            } else {
                bounds.top = child.getBottom() + params.bottomMargin + transitionY;
                bounds.bottom = bounds.top + dividerSize;
            }
        } else {
            // set center point of divider
            int halfSize = dividerSize / 2;
            if (isReverseLayout) {
                bounds.top = child.getTop() - params.topMargin - halfSize + transitionY;
            } else {
                bounds.top = child.getBottom() + params.bottomMargin + halfSize + transitionY;
            }
            bounds.bottom = bounds.top;
        }

        if (mPositionInsideItem) {
            if (isReverseLayout) {
                bounds.top += dividerSize;
                bounds.bottom += dividerSize;
            } else {
                bounds.top -= dividerSize;
                bounds.bottom -= dividerSize;
            }
        }
        return bounds;
    }

    @Override
    protected void setItemOffsets(Rect outRect, int position, RecyclerView view) {
        if (mPositionInsideItem) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        if (isReverseLayout(view)) {
            outRect.set(0, getDividerSize(position, view), 0, 0);
        } else {
            outRect.set(0, 0, 0, getDividerSize(position, view));
        }
    }

    private int getDividerSize(int position, RecyclerView view) {
        if (mPaintProvider != null) {
            return (int) mPaintProvider.dividerPaint(position, view).getStrokeWidth();
        } else if (mSizeProvider != null) {
            return mSizeProvider.dividerSize(position, view);
        } else if (mDrawableProvider != null) {
            Drawable drawable = mDrawableProvider.drawableProvider(position, view);
            return drawable.getIntrinsicHeight();
        }
        throw new RuntimeException("failed to get size");
    }

    /**
     * Interface for controlling divider margin
     */
    public interface MarginProvider {

        /**
         * Returns left margin of divider.
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param view     RecyclerView
         * @return left margin
         */
        int dividerLeftMargin(int position, RecyclerView view);

        /**
         * Returns right margin of divider.
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param view     RecyclerView
         * @return right margin
         */
        int dividerRightMargin(int position, RecyclerView view);
    }

    public static class Builder extends BaseDividerItemDecoration.Builder<Builder> {

        private MarginProvider mMarginProvider = new MarginProvider() {
            @Override
            public int dividerLeftMargin(int position, RecyclerView view) {
                return 0;
            }

            @Override
            public int dividerRightMargin(int position, RecyclerView view) {
                return 0;
            }
        };

        public Builder(Context context) {
            super(context);
        }

        public Builder margin(final int leftMargin, final int rightMargin) {
            return marginProvider(new MarginProvider() {
                @Override
                public int dividerLeftMargin(int position, RecyclerView view) {
                    return leftMargin;
                }

                @Override
                public int dividerRightMargin(int position, RecyclerView view) {
                    return rightMargin;
                }
            });
        }

        public Builder margin(int horizontalMargin) {
            return margin(horizontalMargin, horizontalMargin);
        }

        public Builder marginResId(@DimenRes int leftMarginId, @DimenRes int rightMarginId) {
            return margin(mResources.getDimensionPixelSize(leftMarginId), mResources.getDimensionPixelSize(rightMarginId));
        }

        public Builder marginResId(@DimenRes int horizontalMarginId) {
            return marginResId(horizontalMarginId, horizontalMarginId);
        }

        public Builder marginProvider(MarginProvider provider) {
            mMarginProvider = provider;
            return this;
        }

        public HorizontalDividerItemItemDecoration build() {
            checkBuilderParams();
            return new HorizontalDividerItemItemDecoration(this);
        }
    }
}