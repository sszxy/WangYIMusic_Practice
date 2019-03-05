package com.example.wangyimusic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 张翔宇 on 2018/3/30.
 */

public class playView extends View {
    Paint paint;
    Bitmap bitmap;
    public playView(Context context) {
        super(context,null);

    }

    public playView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.playView);
        int bitmapid=ta.getResourceId(R.styleable.playView_bitmapsrc,R.drawable.timg);
        bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.timg);
        ta.recycle();


    }

    public playView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setBitmap(Bitmap bitmap){
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        float scale=((float) (getWidth()-135))/width;
        Matrix x=new Matrix();
        x.postScale(scale,scale);
        this.bitmap=Bitmap.createBitmap(bitmap,0,0,width,height,x,true);
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLACK);
        int width=getWidth();
        int hight=getHeight();
        canvas.drawCircle(width/2,hight/2,width/2,paint);
        Path path=new Path();
        path.addCircle(width/2,hight/2,width/2-135, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,135,135,paint);
    }
}
