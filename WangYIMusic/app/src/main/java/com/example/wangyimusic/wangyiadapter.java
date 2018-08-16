package com.example.wangyimusic;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 张翔宇 on 2018/3/15.
 */

public class wangyiadapter extends PagerAdapter {
    List<View> viewlist;
    public String titles[]={"个性推荐","歌单","主播电台","排行榜"};
    public wangyiadapter(List<View> views){
        viewlist=views;
    }
    @Override
    public int getCount() {
        return viewlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=viewlist.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewlist.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
