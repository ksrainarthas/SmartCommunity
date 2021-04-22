package com.lee.smartcommunity.utils;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;

import com.lee.smartcommunity.ui.activity.AnnouncementActivity;

import org.xml.sax.XMLReader;

/**
 * 文件名: MxgsaTagHandler
 * 创建者: WangYu
 * 创建日期: 2021/4/21 14:03
 */
public class MxgsaTagHandler implements Html.TagHandler {
    private int sIndex = 0;
    private int eIndex = 0;
    private final Context mContext;

    public MxgsaTagHandler(Context context) {
        mContext = context;
    }

    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (tag.toLowerCase().equals("mxgsa")) {
            if (opening) {
                sIndex = output.length();
            } else {
                eIndex = output.length();
                output.setSpan(new MxgsaSpan(), sIndex, eIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private class MxgsaSpan extends ClickableSpan implements View.OnClickListener {
        @Override
        public void onClick(View widget) {
            //具体代码，可以是跳转页面，可以是弹出对话框，下面是跳转页面
            mContext.startActivity(new Intent(mContext, AnnouncementActivity.class));
        }
    }
}