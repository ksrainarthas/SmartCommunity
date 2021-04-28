package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.mvvm.AppManager;
import com.lee.smartcommunity.databinding.ActivityMainBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;
import com.lee.utils.ThreadUtils;
import com.lee.utils.ToastUtils;

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
    protected boolean isShowTime() {
        return true;
    }

    @Override
    protected void initView() {
        initViewClick();
    }

    private void initViewClick() {
        viewBinding.tvNews.setTipVisibility(1);
        viewBinding.tvNews.setOnClickListener(v -> {
            startActivity(NewsActivity.class);
        });
        viewBinding.tvOnlineProperty.setOnClickListener(v -> {
            startActivity(OnlinePropertyActivity.class);
        });
        viewBinding.tvGoodsOrder.setOnClickListener(v -> {
            startActivity(GoodsOrderActivity.class);
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

    private boolean backToQuit;

    @Override
    public void onBackPressed() {
        if (backToQuit) {
            backToQuit = false;
            AppManager.getAppManager().appExit();
            super.onBackPressed();
        } else {
            backToQuit = true;
            ToastUtils.showShort(R.string.app_quit);
            ThreadUtils.runOnUiThreadDelayed(() -> backToQuit = false, 1000);
        }
    }
}