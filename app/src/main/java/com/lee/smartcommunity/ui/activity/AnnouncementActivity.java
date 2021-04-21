package com.lee.smartcommunity.ui.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityAnnouncementBinding;
import com.lee.smartcommunity.model.AnnouncementResult;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.net.AppUrl;
import com.lee.smartcommunity.ui.adapter.AnnouncementAdapter;
import com.lee.smartcommunity.ui.decoration.HorizontalDividerItemItemDecoration;
import com.lee.smartcommunity.viewmodel.MainViewModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知公告
 * 文件名: AnnouncementActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:31
 */
public class AnnouncementActivity extends BaseActivity<ActivityAnnouncementBinding, MainViewModel> {

    private List<AnnouncementResult.DataBean> list;
    private AnnouncementAdapter announcementAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_announcement;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        viewBinding.rvAnnouncement.setLayoutManager(linearLayoutManager);
        viewBinding.rvAnnouncement.addItemDecoration(new HorizontalDividerItemItemDecoration.Builder(this).drawable(android.R.color.transparent).size(30).build());
        announcementAdapter = new AnnouncementAdapter(this, list);
        viewBinding.rvAnnouncement.setAdapter(announcementAdapter);

        baseBinding.tvTitle.setText(this.getString(R.string.community_reminder));
        baseBinding.tvAddr.setVisibility(View.GONE);

//        viewModel.getAnnouncementResult().observe(this, resource -> {
//            if (resource.status == Status.SUCCESS) {
//                ToastUtils.showShort("网络请求成功" + resource.data.toString());
//            } else if (resource.status == Status.ERROR) {
//                ToastUtils.showShort("网络请求失败");
//            }
//        });
//        viewModel.getAnnouncement(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("?")
                .append("g=Api")
                .append("&c=House")
                .append("&a=getNews");

        OkGo.<String>post(AppUrl.BASE_URL + AppUrl.GET_ANNOUNCEMENT + stringBuilder.toString()).params("villageId", 1).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                putData(response.body());
            }
        });
    }

    private void putData(String json) {
        AnnouncementResult announcementResult = new Gson().fromJson(json, AnnouncementResult.class);
        list.clear();
        list.addAll(announcementResult.getData());
        announcementAdapter.notifyDataSetChanged();
    }
}