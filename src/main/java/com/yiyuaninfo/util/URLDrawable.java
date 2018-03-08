package com.yiyuaninfo.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by gaocongcong on 2017/11/8.
 */
public class URLDrawable extends BitmapDrawable {
    protected Bitmap bitmap;
    @Override
    public void draw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, getPaint());
        }
    }
}
