package com.example.wangyimusic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 张翔宇 on 2018/3/30.
 */

public class controlview extends View {
    Paint paint;
    boolean zhuangtai=true;
    public controlview(Context context) {
        super(context);
    }

    public controlview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public controlview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        if(zhuangtai==false){
            canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-2,paint);
            canvas.drawLine(getWidth()/2-15,getHeight()/2-25,getWidth()/2-15,getHeight()/2+25,paint);
            canvas.drawLine(getWidth()/2+15,getHeight()/2-25,getWidth()/2+15,getHeight()/2+25,paint);
        }
        else {
            canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-2,paint);
            canvas.drawLine(getWidth()/2-20,getHeight()/2-30,getWidth()/2-20,getHeight()/2+30,paint);
            canvas.drawLine(getWidth()/2-20,getHeight()/2-30,getWidth()/2+30,getHeight()/2,paint);
            canvas.drawLine(getWidth()/2-20,getHeight()/2+30,getWidth()/2+30,getHeight()/2,paint);
        }
    }

}
