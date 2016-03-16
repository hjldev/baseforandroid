package com.baiyyy.byhjl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baiyyy.byhjl.view.MyMatrixView;
import com.baiyyy.byhjl.view.MyView;
import com.baiyyy.byhjl.view.TextViewTest;


/**
 * Created by huangjinlong on 2015/12/23.
 */
public class MyViewActivity extends AppCompatActivity implements View.OnClickListener{
    private MyMatrixView my_view;
    // 设置倾斜度
    private float sx;
    // 缩放比例
    private float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview);
        my_view = (MyMatrixView) findViewById(R.id.my_view);
        sx = my_view.sx;
        scale = my_view.scale;
        findViewById(R.id.left_btn).setOnClickListener(this);
        findViewById(R.id.right_btn).setOnClickListener(this);
        findViewById(R.id.expand_btn).setOnClickListener(this);
        findViewById(R.id.reduce_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_btn:
                my_view.isScale = false;
                sx+=0.1;
                my_view.sx = sx;
                my_view.postInvalidate();
                break;
            case R.id.right_btn:
                my_view.isScale = false;
                sx-=0.1;
                my_view.sx = sx;
                my_view.postInvalidate();
                break;
            case R.id.expand_btn:
                my_view.isScale = true;
                if (scale < 2.0){
                    scale += 0.1;
                }
                my_view.scale = scale;
                my_view.postInvalidate();
                break;
            case R.id.reduce_btn:
                my_view.isScale = true;
                if (scale >0.5){
                    scale -= 0.1;
                }
                my_view.scale = scale;
                my_view.postInvalidate();
                break;
        }
    }
}
