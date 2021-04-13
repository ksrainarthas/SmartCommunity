package com.lee.smartcommunity.ui.decoration;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 分割线  https://github.com/yqritc/RecyclerView-FlexibleDivider
 *
 * @author Lee
 */
public abstract class BaseDividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int DEFAULT_SIZE = 1;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };


    protected enum DividerType {
        /**
         * 绘制分割线模式
         */
        DRAWABLE,
        /**
         * 绘制分割线模式
         */
        PAINT,
        /**
         * 绘制分割线模式
         */
        COLOR
    }

    protected DividerType mDividerType;
    protected VisibilityProvider mVisibilityProvider;
    protected PaintProvider mPaintProvider;
    protected ColorProvider mColorProvider;
    protected DrawableProvider mDrawableProvider;
    protected SizeProvider mSizeProvider;
    protected boolean mShowLastDivider;
    protected boolean mPositionInsideItem;
    private Paint mPaint;

    protected BaseDividerItemDecoration(Builder builder) {
        if (builder.mPaintProvider != null) {
            mDividerType = DividerType.PAINT;
            mPaintProvider = builder.mPaintProvider;
        } else if (builder.mColorProvider != null) {
            mDividerType = DividerType.COLOR;
            mColorProvider = builder.mColorProvider;
            mPaint = new Paint();
            setSizeProvider(builder);
        } else {
            mDividerType = DividerType.DRAWABLE;
            if (builder.mDrawableProvider == null) {
                TypedArray a = builder.mContext.obtainStyledAttributes(ATTRS);
                final Drawable divider = a.getDrawable(0);
                a.recycle();
                mDrawableProvider = new DrawableProvider() {
                    @Override
                    public Drawable drawableProvider(int position, RecyclerView view) {
                        return divider;
                    }
                };
            } else {
                mDrawableProvider = builder.mDrawableProvider;
            }
            mSizeProvider = builder.mSizeProvider;
        }

        mVisibilityProvider = builder.mVisibilityProvider;
        mShowLastDivider = builder.mShowLastDivider;
        mPositionInsideItem = builder.mPositionInsideItem;
    }

    private void setSizeProvider(Builder builder) {
        mSizeProvider = builder.mSizeProvider;
        if (mSizeProvider == null) {
            mSizeProvider = new SizeProvider() {
                @Override
                public int dividerSize(int position, RecyclerView view) {
                    return DEFAULT_SIZE;
                }
            };
        }
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, RecyclerView view, @NonNull RecyclerView.State state) {
        RecyclerView.Adapter adapter = view.getAdapter();
        if (adapter == null) {
            return;
        }

        int itemCount = adapter.getItemCount();
        int lastDividerOffset = getLastDividerOffset(view);
        int validChildCount = view.getChildCount();
        int lastChildPosition = -1;
        for (int i = 0; i < validChildCount; i++) {
            View child = view.getChildAt(i);
            int childPosition = view.getChildAdapterPosition(child);

            if (childPosition < lastChildPosition) {
                // Avoid remaining divider when animation starts
                continue;
            }
            lastChildPosition = childPosition;

            if (!mShowLastDivider && childPosition >= itemCount - lastDividerOffset) {
                // Don't draw divider for last line if mShowLastDivider = false
                continue;
            }

            if (wasDividerAlreadyDrawn(childPosition, view)) {
                // No need to draw divider again as it was drawn already by previous column
                continue;
            }

            int groupIndex = getGroupIndex(childPosition, view);
            if (mVisibilityProvider.shouldHideDivider(groupIndex, view)) {
                continue;
            }

            Rect bounds = getDividerBound(groupIndex, view, child);
            switch (mDividerType) {
                case DRAWABLE:
                    Drawable drawable = mDrawableProvider.drawableProvider(groupIndex, view);
                    drawable.setBounds(bounds);
                    drawable.draw(canvas);
                    break;
                case PAINT:
                    mPaint = mPaintProvider.dividerPaint(groupIndex, view);
                    canvas.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, mPaint);
                    break;
                case COLOR:
                    mPaint.setColor(mColorProvider.dividerColor(groupIndex, view));
                    mPaint.setStrokeWidth(mSizeProvider.dividerSize(groupIndex, view));
                    canvas.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, mPaint);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect rect, @NonNull View v, RecyclerView view, @NonNull RecyclerView.State state) {
        int position = view.getChildAdapterPosition(v);
        int itemCount = view.getAdapter().getItemCount();
        int lastDividerOffset = getLastDividerOffset(view);
        if (!mShowLastDivider && position >= itemCount - lastDividerOffset) {
            // Don't set item offset for last line if mShowLastDivider = false
            return;
        }

        int groupIndex = getGroupIndex(position, view);
        if (mVisibilityProvider.shouldHideDivider(groupIndex, view)) {
            return;
        }

        setItemOffsets(rect, groupIndex, view);
    }

    /**
     * Check if recyclerview is reverse layout
     *
     * @param view RecyclerView
     * @return true if recyclerview is reverse layout
     */
    protected boolean isReverseLayout(RecyclerView view) {
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getReverseLayout();
        } else {
            return false;
        }
    }

    /**
     * In the case mShowLastDivider = false,
     * Returns offset for how many views we don't have to draw a divider for,
     * for LinearLayoutManager it is as simple as not drawing the last child divider,
     * but for a GridLayoutManager it needs to take the span count for the last items into account
     * until we use the span count configured for the grid.
     *
     * @param view RecyclerView
     * @return offset for how many views we don't have to draw a divider or 1 if its a
     * LinearLayoutManager
     */
    private int getLastDividerOffset(RecyclerView view) {
        if (view.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) view.getLayoutManager();
            GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
            int spanCount = layoutManager.getSpanCount();
            int itemCount = view.getAdapter().getItemCount();
            for (int i = itemCount - 1; i >= 0; i--) {
                if (spanSizeLookup.getSpanIndex(i, spanCount) == 0) {
                    return itemCount - i;
                }
            }
        }
        return 1;
    }

    /**
     * Determines whether divider was already drawn for the row the item is in,
     * effectively only makes sense for a grid
     *
     * @param position current view position to draw divider
     * @param view     RecyclerView
     * @return true if the divider can be skipped as it is in the same row as the previous one.
     */
    private boolean wasDividerAlreadyDrawn(int position, RecyclerView view) {
        if (view.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) view.getLayoutManager();
            GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
            int spanCount = layoutManager.getSpanCount();
            return spanSizeLookup.getSpanIndex(position, spanCount) > 0;
        }

        return false;
    }

    /**
     * Returns a group index for GridLayoutManager.
     * for LinearLayoutManager, always returns position.
     *
     * @param position current view position to draw divider
     * @param view     RecyclerView
     * @return group index of items
     */
    private int getGroupIndex(int position, RecyclerView view) {
        if (view.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) view.getLayoutManager();
            GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
            int spanCount = layoutManager.getSpanCount();
            return spanSizeLookup.getSpanGroupIndex(position, spanCount);
        }

        return position;
    }

    /**
     * 分割线绑定
     *
     * @param position position
     * @param view     view
     * @param child    child
     * @return Rect
     */
    protected abstract Rect getDividerBound(int position, RecyclerView view, View child);

    /**
     * 设置
     *
     * @param outRect  outRect
     * @param position position
     * @param view     view
     */
    protected abstract void setItemOffsets(Rect outRect, int position, RecyclerView view);

    /**
     * Interface for controlling divider visibility
     */
    public interface VisibilityProvider {

        /**
         * Returns true if divider should be hidden.
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param view     RecyclerView
         * @return True if the divider at position should be hidden
         */
        boolean shouldHideDivider(int position, RecyclerView view);
    }

    /**
     * Interface for controlling paint instance for divider drawing
     */
    public interface PaintProvider {

        /**
         * Returns {@link Paint} for divider
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param view     RecyclerView
         * @return Paint instance
         */
        Paint dividerPaint(int position, RecyclerView view);
    }

    /**
     * Interface for controlling divider color
     */
    public interface ColorProvider {

        /**
         * Returns {@link android.graphics.Color} value of divider
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param view     RecyclerView
         * @return Color value
         */
        int dividerColor(int position, RecyclerView view);
    }

    /**
     * Interface for controlling drawable object for divider drawing
     */
    public interface DrawableProvider {

        /**
         * Returns drawable instance for divider
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param view     RecyclerView
         * @return Drawable instance
         */
        Drawable drawableProvider(int position, RecyclerView view);
    }

    /**
     * Interface for controlling divider size
     */
    public interface SizeProvider {

        /**
         * Returns size value of divider.
         * Height for horizontal divider, width for vertical divider
         *
         * @param position Divider position (or group index for GridLayoutManager)
         * @param view     RecyclerView
         * @return Size of divider
         */
        int dividerSize(int position, RecyclerView view);
    }

    public static class Builder<T extends Builder> {

        private Context mContext;
        protected Resources mResources;
        private PaintProvider mPaintProvider;
        private ColorProvider mColorProvider;
        private DrawableProvider mDrawableProvider;
        private SizeProvider mSizeProvider;
        private VisibilityProvider mVisibilityProvider = new VisibilityProvider() {
            @Override
            public boolean shouldHideDivider(int position, RecyclerView view) {
                return false;
            }
        };
        private boolean mShowLastDivider = false;
        private boolean mPositionInsideItem = false;

        public Builder(Context context) {
            mContext = context;
            mResources = context.getResources();
        }

        public T paint(final Paint paint) {
            return paintProvider(new PaintProvider() {
                @Override
                public Paint dividerPaint(int position, RecyclerView view) {
                    return paint;
                }
            });
        }

        public T paintProvider(PaintProvider provider) {
            mPaintProvider = provider;
            return (T) this;
        }

        public T color(final int color) {
            return colorProvider(new ColorProvider() {
                @Override
                public int dividerColor(int position, RecyclerView view) {
                    return color;
                }
            });
        }

        public T colorResId(@ColorRes int colorId) {
            return color(ContextCompat.getColor(mContext, colorId));
        }

        public T colorProvider(ColorProvider provider) {
            mColorProvider = provider;
            return (T) this;
        }

        public T drawable(@DrawableRes int id) {
            return drawable(ContextCompat.getDrawable(mContext, id));
        }

        public T drawable(final Drawable drawable) {
            return drawableProvider(new DrawableProvider() {
                @Override
                public Drawable drawableProvider(int position, RecyclerView view) {
                    return drawable;
                }
            });
        }

        public T drawableProvider(DrawableProvider provider) {
            mDrawableProvider = provider;
            return (T) this;
        }

        public T size(final int size) {
            return sizeProvider(new SizeProvider() {
                @Override
                public int dividerSize(int position, RecyclerView view) {
                    return size;
                }
            });
        }

        public T sizeResId(@DimenRes int sizeId) {
            return size(mResources.getDimensionPixelSize(sizeId));
        }

        public T sizeProvider(SizeProvider provider) {
            mSizeProvider = provider;
            return (T) this;
        }

        public T visibilityProvider(VisibilityProvider provider) {
            mVisibilityProvider = provider;
            return (T) this;
        }

        public T showLastDivider() {
            mShowLastDivider = true;
            return (T) this;
        }

        public T positionInsideItem(boolean positionInsideItem) {
            mPositionInsideItem = positionInsideItem;
            return (T) this;
        }

        protected void checkBuilderParams() {
            if (mPaintProvider != null) {
                if (mColorProvider != null) {
                    throw new IllegalArgumentException(
                            "Use setColor method of Paint class to specify line color. Do not provider ColorProvider if you set PaintProvider.");
                }
                if (mSizeProvider != null) {
                    throw new IllegalArgumentException(
                            "Use setStrokeWidth method of Paint class to specify line size. Do not provider SizeProvider if you set PaintProvider.");
                }
            }
        }
    }
}
