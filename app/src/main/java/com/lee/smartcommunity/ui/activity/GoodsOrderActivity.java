package com.lee.smartcommunity.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
        viewBinding.tvHead.setOnClickListener(v -> startActivity(PersonalCenterActivity.class));
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
                        viewBinding.viewPager.setAdapter(new MyPagerAdapter((FragmentActivity) mActivity));
                        viewBinding.viewPager.setOffscreenPageLimit(titles.length);
                        viewBinding.tabLayout.setTitles(titles);
                        viewBinding.tabLayout.setViewPager2(viewBinding.viewPager);
                    }
                }
            } else if (resource.status == Status.ERROR) {
                ToastUtils.showShort("获取商品分类失败");
            }
        });
        viewModel.getShopGoodsCategory(11);
    }

    public class MyPagerAdapter extends FragmentStateAdapter {
        public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            GoodsOrderFragment goodsOrderFragment = new GoodsOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putString("storeId", resultData.get(position).getSort_id());
            goodsOrderFragment.setArguments(bundle);
            return goodsOrderFragment;
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}