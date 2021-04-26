package com.lee.smartcommunity.ui.activity;

import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.bumptech.glide.Glide;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityGoodsDetailBinding;
import com.lee.smartcommunity.model.GetShopGoodsResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ToastUtils;

public class GoodsDetailActivity extends BaseActivity<ActivityGoodsDetailBinding, AppViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected boolean isContainToolBar() {
        return false;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            GetShopGoodsResult.DataBean dataBean = (GetShopGoodsResult.DataBean) intent.getSerializableExtra("goods_detail");
            Glide.with(this)
                    .load(dataBean.getImage())
                    .placeholder(R.drawable.ic_loading)
                    .fallback(R.drawable.ic_loading)
                    .error(R.drawable.ic_loading)
                    .into(viewBinding.ivImage);
            viewBinding.tvName.setText(dataBean.getName());
            viewBinding.button.setOnClickListener(v -> {
                ToastUtils.showShort("确认购买");
            });
            String selling_price = this.getString(R.string.selling_price_value, dataBean.getPrice());
            SpannableStringBuilder ssb = new SpannableStringBuilder(selling_price);
            ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.goods_price));
            ssb.setSpan(fcs, 4, selling_price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewBinding.tvSellingPrice.setText(ssb);
            String member_price = this.getString(R.string.member_price_value, dataBean.getNb_price());
            SpannableStringBuilder ssb2 = new SpannableStringBuilder(member_price);
            ForegroundColorSpan fcs2 = new ForegroundColorSpan(getResources().getColor(R.color.goods_price));
            ssb2.setSpan(fcs2, 4, member_price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewBinding.tvMemberPrice.setText(ssb2);
        }
    }
}