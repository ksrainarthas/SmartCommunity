package com.lee.smartcommunity.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.lee.retrofit.model.Status;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityPropertyPaymentBinding;
import com.lee.smartcommunity.model.GetPropertyResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.adapter.GetPropertyPayAdapter;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 物业缴费
 *
 * @author Lee
 */
public class PropertyPaymentActivity extends BaseActivity<ActivityPropertyPaymentBinding, AppViewModel> {


    private List<GetPropertyResult.DataBean> list;
    private GetPropertyPayAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_property_payment;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.property_payment);
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        viewModel.getPropertyPaymentResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                if (resource.data != null) {
                    list = resource.data.getData();
                    if (list != null) {
                        adapter = new GetPropertyPayAdapter(mContext, R.layout.item_repair_list, list);
                        viewBinding.rvPropertyPayment.setLayoutManager(new LinearLayoutManager(mContext));
                        viewBinding.rvPropertyPayment.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(this)
                                .color(getResources().getColor(R.color.border)).build());
                        viewBinding.rvPropertyPayment.setAdapter(adapter);
                    }
                }
            } else if (resource.status == Status.ERROR) {
                ToastUtils.showShort("网络请求失败");
            }
        });
        viewModel.getPropertyPayment(1, 13515);
    }
}