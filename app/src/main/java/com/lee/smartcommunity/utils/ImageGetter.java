package com.lee.smartcommunity.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

import com.lee.smartcommunity.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 文件名: ImageGetter
 * 创建者: WangYu
 * 创建日期: 2021/4/22 13:41
 */
public class ImageGetter implements Html.ImageGetter {
    private Context context;
    private TextView tv;

    public ImageGetter(Context context, TextView tv) {
        this.context = context;
        this.tv = tv;
    }

    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = context.getResources().getDrawable(R.mipmap.ic_launcher);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage(tv).execute(source, d);

        return d;
    }

    private class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;
        private TextView textView;

        public LoadImage(TextView textView) {
            this.textView = textView;
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = textView.getText();
                textView.setText(t);
            }
        }
    }
}