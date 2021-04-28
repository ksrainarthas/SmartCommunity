package com.lee.smartcommunity.mvvm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Fragment 基类
 *
 * @author Lee
 */
public abstract class BaseFragment<VB extends ViewBinding, VM extends BaseViewModel> extends Fragment {

    protected Activity mActivity;

    protected Context mContext;

    protected VB viewBinding;

    protected VM viewModel;

    private View view;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            initViewBinding(container);
            initViewModel();
            if (viewBinding != null) {
                view = viewBinding.getRoot();
            } else {
                initViewBinding(container);
                if (viewBinding != null) {
                    view = viewBinding.getRoot();
                } else {
                    return super.onCreateView(inflater, container, savedInstanceState);
                }
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    /**
     * 使用ViewBinding自动绑定布局, 替代ButterKnife
     */
    private void initViewBinding(@Nullable ViewGroup container) {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass != null) {
            Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            try {
                Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                viewBinding = (VB) method.invoke(null, getLayoutInflater(), container, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化viewModel
     */
    private void initViewModel() {
        viewModel = getViewModel();
        if (viewModel == null) {
            viewModel = createViewModel(this, (Class<VM>) getVmClass());
        }
    }

    /**
     * 获取泛型T的类型
     *
     * @return T.class
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
    protected VM createViewModel(Fragment fragment, Class<VM> cls) {
        try {
            return new ViewModelProvider(fragment).get(cls);
        } catch (Exception e) {
            return null;
        }
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
     * 子类可以调用这个方法进行初始化
     */
    protected abstract void initView();

    /**
     * 跳转页面
     *
     * @param cls 所跳转的目的Activity类
     */
    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(mContext, cls));
    }

    /**
     * 跳转页面
     *
     * @param cls 所跳转的目的Activity类
     */
    protected void startActivityFinish(Class<?> cls) {
        startActivity(cls);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 跳转页面
     *
     * @param cls    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(mContext, cls);
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
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewBinding = null;
        viewModel = null;
    }
}
