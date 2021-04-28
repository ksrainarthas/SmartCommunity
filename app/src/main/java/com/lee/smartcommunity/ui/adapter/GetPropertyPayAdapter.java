package com.lee.smartcommunity.ui.adapter;

import android.content.Context;

import com.lee.adapter.recyclerview.CommonAdapter;
import com.lee.adapter.recyclerview.base.ViewHolder;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.model.GetPropertyResult;

import java.util.List;

/**
 * 在线报修adapter
 *
 * @author Lee
 */
public class GetPropertyPayAdapter extends CommonAdapter<GetPropertyResult.DataBean> {

    private final Context context;

    public GetPropertyPayAdapter(Context context, int layoutId, List<GetPropertyResult.DataBean> data) {
        super(context, layoutId, data);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, GetPropertyResult.DataBean dataBean, int position) {
        holder.setText(R.id.tv_num, String.valueOf(position + 1));
        holder.setText(R.id.tv_content, dataBean.getDiy_content());
        holder.getView(R.id.tv_content).requestFocus();
        holder.setText(R.id.tv_time, dataBean.getProperty_year_name()+"/"+dataBean.getProperty_month_num());
        String status = String.valueOf(dataBean.getProperty_price());
        if (status == null || status.equals("0")) {
            holder.setText(R.id.tv_status, context.getResources().getString(R.string.status_un_pay));
            holder.setBackgroundColor(R.id.tv_status, context.getResources().getColor(R.color.repair_status_undone));
        } else {
            holder.setText(R.id.tv_status, context.getResources().getString(R.string.status_pay));
            holder.setBackgroundColor(R.id.tv_status, context.getResources().getColor(R.color.repair_status_done));
        }
    }
}
