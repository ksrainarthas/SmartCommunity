package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityConfirmOrderBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 确认订单
 *
 * @author Lee
 */
public class ConfirmOrderActivity extends BaseActivity<ActivityConfirmOrderBinding, AppViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.confirm_order);
    }

    @Override
    protected boolean isShowHomeIcon() {
        return true;
    }

    @Override
    protected void initView() {

    }
}