package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityPropertyPaymentBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 物业缴费
 *
 * @author Lee
 */
public class PropertyPaymentActivity extends BaseActivity<ActivityPropertyPaymentBinding, AppViewModel> {

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

    }
}