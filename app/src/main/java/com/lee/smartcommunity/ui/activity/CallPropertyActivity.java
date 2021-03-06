package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityCallPropertyBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 呼叫物业
 *
 * @author Lee
 */
public class CallPropertyActivity extends BaseActivity<ActivityCallPropertyBinding, AppViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_call_property;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.call_property);
    }

    @Override
    protected boolean isShowTime() {
        return true;
    }

    @Override
    protected void initView() {

    }
}