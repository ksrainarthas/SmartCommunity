package com.lee.smartcommunity.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityVideoChatBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.utils.LogUtils;
import com.lee.utils.ToastUtils;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

/**
 * 视频通话 https://docs.agora.io/cn/Video/landing-page?platform=Android
 *
 * @author Lee
 */
public class VideoChatActivity extends BaseActivity<ActivityVideoChatBinding, BaseViewModel> {
    private static final String TAG = VideoChatActivity.class.getSimpleName();

    private static final int PERMISSION_REQ_ID = 22;

    private static final String[] REQUESTED_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private RtcEngine mRtcEngine;
    private boolean mMuted;
    private boolean mCallEnd;

    private VideoCanvas mLocalVideo;
    private VideoCanvas mRemoteVideo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_chat;
    }

    @Override
    protected void initTheme() {

    }

    @Override
    protected boolean isContainToolBar() {
        return false;
    }

    @Override
    protected void initView() {
        initViewOnClick();
        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
            initEngineAndJoinChannel();
        }
    }

    private boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_ID) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED || grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                ToastUtils.showShort("Need permissions " + Manifest.permission.RECORD_AUDIO + "/" + Manifest.permission.CAMERA + "/" + Manifest.permission.WRITE_EXTERNAL_STORAGE);
                finish();
                return;
            }
            initEngineAndJoinChannel();
        }
    }

    private void initEngineAndJoinChannel() {
        initializeEngine();
        setupVideoConfig();
        setupLocalVideo();
        joinChannel();
    }

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LogUtils.d(TAG, "Join channel success, uid: " + (uid & 0xFFFFFFFFL));
                }
            });
        }

        @Override
        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LogUtils.d(TAG, "First remote video decoded, uid: " + (uid & 0xFFFFFFFFL));
                    setupRemoteVideo(uid);
                }
            });
        }


        @Override
        public void onUserOffline(final int uid, int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LogUtils.d(TAG, "User offline, uid: " + (uid & 0xFFFFFFFFL));
                    onRemoteUserLeft(uid);
                }
            });
        }
    };

    private void setupRemoteVideo(int uid) {
        ViewGroup parent = viewBinding.rlRemoteVideoView;
        if (parent.indexOfChild(mLocalVideo.view) > -1) {
            parent = viewBinding.rlLocalVideoView;
        }

        if (mRemoteVideo != null) {
            return;
        }

        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        surfaceView.setZOrderMediaOverlay(parent == viewBinding.rlLocalVideoView);
        parent.addView(surfaceView);
        mRemoteVideo = new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid);
        mRtcEngine.setupRemoteVideo(mRemoteVideo);
    }

    private void onRemoteUserLeft(int uid) {
        if (mRemoteVideo != null && mRemoteVideo.uid == uid) {
            removeFromParent(mRemoteVideo);
            mRemoteVideo = null;
        }
    }

    String appId = "0a57ca8e2a644a8bb51a6b24d63f368a";
    String accessToken = "0060a57ca8e2a644a8bb51a6b24d63f368aIABmTzKeqwuqf1ON3x83x+iHhKMKsfcICrgzXZ6VNQ2WQAmoacQAAAAAEAB33sfLRIifYAEAAQBDiJ9g";

    private void initializeEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), appId, mRtcEventHandler);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    private void setupVideoConfig() {
        mRtcEngine.enableVideo();
        mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
                VideoEncoderConfiguration.VD_1280x720,
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_ADAPTIVE));
    }

    private void setupLocalVideo() {
        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        surfaceView.setZOrderMediaOverlay(true);
        viewBinding.rlLocalVideoView.addView(surfaceView);
        mLocalVideo = new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, 0);
        mRtcEngine.setupLocalVideo(mLocalVideo);
    }

    private void joinChannel() {
        String token = accessToken;
        mRtcEngine.joinChannel(token, "zhangxu", "Extra Optional Data", 0);
    }

    private void leaveChannel() {
        if (mRtcEngine != null) {
            mRtcEngine.leaveChannel();
        }
    }

    private void startCall() {
        setupLocalVideo();
        joinChannel();
    }

    private void endCall() {
        removeFromParent(mLocalVideo);
        mLocalVideo = null;
        removeFromParent(mRemoteVideo);
        mRemoteVideo = null;
        leaveChannel();
    }

    private ViewGroup removeFromParent(VideoCanvas canvas) {
        if (canvas != null) {
            ViewParent parent = canvas.view.getParent();
            if (parent != null) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(canvas.view);
                return group;
            }
        }
        return null;
    }

    private void switchView(VideoCanvas canvas) {
        ViewGroup parent = removeFromParent(canvas);
        if (parent == viewBinding.rlLocalVideoView) {
            if (canvas.view instanceof SurfaceView) {
                ((SurfaceView) canvas.view).setZOrderMediaOverlay(false);
            }
            viewBinding.rlRemoteVideoView.addView(canvas.view);
        } else if (parent == viewBinding.rlRemoteVideoView) {
            if (canvas.view instanceof SurfaceView) {
                ((SurfaceView) canvas.view).setZOrderMediaOverlay(true);
            }
            viewBinding.rlLocalVideoView.addView(canvas.view);
        }
    }

    private void initViewOnClick() {
        viewBinding.rlLocalVideoView.setOnClickListener(v -> {
            switchView(mLocalVideo);
            switchView(mRemoteVideo);
        });

        viewBinding.btnMute.setOnClickListener(v -> {
            mMuted = !mMuted;
            mRtcEngine.muteLocalAudioStream(mMuted);
            int res = mMuted ? R.drawable.btn_mute : R.drawable.btn_un_mute;
            viewBinding.btnMute.setImageResource(res);
        });

        viewBinding.btnCall.setOnClickListener(v -> {
            if (mCallEnd) {
                startCall();
                mCallEnd = false;
                viewBinding.btnCall.setImageResource(R.drawable.btn_end_call);
            } else {
                endCall();
                mCallEnd = true;
                viewBinding.btnCall.setImageResource(R.drawable.btn_start_call);
            }
            viewBinding.btnMute.setVisibility(mCallEnd ? View.GONE : View.VISIBLE);
            viewBinding.btnSwitchCamera.setVisibility(mCallEnd ? View.GONE : View.VISIBLE);
        });

        viewBinding.btnSwitchCamera.setOnClickListener(v -> mRtcEngine.switchCamera());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mCallEnd) {
            leaveChannel();
        }
        RtcEngine.destroy();
        LogUtils.d(TAG, " RtcEngine.destroy");
    }
}
