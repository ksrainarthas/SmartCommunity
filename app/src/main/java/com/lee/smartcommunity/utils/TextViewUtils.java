package com.lee.smartcommunity.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.lee.smartcommunity.R;

/**
 * 文件名: TextUtils
 * 创建者: WangYu
 * 创建日期: 2021/4/26 16:29
 */
public class TextViewUtils {

    /**
     * @param context     上下文
     * @param textView    需要设置的TextView
     * @param string      整段文字文本
     * @param fixedLength 不需要设置颜色的文字的长度
     * @param isFront     需要设置的文字是否在前
     */
    public static void setSpannable(Context context, TextView textView, String string, int fixedLength, boolean isFront) {
        try {
            SpannableStringBuilder ssb = new SpannableStringBuilder(string);
            ForegroundColorSpan fcs = new ForegroundColorSpan(context.getResources().getColor(R.color.goods_price));
            if (isFront) {
                ssb.setSpan(fcs, 0, string.length() - fixedLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                ssb.setSpan(fcs, fixedLength, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setText(ssb);
        } catch (Exception e) {
            textView.setText(string);
        }
    }
}