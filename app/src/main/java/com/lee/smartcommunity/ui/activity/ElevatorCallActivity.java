package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityElevatorCallBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 电梯呼叫
 * 文件名: ElevatorCallActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:50
 */
public class ElevatorCallActivity extends BaseActivity<ActivityElevatorCallBinding, AppViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_elevator_call;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.elevator_call);
    }

    @Override
    protected boolean isShowTime() {
        return true;
    }

    @Override
    protected void initView() {

    }
}