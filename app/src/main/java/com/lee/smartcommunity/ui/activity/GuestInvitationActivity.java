package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityGuestInvitationBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * 访客邀请
 *
 * @author Lee
 */
public class GuestInvitationActivity extends BaseActivity<ActivityGuestInvitationBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guest_invitation;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.guest_invitation);
    }

    @Override
    protected void initView() {

    }
}