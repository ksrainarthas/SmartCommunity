package com.lee.smartcommunity.ui.activity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityGoodsDetailBinding;
import com.lee.smartcommunity.model.GetShopGoodsResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.adapter.GoodsDetailLabelsAdapter;
import com.lee.smartcommunity.ui.decoration.VerticalDividerItemItemDecoration;
import com.lee.smartcommunity.utils.TextViewUtils;
import com.lee.smartcommunity.viewmodel.AppViewModel;

import java.util.Arrays;
import java.util.List;

/**
 * 商品详情
 *
 * @author Lee
 */
public class GoodsDetailActivity extends BaseActivity<ActivityGoodsDetailBinding, AppViewModel> {

    String[] labels = {"汁水饱满", "新鲜到家", "经济实惠", "产地直采"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.goods_detail);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            GetShopGoodsResult.DataBean dataBean = (GetShopGoodsResult.DataBean) intent.getSerializableExtra("goods_detail");
            if (dataBean != null) {
                Glide.with(this)
                        .load(dataBean.getImage())
                        .placeholder(R.drawable.ic_loading)
                        .fallback(R.drawable.ic_loading)
                        .error(R.drawable.ic_loading)
                        .into(viewBinding.ivImage);
                viewBinding.tvName.setText(dataBean.getName());
                viewBinding.confirmPurchase.setOnClickListener(v -> {
                    Intent extra = new Intent(this, ConfirmOrderActivity.class);
                    extra.putExtra("confirm_detail", dataBean);
                    startActivity(extra);
                });
                TextViewUtils.setSpannable(this, viewBinding.tvSellingPrice, this.getString(R.string.selling_price_value, dataBean.getPrice()), 4, false);
                TextViewUtils.setSpannable(this, viewBinding.tvMemberPrice, this.getString(R.string.member_price_value, dataBean.getNb_price()), 4, false);
                LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                viewBinding.rvLabels.setLayoutManager(llm);
                viewBinding.rvLabels.addItemDecoration(new VerticalDividerItemItemDecoration.Builder(this).drawable(android.R.color.transparent).size(20).build());
                // 假数据
                List<String> list = Arrays.asList(labels);
                viewBinding.rvLabels.setAdapter(new GoodsDetailLabelsAdapter(this, R.layout.item_goods_detail_labels, list));
                // 假数据
                TextViewUtils.setSpannable(this, viewBinding.tvCommentsValue, this.getString(R.string.comments_value, "5.3万+"), 2, true);
                TextViewUtils.setSpannable(this, viewBinding.tvFavorableRate, this.getString(R.string.favorable_rate, "99%"), 3, true);
            }
        }
    }
}