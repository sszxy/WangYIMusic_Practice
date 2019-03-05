package com.example.wangyimusic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class playlastview extends View {
    Paint paint;
    public playlastview(Context context) {
        super(context);
        init();
    }

    public playlastview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public playlastview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init(){
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(getWidth()/2+20,getHeight()/2-30,getWidth()/2+20,getHeight()/2+30,paint);
        canvas.drawLine(getWidth()/2-30,getHeight()/2,getWidth()/2+20,getHeight()/2+30,paint);
        canvas.drawLine(getWidth()/2-30,getHeight()/2,getWidth()/2+20,getHeight()/2-30,paint);
        canvas.drawLine(getWidth()/2-33,getHeight()/2-30,getWidth()/2-33,getHeight()/2+30,paint);
    }
}
