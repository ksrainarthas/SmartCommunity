package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.app.AppManager;
import com.lee.smartcommunity.databinding.ActivityMainBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * @author Lee
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initViewClick();
    }

    private void initViewClick() {
        viewBinding.tvAnnouncement.setOnClickListener(v -> {
            startActivity(AnnouncementActivity.class);
        });
        viewBinding.tvOnlineProperty.setOnClickListener(v -> {
            startActivity(OnlinePropertyActivity.class);
        });
        viewBinding.tvCommodityOrder.setOnClickListener(v -> {
            startActivity(CommodityOrderActivity.class);
        });
        viewBinding.tvServiceOrder.setOnClickListener(v -> {
            startActivity(ServiceOrderActivity.class);
        });
        viewBinding.tvBuildingIntercom.setOnClickListener(v -> {
            startActivity(BuildingIntercomActivity.class);
        });
        viewBinding.tvElevatorCall.setOnClickListener(v -> {
            startActivity(ElevatorCallActivity.class);
        });
        viewBinding.tvHealthManage.setOnClickListener(v -> {
            startActivity(HealthManageActivity.class);
        });
    }

    @Override
    public void onBackPressed() {
        AppManager.getAppManager().appExit();
        super.onBackPressed();
    }
}