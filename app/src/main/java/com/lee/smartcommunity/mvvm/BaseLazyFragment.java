package com.lee.smartcommunity.mvvm;

import androidx.viewbinding.ViewBinding;

/**
 * NewLazyFragment 基类
 * 新版本懒加载
 *
 * @author Lee
 */
public abstract class BaseLazyFragment<VB extends ViewBinding, VM extends BaseViewModel> extends BaseFragment<VB, VM> {

    /**
     * 是否加载完成
     */
    private boolean isLoadOver;

    @Override
    public void onResume() {
        super.onResume();
        if (!isLoadOver) {
            onLazyLoad();
            isLoadOver = true;
        }
    }

    /**
     * 懒加载数据
     */
    public abstract void onLazyLoad();
}
