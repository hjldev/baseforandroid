package com.baiyyy.byhjl.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.baiyyy.byhjl.R;

import java.util.jar.Attributes;

/**
 * Created by huangjinlong on 2015/12/23.
 */
public class MyMatrixView extends View{
    // 初始化的图片资源
    private Bitmap bitmap;
    // Matrix实例
    private Matrix matrix = new Matrix();

    // 设置倾斜度
    public float sx = 0.0f;
    // 位图的宽和高
    private int width, height;
    // 缩放比例
    public float scale = 1.0f;
    // 判断是缩放还是旋转
    public boolean isScale = false;

    public MyMatrixView(Context context, AttributeSet set) {
        super(context, set);
        // 获得位图
        bitmap = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 重置Matrix
        matrix.reset();
        // 判断是旋转还是缩放
        if (!isScale){
            matrix.setSkew(sx,0);
        } else{
            matrix.setScale(scale, scale);
        }
        // 根据原始位图和Matrix创建新图片
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        canvas.drawBitmap(bitmap2, matrix, null);
    }
}
