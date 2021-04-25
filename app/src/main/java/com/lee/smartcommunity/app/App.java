package com.lee.smartcommunity.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.lee.utils.LogUtils;
import com.lee.utils.Utils;

/**
 * 程序入口
 *
 * @author Lee
 */
public class App extends Application {

    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Utils.init(this);
        setApplication(this);
        initLogUtils();
    }

    private void initLogUtils() {
        LogUtils.Config config = LogUtils.getConfig();
        config.setLog2FileSwitch(true);
        config.setGlobalTag("JLog");
    }

    public static synchronized void setApplication(@NonNull Application application) {
        // 注册监听每个activity的生命周期, 便于堆栈式管理
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }

    public static App getContext() {
        return application;
    }
}
