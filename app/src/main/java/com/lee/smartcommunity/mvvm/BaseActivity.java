package com.lee.smartcommunity.mvvm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.lee.immersionbar.ImmersionBar;
import com.lee.smartcommunity.databinding.ActivityBaseBinding;
import com.lee.smartcommunity.ui.activity.MainActivity;
import com.lee.smartcommunity.ui.activity.PersonalCenterActivity;
import com.lee.swipeback.ParallaxBack;
import com.lee.swipeback.ParallaxHelper;
import com.lee.utils.ThreadUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Activity 基类
 *
 * @author Lee
 */
@ParallaxBack(edge = ParallaxBack.Edge.LEFT, layout = ParallaxBack.Layout.PARALLAX, edgeMode = ParallaxBack.EdgeMode.FULLSCREEN)
public abstract class BaseActivity<VB extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected ActivityBaseBinding baseBinding;

    protected Activity mActivity;

    protected Context mContext;

    protected VB viewBinding;

    protected VM viewModel;

    private Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initParam();
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        initTheme();
        initContentView();
        initViewModel();
    }

    /**
     * 设置Activity的主题, 同时注册主题更换监听
     */
    protected void initTheme() {
        ImmersionBar.with(this)
                .fitsSystemWindowsInt(true, 0xffffffff)
                .statusBarDarkFont(true)
                .navigationBarColorInt(0xffffffff)
                .init();
    }

    /**
     * 如果使用绑定布局(layout包裹), 不能使用setContentView
     * 否则会修改根布局view的默认tag, 不需要修改tag的请无视
     * 使用ViewBinding自动绑定布局, 替代ButterKnife
     */
    protected void initContentView() {
        if (isContainToolBar()) {
            baseBinding = ActivityBaseBinding.inflate(getLayoutInflater());
            View view = getLayoutInflater().inflate(getLayoutId(), null);
            baseBinding.container.addView(view);
            setContentView(baseBinding.getRoot());
            initViewBinding("bind", View.class, view);
            initBarView(baseBinding);
        } else {
            ParallaxHelper.disableParallaxBack(this);
            initViewBinding("inflate", LayoutInflater.class, getLayoutInflater());
            if (viewBinding != null) {
                setContentView(viewBinding.getRoot());
            } else {
                initViewBinding("inflate", LayoutInflater.class, getLayoutInflater());
                if (viewBinding != null) {
                    setContentView(viewBinding.getRoot());
                }
            }
        }
    }

    private void startTimer(ActivityBaseBinding baseBinding) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ThreadUtils.getCpuPool().execute(() -> {
                    runOnUiThread(() -> {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        Date date = new Date(System.currentTimeMillis());
                        baseBinding.tvTime.setText(simpleDateFormat.format(date));
                    });
                });
            }
        }, 0, 1000);
    }

    /**
     * 设置是否包含默认toolBar
     *
     * @return 重写 返回true包含toolBar
     */
    protected boolean isContainToolBar() {
        return true;
    }

    /**
     * 设置是否显示回到首页图片
     *
     * @return 重写 返回true显示图片
     */
    protected boolean isShowHomeIcon() {
        return false;
    }

    /**
     * 设置是否显示地址,只有首页显示
     *
     * @return 重写 返回true包含Address
     */
    protected boolean isShowAddress() {
        return false;
    }

    /**
     * 设置是否显示图片
     *
     * @return 重写 返回true包含Head 默认显示, 头像界面多
     */
    protected boolean isShowHead() {
        return true;
    }

    /**
     * 设置是否显示时间
     *
     * @return 重写 返回true包含Time 默认不显示, 时间界面少
     */
    protected boolean isShowTime() {
        return false;
    }

    /**
     * 反射初始化ViewBinding
     * ActivityBaseBinding.inflate()
     * ActivityBaseBinding.bind()
     * 具体getActualTypeArguments取[0]还是[1]
     * 取决于BaseActivity的泛型的第几位, 此方法为VB是[0]
     */
    protected void initViewBinding(String name, Class<?> cls, Object obj) {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass != null) {
            Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            try {
                Method method = aClass.getDeclaredMethod(name, cls);
                method.setAccessible(true);
                viewBinding = (VB) method.invoke(null, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化标题内容
     */
    protected void initBarView(ActivityBaseBinding baseBinding) {
        baseBinding.tvBack.setOnClickListener(v -> finish());
        baseBinding.tvTitle.setText(setTitle());
        if (isShowHomeIcon()) {
            baseBinding.tvHome.setVisibility(View.VISIBLE);
            baseBinding.tvHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppManager.getAppManager().finishAllActivity();
                    startActivity(MainActivity.class);
                }
            });
        } else {
            baseBinding.tvHome.setVisibility(View.GONE);
        }
        if (isShowAddress()) {
            baseBinding.tvAddress.setVisibility(View.VISIBLE);
        }
        if (isShowHead()) {
            baseBinding.tvHead.setVisibility(View.VISIBLE);
            baseBinding.tvTime.setVisibility(View.GONE);
            baseBinding.tvHead.setOnClickListener(v -> startActivity(PersonalCenterActivity.class));
        } else {
            baseBinding.tvHead.setVisibility(View.GONE);
        }
        if (isShowTime()) {
            baseBinding.tvHead.setVisibility(View.GONE);
            baseBinding.tvTime.setVisibility(View.VISIBLE);
            startTimer(baseBinding);
        } else {
            baseBinding.tvTime.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化要绑定view
     */
    private void initViewModel() {
        viewModel = getViewModel();
        if (viewModel == null) {
            viewModel = createViewModel(this, (Class<VM>) getVmClass());
        }
        initView();
    }

    /**
     * 获取泛型T的类型
     * 具体getActualTypeArguments取[0]还是[1]
     * 取决于BaseActivity的泛型的第几位, 此方法为VM是[1]
     *
     * @return VM.class
     */
    private Class<?> getVmClass() {
        Class<?> vmClass;
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            vmClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[1];
        } else {
            // 如果没有指定泛型参数, 则默认使用BaseViewModel
            vmClass = BaseViewModel.class;
        }
        return vmClass;
    }

    /**
     * 创建ViewModel
     *
     * @param cls VM.class
     * @return 继承ViewModel的viewModel
     */
    protected VM createViewModel(FragmentActivity activity, Class<VM> cls) {
        try {
            return new ViewModelProvider(activity).get(cls);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 执行需要在super.onCreate(savedInstanceState)之前的方法
     */
    protected void initParam() {

    }

    /**
     * 设置标题
     *
     * @return 要显示的标题名称
     */
    protected String setTitle() {
        return "";
    }

    /**
     * 初始化ViewModel
     *
     * @return 重写此方法创建带factory的viewModel
     */
    protected VM getViewModel() {
        return null;
    }

    /**
     * 获取要显示内容的布局文件的资源id
     *
     * @return 显示的内容界面的资源id
     */
    protected abstract int getLayoutId();

    /**
     * 子类可以调用这个方法进行初始化
     */
    protected abstract void initView();

    /**
     * 跳转页面
     *
     * @param cls 所跳转的目的Activity类
     */
    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 跳转页面
     *
     * @param cls 所跳转的目的Activity类
     */
    protected void startActivityFinish(Class<?> cls) {
        startActivity(cls);
        finish();
    }

    /**
     * 跳转页面
     *
     * @param cls    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param cls    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected void startActivityFinish(Class<?> cls, Bundle bundle) {
        startActivity(cls, bundle);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        viewBinding = null;
        viewModel = null;
    }
}
