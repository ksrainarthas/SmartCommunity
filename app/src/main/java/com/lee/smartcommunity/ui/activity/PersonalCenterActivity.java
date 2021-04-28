package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityPersonalCenterBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.mvvm.BaseViewModel;

/**
 * 个人中心
 *
 * @author Lee
 */
public class PersonalCenterActivity extends BaseActivity<ActivityPersonalCenterBinding, BaseViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.personal_center);
    }

    @Override
    protected void initView() {

    }
}