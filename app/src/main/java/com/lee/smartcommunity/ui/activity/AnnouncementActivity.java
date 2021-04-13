package com.lee.smartcommunity.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.model.AnnouncementModel;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.adapter.AnnouncementAdapter;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知公告
 * 文件名: AnnouncementActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:31
 */
public class AnnouncementActivity extends BaseActivity<com.lee.smartcommunity.databinding.ActivityAnnouncementBinding, MainViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_announcement;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        viewBinding.rvAnnouncement.setLayoutManager(linearLayoutManager);
        viewBinding.rvAnnouncement.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(this).drawable(android.R.color.transparent).size(30).build());
        List<AnnouncementModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AnnouncementModel announcementModel = new AnnouncementModel();
            announcementModel.setTitle("标题" + (i + 1));
            announcementModel.setContent("内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容" + (i + 1));
            list.add(announcementModel);
        }
        AnnouncementAdapter announcementAdapter = new AnnouncementAdapter(this, list);
        viewBinding.rvAnnouncement.setAdapter(announcementAdapter);
    }
}