package com.lee.smartcommunity.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 轻量级存储
 *
 * @author Lee
 */
public class SharedUtils {

    public static String TAG = "SmartCommunity";
    public static SharedPreferences sp;

    public static void setVip(Context context, boolean isVip) {
        sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        sp.edit().putBoolean("vip", isVip).apply();
    }

    public static boolean isVip(Context context) {
        sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sp.getBoolean("vip", false);
    }

    public static void clearHistory(Context context) {
        sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}
