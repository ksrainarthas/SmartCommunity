package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityBuildingIntercomBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 楼宇对讲
 * 文件名: BuildingIntercomActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:49
 */
public class BuildingIntercomActivity extends BaseActivity<ActivityBuildingIntercomBinding, AppViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_building_intercom;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.building_intercom);
    }

    @Override
    protected boolean isShowTime() {
        return true;
    }

    @Override
    protected void initView() {

    }
}