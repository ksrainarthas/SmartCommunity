package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityCommodityOrderBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * 商品订购
 * 文件名: CommodityOrderActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:38
 */
public class CommodityOrderActivity extends BaseActivity<ActivityCommodityOrderBinding, MainViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_order;
    }

    @Override
    protected void initView() {

    }
}