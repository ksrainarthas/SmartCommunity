package com.lee.smartcommunity.ui.adapter;

import android.content.Context;

import com.lee.adapter.recyclerview.CommonAdapter;
import com.lee.adapter.recyclerview.base.ViewHolder;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.model.CalcPrice;

import java.util.List;

/**
 * 文件名: CalcPriceAdapter
 * 创建者: WangYu
 * 创建日期: 2021/4/28 14:36
 */
public class CalcPriceAdapter extends CommonAdapter<CalcPrice> {

    private final Context context;

    public CalcPriceAdapter(Context context, int layoutId, List<CalcPrice> data) {
        super(context, layoutId, data);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, CalcPrice calcPrice, int position) {
        holder.setText(R.id.tv_project, calcPrice.getProject());
        holder.setTextColor(R.id.tv_content, position == 0 ? context.getResources().getColor(android.R.color.black) : context.getResources().getColor(R.color.goods_price));
        holder.setText(R.id.tv_content, calcPrice.getContent());
    }
}