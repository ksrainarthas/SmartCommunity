package com.lee.smartcommunity.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.lee.retrofit.model.Status;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.FragmentGoodsOrderBinding;
import com.lee.smartcommunity.model.GetShopGoodsResult;
import com.lee.smartcommunity.mvvm.BaseLazyFragment;
import com.lee.smartcommunity.ui.adapter.GoodsOrderAdapter;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.ui.decoration.SpaceItemDecoration;
import com.lee.smartcommunity.ui.decoration.VerticalDividerItemItemDecoration;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品订单
 *
 * @author Lee
 */
public class GoodsOrderFragment extends BaseLazyFragment<FragmentGoodsOrderBinding, AppViewModel> {
    private static final int column = 3;
    private String storeId;
    private List<GetShopGoodsResult.DataBean> resultData = new ArrayList<>();

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            storeId = arguments.getString("storeId");
        }
    }

    @Override
    public void onLazyLoad() {
        viewModel.getShopGoodsListByIdResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                GetShopGoodsResult goodsResult = resource.data;
                if (goodsResult != null) {
                    resultData = goodsResult.getData();
                    GridLayoutManager glm = new GridLayoutManager(getActivity(), column);
                    viewBinding.rvGoods.setLayoutManager(glm);
                    viewBinding.rvGoods.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(getActivity()).drawable(android.R.color.transparent).size(15).build());
                    viewBinding.rvGoods.addItemDecoration(new VerticalDividerItemItemDecoration.Builder(getActivity()).drawable(android.R.color.transparent).size(30).build());
                    viewBinding.rvGoods.addItemDecoration(new SpaceItemDecoration(column));
                    viewBinding.rvGoods.setAdapter(new GoodsOrderAdapter(getActivity(), R.layout.item_goods_order, resultData));
                }
            } else if (resource.status == Status.ERROR) {
                ToastUtils.showShort("获取商品列表失败");
            }
        });
//        viewModel.getShopGoodsListById(Integer.parseInt(storeId));
        viewModel.getShopGoodsListById(36);// 测试数据
    }
}
