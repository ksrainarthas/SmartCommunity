package com.lee.smartcommunity.ui.fragment;

import android.os.Bundle;

import com.lee.smartcommunity.databinding.FragmentGoodsOrderBinding;
import com.lee.smartcommunity.mvvm.BaseLazyFragment;
import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.utils.LogUtils;

/**
 * 商品订单
 *
 * @author Lee
 */
public class GoodsOrderFragment extends BaseLazyFragment<FragmentGoodsOrderBinding, BaseViewModel> {
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
