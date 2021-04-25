package com.lee.smartcommunity.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.lee.adapter.recyclerview.CommonAdapter;
import com.lee.adapter.recyclerview.base.ViewHolder;
import com.lee.retrofit.model.Status;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityOnlineRepairBinding;
import com.lee.smartcommunity.model.AreaListResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.utils.TimeStampUtil;
import com.lee.smartcommunity.viewmodel.MainViewModel;
import com.lee.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 在线报修
 *
 * @author Lee
 */
public class OnlineRepairActivity extends BaseActivity<ActivityOnlineRepairBinding, MainViewModel> {

    private List<AreaListResult.DataBean> list;
    private CommonAdapter<AreaListResult.DataBean> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_repair;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.online_repair);
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        viewModel.getAreaListResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                if (resource.data != null) {
                    list = resource.data.getData();
                    if (list != null) {
                        adapter = new CommonAdapter<AreaListResult.DataBean>(mContext, R.layout.item_area_list, list) {
                            @Override
                            public void convert(ViewHolder holder, AreaListResult.DataBean dataBean, int position) {
                                holder.setText(R.id.tv_num, String.valueOf(position + 1));
                                holder.setText(R.id.tv_content, dataBean.getContent());
                                holder.setText(R.id.tv_time, TimeStampUtil.timeStamp2Date(dataBean.getTime(), "MM/dd"));
                                String status = dataBean.getStatus();
                                if (status == null || status.equals("0")) {
                                    holder.setText(R.id.tv_status, getResources().getString(R.string.repair_status_undone));
                                    holder.setBackgroundColor(R.id.tv_status, getResources().getColor(R.color.repair_status_undone));
                                } else {
                                    holder.setText(R.id.tv_status, getResources().getString(R.string.repair_status_done));
                                    holder.setBackgroundColor(R.id.tv_status, getResources().getColor(R.color.repair_status_done));
                                }

                            }
                        };
                        viewBinding.rvAreaList.setLayoutManager(new LinearLayoutManager(mContext));
                        viewBinding.rvAreaList.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(this)
                                .color(getResources().getColor(R.color.border)).build());
                        viewBinding.rvAreaList.setAdapter(adapter);
                    }
                }
            } else if (resource.status == Status.ERROR) {
                ToastUtils.showShort("网络请求失败");
            }
        });
        viewModel.getAreaList(1, 13515);
    }
}