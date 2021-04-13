package com.lee.smartcommunity.ui.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * gridView布局边距
 *
 * @author Lee
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * 列数
     */
    private int spanCount;
    /**
     * 间隔
     */
    private int spacing;
    /**
     * 是否包含边缘
     */
    private boolean includeEdge;

    public SpaceItemDecoration(int spanCount) {
        this(spanCount, 20);
    }

    public SpaceItemDecoration(int spanCount, int spacing) {
        this(spanCount, spacing, true);
    }

    public SpaceItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        if (includeEdge) {
            rect.left = spacing - column * spacing / spanCount;
            rect.right = (column + 1) * spacing / spanCount;
            if (position < spanCount) {
                rect.top = spacing;
            }
            rect.bottom = spacing;
        } else {
            rect.left = column * spacing / spanCount;
            rect.right = spacing - (column + 1) * spacing / spanCount;
            if (position >= spanCount) {
                rect.top = spacing;
            }
        }
    }
}
