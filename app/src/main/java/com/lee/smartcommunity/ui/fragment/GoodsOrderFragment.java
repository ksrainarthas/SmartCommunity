package com.lee.smartcommunity.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.lee.retrofit.model.Status;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.FragmentGoodsOrderBinding;
import com.lee.smartcommunity.databinding.IncludeRefreshBinding;
import com.lee.smartcommunity.model.GetShopGoodsResult;
import com.lee.smartcommunity.mvvm.BaseLazyFragment;
import com.lee.smartcommunity.ui.adapter.GoodsOrderAdapter;
import com.lee.smartcommunity.ui.decoration.SpaceItemDecoration;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ToastUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品订单
 *
 * @author Lee
 */
public class GoodsOrderFragment extends BaseLazyFragment<FragmentGoodsOrderBinding, AppViewModel> {

    private List<GetShopGoodsResult.DataBean> resultData = new ArrayList<>();

    private IncludeRefreshBinding refreshBinding;

    private static final int column = 3;
    private boolean isShowData;
    private String storeId;
    private GoodsOrderAdapter goodsOrderAdapter;

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            storeId = arguments.getString("storeId");
        }
    }

    @Override
    public void onLazyLoad() {
        refreshBinding = IncludeRefreshBinding.inflate(getLayoutInflater(), viewBinding.getRoot(), true);

        refreshBinding.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.getShopGoodsListById(36);
            }
        });
        viewModel.getShopGoodsListByIdResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                refreshBinding.refreshLayout.finishRefresh();
                refreshBinding.refreshLayout.finishLoadMore();
                GetShopGoodsResult goodsResult = resource.data;
                if (goodsResult != null) {
                    resultData = goodsResult.getData();
                    if (!isShowData) {
                        goodsOrderAdapter = new GoodsOrderAdapter(getActivity(), R.layout.item_goods_order, resultData);
                        refreshBinding.rvGoods.setLayoutManager(new GridLayoutManager(getActivity(), column));
                        refreshBinding.rvGoods.addItemDecoration(new SpaceItemDecoration(column));
                        refreshBinding.rvGoods.setAdapter(goodsOrderAdapter);
                        isShowData = true;
                    } else {
                        goodsOrderAdapter.setData(resultData);
                        goodsOrderAdapter.notifyDataSetChanged();
                    }
                }
            } else if (resource.status == Status.ERROR) {
                refreshBinding.refreshLayout.finishRefresh();
                refreshBinding.refreshLayout.finishLoadMore();
                ToastUtils.showShort("获取商品列表失败");
            }
        });
        //viewModel.getShopGoodsListById(Integer.parseInt(storeId));
        viewModel.getShopGoodsListById(36);// 测试数据
    }
}
