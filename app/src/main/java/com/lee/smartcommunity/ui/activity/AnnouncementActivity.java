package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityAnnouncementBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * 通知公告
 * 文件名: AnnouncementActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:31
 */
public class AnnouncementActivity extends BaseActivity<ActivityAnnouncementBinding, MainViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_announcement;
    }

    @Override
    protected void initView() {

    }
}