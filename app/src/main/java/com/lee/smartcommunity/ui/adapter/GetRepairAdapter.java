package com.lee.smartcommunity.ui.adapter;

import android.content.Context;

import com.lee.adapter.recyclerview.CommonAdapter;
import com.lee.adapter.recyclerview.base.ViewHolder;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.model.GetRepairResult;
import com.lee.utils.TimeUtils;

import java.util.List;

/**
 * 在线报修adapter
 *
 * @author Lee
 */
public class GetRepairAdapter extends CommonAdapter<GetRepairResult.DataBean> {

    private final Context context;

    public GetRepairAdapter(Context context, int layoutId, List<GetRepairResult.DataBean> data) {
        super(context, layoutId, data);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, GetRepairResult.DataBean dataBean, int position) {
        holder.setText(R.id.tv_num, String.valueOf(position + 1));
        holder.setText(R.id.tv_content, dataBean.getContent());
        holder.setText(R.id.tv_time, TimeUtils.millis2String(Long.parseLong(dataBean.getTime() + "000"), TimeUtils.getSafeDateFormat("MM/dd")));
        String status = dataBean.getStatus();
        if (status == null || status.equals("0")) {
            holder.setText(R.id.tv_status, context.getResources().getString(R.string.repair_status_undone));
            holder.setBackgroundColor(R.id.tv_status, context.getResources().getColor(R.color.repair_status_undone));
        } else {
            holder.setText(R.id.tv_status, context.getResources().getString(R.string.repair_status_done));
            holder.setBackgroundColor(R.id.tv_status, context.getResources().getColor(R.color.repair_status_done));
        }
    }
}
