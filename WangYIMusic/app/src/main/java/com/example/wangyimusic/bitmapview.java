package com.example.wangyimusic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 张翔宇 on 2018/3/13.
 */

public class bitmapview extends View {
    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ic_needle);
    Paint paint;
    public bitmapview(Context context) {
        super(context,null);
    }

    public bitmapview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public bitmapview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        Matrix matrix=new Matrix();
        matrix.postScale(0.3f,0.3f);
        matrix.postRotate(-30);
        Bitmap bitmap1=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        canvas.drawBitmap(bitmap1,0,0,null);
    }

}
