package com.lee.smartcommunity.ui.activity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityConfirmOrderBinding;
import com.lee.smartcommunity.model.CalcPrice;
import com.lee.smartcommunity.model.GetShopGoodsResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.adapter.CalcPriceAdapter;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.viewmodel.AppViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 确认订单
 *
 * @author Lee
 */
public class ConfirmOrderActivity extends BaseActivity<ActivityConfirmOrderBinding, AppViewModel> {

    private int mBuyNum = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.confirm_order);
    }

    @Override
    protected boolean isShowHomeIcon() {
        return true;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            GetShopGoodsResult.DataBean dataBean = (GetShopGoodsResult.DataBean) intent.getSerializableExtra("confirm_detail");
            if (dataBean != null) {
                Glide.with(this)
                        .load(dataBean.getImage())
                        .placeholder(R.drawable.ic_loading)
                        .fallback(R.drawable.ic_loading)
                        .error(R.drawable.ic_loading)
                        .into(viewBinding.ivImage);
                viewBinding.tvGoodsName.setText(dataBean.getName());
                viewBinding.tvBuyNum.setText(String.valueOf(mBuyNum));
                viewBinding.ivAdd.setOnClickListener(v -> addBuyNum());
                viewBinding.ivMinus.setOnClickListener(v -> minusBuyNum());
                viewBinding.btnSubmit.setOnClickListener(v -> {
                    // 假数据&假跳转
                    Intent extra = new Intent(ConfirmOrderActivity.this, PayInfoActivity.class);
                    extra.putExtra("pay_info", "9.9");
                    startActivity(extra);
                });
                //假数据
                viewBinding.tvUserName.setText("张恩");
                viewBinding.tvUserTel.setText("13812345678");
                viewBinding.tvUserAddress.setText("长春市诺睿德商务广场");
                viewBinding.tvPayAmount.setText(getString(R.string.price_value, "12.99"));
                LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                viewBinding.rvCalcPrice.setLayoutManager(llm);
                viewBinding.rvCalcPrice.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(this).drawable(android.R.color.transparent).size(20).build());
                String[] stringArray = this.getResources().getStringArray(R.array.calc_price_array);
                List<String> contents = new ArrayList<>();
                contents.add("自提点自提");
                contents.add(getString(R.string.price_value, "9.90"));
                contents.add(getString(R.string.price_value, "2.90"));
                contents.add("- ￥1.10");
                List<CalcPrice> list = new ArrayList<>();
                for (int i = 0; i < stringArray.length; i++) {
                    list.add(new CalcPrice(stringArray[i], contents.get(i)));
                }
                viewBinding.rvCalcPrice.setAdapter(new CalcPriceAdapter(this, R.layout.item_calc_price, list));
            }
        }
    }

    private void addBuyNum() {
        mBuyNum++;
        viewBinding.tvBuyNum.setText(String.valueOf(mBuyNum));
    }

    private void minusBuyNum() {
        if (mBuyNum > 1) {
            mBuyNum--;
            viewBinding.tvBuyNum.setText(String.valueOf(mBuyNum));
        }
    }
}