package com.lee.smartcommunity.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lee.retrofit.model.Status;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityNewsBinding;
import com.lee.smartcommunity.model.GetNewsResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.ui.adapter.GetNewsAdapter;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知公告
 * 文件名: GetNewsActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:31
 */
public class NewsActivity extends BaseActivity<ActivityNewsBinding, AppViewModel> {

    private List<GetNewsResult.DataBean> list;
    private GetNewsAdapter announcementAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.community_reminder);
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        viewBinding.rvAnnouncement.setLayoutManager(linearLayoutManager);
        viewBinding.rvAnnouncement.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(this).drawable(android.R.color.transparent).size(30).build());
        announcementAdapter = new GetNewsAdapter(this, list);
        viewBinding.rvAnnouncement.setAdapter(announcementAdapter);

        viewModel.getNewsResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                if (resource.data != null) {
                    putData(resource.data.getData());
                }
            } else if (resource.status == Status.ERROR) {
                ToastUtils.showShort("网络请求失败");
            }
        });
        viewModel.getNews(1);
    }

    private void putData(List<GetNewsResult.DataBean> data) {
        list.clear();
        list.addAll(data);
        announcementAdapter.notifyDataSetChanged();
    }
}