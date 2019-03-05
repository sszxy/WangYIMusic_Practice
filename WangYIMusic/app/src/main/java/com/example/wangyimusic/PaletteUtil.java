package com.example.wangyimusic;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

public class PaletteUtil {
    public static void getColor(Bitmap bitmap, Palette.PaletteAsyncListener listener){
       Palette.from(bitmap).generate(listener);
    }
}
