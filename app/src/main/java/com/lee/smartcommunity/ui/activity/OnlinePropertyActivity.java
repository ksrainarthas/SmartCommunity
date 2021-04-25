package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityOnlinePropertyBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * 线上物业
 * 文件名: OnlinePropertyActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:43
 */
public class OnlinePropertyActivity extends BaseActivity<ActivityOnlinePropertyBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_property;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.online_property);
    }

    @Override
    protected void initView() {
        viewBinding.tvCallProperty.setOnClickListener(v -> {
            startActivity(CallPropertyActivity.class);
        });
        viewBinding.tvPropertyPayment.setOnClickListener(v -> {
            startActivity(PropertyPaymentActivity.class);
        });
        viewBinding.tvOnlineRepair.setOnClickListener(v -> {
            startActivity(OnlineRepairActivity.class);
        });
        viewBinding.tvGuestInvitation.setOnClickListener(v -> {
            startActivity(GuestInvitationActivity.class);
        });
    }
}