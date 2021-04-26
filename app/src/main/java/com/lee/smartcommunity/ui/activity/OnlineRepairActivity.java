package com.lee.smartcommunity.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.lee.retrofit.model.Status;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityOnlineRepairBinding;
import com.lee.smartcommunity.model.GetRepairResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.adapter.GetRepairAdapter;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 在线报修
 *
 * @author Lee
 */
public class OnlineRepairActivity extends BaseActivity<ActivityOnlineRepairBinding, AppViewModel> {

    private List<GetRepairResult.DataBean> list;
    private GetRepairAdapter adapter;

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
        viewModel.getRepairResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                if (resource.data != null) {
                    list = resource.data.getData();
                    if (list != null) {
                        adapter = new GetRepairAdapter(mContext, R.layout.item_repair_list, list);
                        viewBinding.rvRepairList.setLayoutManager(new LinearLayoutManager(mContext));
                        viewBinding.rvRepairList.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(this)
                                .color(getResources().getColor(R.color.border)).build());
                        viewBinding.rvRepairList.setAdapter(adapter);
                    }
                }
            } else if (resource.status == Status.ERROR) {
                ToastUtils.showShort("网络请求失败");
            }
        });
        viewModel.getRepair("1", "13515");
    }
}