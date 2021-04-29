package com.lee.smartcommunity.ui.activity;

import android.content.Intent;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityPayInfoBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.utils.TextViewUtils;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 支付信息
 */
public class PayInfoActivity extends BaseActivity<ActivityPayInfoBinding, AppViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_info;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.pay_info);
    }

    @Override
    protected boolean isContainToolBar() {
        return true;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            String pay_info = intent.getStringExtra("pay_info");
            TextViewUtils.setSpannable(this, viewBinding.tvPayPrice, this.getString(R.string.pay_price, pay_info), 4, false);
        }
    }
}