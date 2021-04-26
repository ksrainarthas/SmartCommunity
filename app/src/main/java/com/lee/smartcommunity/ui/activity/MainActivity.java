package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.app.AppManager;
import com.lee.smartcommunity.databinding.ActivityMainBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 首页
 *
 * @author Lee
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, AppViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isShowAddress() {
        return true;
    }

    @Override
    protected void initView() {
        initViewClick();
    }

    private void initViewClick() {
        viewBinding.tvAnnouncement.setTipVisibility(1);
        viewBinding.tvAnnouncement.setOnClickListener(v -> {
            startActivity(NewsActivity.class);
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