package com.example.wangyimusic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LrcView extends View implements View.OnTouchListener{
    LrcBean mLrcBean;
    Paint mTextPaint;
    Paint mHighPaint;
    String mDefaultText="找不到歌词";
    long mLineSpace;
    long mLrcSpace;
    int mPosition;
    int mCurrentPosition;
    int mDownY;
    int mMoveY;
    Scroller mScroller;
    MyPlayer mPlayer;
    int mTouchSlip;
    boolean mFlag=false;

    public LrcView(Context context) {
        super(context);
        initView();
    }

    public LrcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LrcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){
        mTextPaint = new Paint();
        mTextPaint.setDither(true);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(50);

        mHighPaint = new Paint();
        mHighPaint.setDither(true);
        mHighPaint.setAntiAlias(true);
        mHighPaint.setTextAlign(Paint.Align.CENTER);
        mHighPaint.setColor(Color.GREEN);
        mHighPaint.setTextSize(60);

        mLineSpace=20;
        measureLineHeight();
        mScroller=new Scroller(getContext());
        mTouchSlip=  ViewConfiguration.get(getContext()).getScaledTouchSlop();
        setOnTouchListener(this);
    }

    public void setPlayer(MyPlayer player){
        this.mPlayer=player;
    }

    public void measureLineHeight(){
        Rect rect=new Rect();
        mTextPaint.getTextBounds(mDefaultText,0,mDefaultText.length(),rect);
        mLrcSpace = rect.height()+ mLineSpace;
    }

    public void setLrc(String lrc){
        OkHttpUtil.GetHttp(lrc, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream=response.body().byteStream();
                LrcBean bean=LrcAnalyze.AnalyzeLrc(inputStream,"UTF8");
                setLrcBean(bean);
            }
        });
    }
    public void setLrcBean(LrcBean bean){
        this.mLrcBean=bean;
        invalidate();
    }

    public void getCurrentPosition(){
        if (mPlayer.getCurrentPosition()<mLrcBean.lineInfos.get(0).getStartTime()){
            mPosition=0;
        } else if (mPlayer.getCurrentPosition()>mLrcBean.lineInfos.get(mLrcBean.lineInfos.size()-1).getStartTime()) {
            mPosition=mLrcBean.lineInfos.size()-1;
        } else{
            for (int i=0;i<=mLrcBean.lineInfos.size()-2;i++){
                if (mPlayer.getCurrentPosition()>mLrcBean.lineInfos.get(i).startTime&&
                        mPlayer.getCurrentPosition()<mLrcBean.lineInfos.get(i+1).startTime){
                    mPosition = i;
                    break;
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mLrcBean!=null&&mPlayer!=null){
            getCurrentPosition();
            for (int i=0; i<mLrcBean.lineInfos.size();i++){
                if (i==mPosition){
                    canvas.drawText(mLrcBean.lineInfos.get(i).getContent(),getMeasuredWidth()/2,
                            getMeasuredHeight()/2+i*mLrcSpace,mHighPaint);
                }else {
                    canvas.drawText(mLrcBean.lineInfos.get(i).getContent(),getMeasuredWidth()/2,
                            getMeasuredHeight()/2+i*mLrcSpace,mTextPaint);
                }
            }
            if (mCurrentPosition!=mPosition){
                mScroller.startScroll(0,getScrollY(),0, (int) ((mPosition-mCurrentPosition)*mLrcSpace));
                mCurrentPosition=mPosition;
                invalidate();
            }
            postInvalidateDelayed(100);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY= (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveY= (int) event.getY();
                if (mFlag||Math.abs(mDownY-mMoveY)>mTouchSlip){
                    mFlag=true;
                    scrollBy(0,mDownY-mMoveY);
                    mDownY=mMoveY;
                    return true;
                }
                return false;
            case MotionEvent.ACTION_UP:
                if (mFlag){
                    mFlag=false;
                    return true;
                }
                break;
        }
        return false;
    }
}
