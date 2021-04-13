package com.lee.smartcommunity.livedata;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * 设置并监听单一数据源, 并做数据转换时使用 LiveData
 * 方便于当需要切换数据源时自动取消掉前一个数据源的监听
 *
 * @param <I> 数据源输入类型
 * @param <O> 转换的结果类型
 * @author Lee
 */
public class SingleSourceMapLiveData<I, O> extends MutableLiveData<O> {
    private LiveData<I> lastSource;

    private I lastData;

    private final Function<I, O> lastFunction;

    /**
     * 创建对象时传入需要转换数据的方法
     *
     * @param function 将数据源的I类型转为结果数据类型O
     */
    public SingleSourceMapLiveData(Function<I, O> function) {
        lastFunction = function;
    }

    private final Observer<I> observer = new Observer<I>() {
        @Override
        public void onChanged(I i) {
            if (i != null && i == lastData) {
                return;
            }

            lastData = i;
            O result = lastFunction.apply(i);
            setValue(result);
        }
    };

    /**
     * 设置数据源, 当有已设置过的数据源时会取消该数据源的监听
     */
    public void setSource(LiveData<I> source) {
        if (lastSource == source) {
            return;
        }

        if (lastSource != null) {
            lastSource.removeObserver(observer);
        }

        lastSource = source;

        if (hasActiveObservers()) {
            lastSource.observeForever(observer);
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (lastSource != null) {
            lastSource.observeForever(observer);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (lastSource != null) {
            lastSource.removeObserver(observer);
        }
    }
}
