package com.lee.smartcommunity.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lee.adapter.recyclerview.CommonAdapter;
import com.lee.adapter.recyclerview.base.ViewHolder;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.model.GetShopGoodsResult;
import com.lee.smartcommunity.ui.activity.GoodsDetailActivity;

import java.util.List;

/**
 * 文件名: GoodsOrderAdapter
 * 创建者: WangYu
 * 创建日期: 2021/4/26 10:52
 */
public class GoodsOrderAdapter extends CommonAdapter<GetShopGoodsResult.DataBean> {

    private final Context context;

    public GoodsOrderAdapter(Context context, int layoutId, List<GetShopGoodsResult.DataBean> data) {
        super(context, layoutId, data);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, GetShopGoodsResult.DataBean dataBean, int position) {
        holder.setText(R.id.tv_selling_price, context.getString(R.string.price_value, dataBean.getPrice()));
        holder.setText(R.id.tv_member_price, context.getString(R.string.member_price_value, dataBean.getNb_price()));
        holder.setText(R.id.tv_name, dataBean.getName());
        ImageView iv_image = holder.getView(R.id.iv_image);
        Glide.with(context)
                .load(dataBean.getImage())
                .placeholder(R.drawable.ic_loading)
                .fallback(R.drawable.ic_loading)
                .error(R.drawable.ic_loading)
                .into(iv_image);
        holder.setOnClickListener(R.id.cl_container, v -> {
            Intent intent = new Intent(context, GoodsDetailActivity.class);
            intent.putExtra("goods_detail", dataBean);
            context.startActivity(intent);
        });
    }
}