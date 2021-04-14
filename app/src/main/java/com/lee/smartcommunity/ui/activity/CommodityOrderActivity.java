package com.lee.smartcommunity.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityCommodityOrderBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.fragment.CommodityOrderFragment;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * 商品订购
 * 文件名: CommodityOrderActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:38
 */
public class CommodityOrderActivity extends BaseActivity<ActivityCommodityOrderBinding, MainViewModel> {

    private String[] titles;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_order;
    }

    @Override
    protected boolean isContainToolBar() {
        return false;
    }

    @Override
    protected void initView() {
        titles = new String[]{"蔬菜", "水果", "粮油", "洗化", "日化", "居家"};
        viewBinding.viewPager.setAdapter(new MyPagerAdapter((FragmentActivity) mActivity));
        viewBinding.tabLayout.setTitles(titles);
        viewBinding.tabLayout.setViewPager2(viewBinding.viewPager);
    }

    public class MyPagerAdapter extends FragmentStateAdapter {
        public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            CommodityOrderFragment commodityOrderFragment = new CommodityOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", titles[position]);
            commodityOrderFragment.setArguments(bundle);
            return commodityOrderFragment;
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}