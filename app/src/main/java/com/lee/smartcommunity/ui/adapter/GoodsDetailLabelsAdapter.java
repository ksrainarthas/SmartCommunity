package com.lee.smartcommunity.ui.adapter;

import android.content.Context;

import com.lee.adapter.recyclerview.CommonAdapter;
import com.lee.adapter.recyclerview.base.ViewHolder;
import com.lee.smartcommunity.R;

import java.util.List;

/**
 * 文件名: GoodsDetailLabelsAdapter
 * 创建者: WangYu
 * 创建日期: 2021/4/26 16:07
 */
public class GoodsDetailLabelsAdapter extends CommonAdapter<String> {

    private final Context context;

    public GoodsDetailLabelsAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_label, s);
    }
}