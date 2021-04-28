package com.lee.smartcommunity.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lee.retrofit.model.Status;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityGoodsOrderBinding;
import com.lee.smartcommunity.model.GetShopGoodsCategoryResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.fragment.GoodsOrderFragment;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品订购
 * 文件名: GoodsOrderActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:38
 */
public class GoodsOrderActivity extends BaseActivity<ActivityGoodsOrderBinding, AppViewModel> {

    private String[] titles;
    private List<GetShopGoodsCategoryResult.DataBean> resultData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_order;
    }

    @Override
    protected boolean isContainToolBar() {
        return false;
    }

    @Override
    protected void initView() {
        viewBinding.tvBack.setOnClickListener(v -> finish());
        viewModel.getShopGoodsCategoryResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                GetShopGoodsCategoryResult result = resource.data;
                if (result != null && result.getData() != null) {
                    resultData = result.getData();
                    if (resultData != null) {
                        List<String> sortNameList = new ArrayList<>();
                        for (GetShopGoodsCategoryResult.DataBean dataBean : resultData) {
                            sortNameList.add(dataBean.getSort_name());
                        }
                        titles = sortNameList.toArray(new String[0]);
                        viewBinding.viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
                        viewBinding.tabLayout.setViewPager(viewBinding.viewPager);
                    }
                }
            } else if (resource.status == Status.ERROR) {
                ToastUtils.showShort("获取商品分类失败");
            }
        });
        viewModel.getShopGoodsCategory(11);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        // 新版懒加载
        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            GoodsOrderFragment goodsOrderFragment = new GoodsOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putString("storeId", resultData.get(position).getSort_id());
            goodsOrderFragment.setArguments(bundle);
            return goodsOrderFragment;
        }
    }
}