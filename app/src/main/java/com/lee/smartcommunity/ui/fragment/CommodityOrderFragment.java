package com.lee.smartcommunity.ui.fragment;

import android.os.Bundle;

import com.lee.smartcommunity.databinding.FragmentCommodityOrderBinding;
import com.lee.smartcommunity.mvvm.BaseLazyFragment;
import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.utils.LogUtils;

/**
 * 商品订单
 *
 * @author Lee
 */
public class CommodityOrderFragment extends BaseLazyFragment<FragmentCommodityOrderBinding, BaseViewModel> {
    private String key;

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            key = arguments.getString("key");
        }
    }

    @Override
    public void onLazyLoad() {
        viewBinding.textView.setText(key);
        LogUtils.d(key);
    }
}
