package com.baiyyy.byhjl;

import android.app.AlertDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by huangjinlong on 2015/12/24.
 */
public class MatchGestureActivity extends AppCompatActivity{

    // 定义手势编辑组件
    private GestureOverlayView gesture_view;
    // 记录手机上已有的手势
    private GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_custom);
        // 读取手势库
        gestureLibrary = GestureLibraries.fromFile("/sdcard/mygesturestest");
        if (gestureLibrary.load()){
            Toast.makeText(this, "手势库装载成功", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "手势库装载失败", Toast.LENGTH_SHORT).show();
        }
        gesture_view = (GestureOverlayView) findViewById(R.id.gesture_view);
        gesture_view.setGestureColor(Color.RED);
        gesture_view.setGestureStrokeWidth(6);
        gesture_view.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                // 识别用户刚刚绘制的手势
                ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
                ArrayList<String> result = new ArrayList<>();
                for (Prediction prediction : predictions){
                    if (prediction.score > 2.0){
                        result.add("与手势【" + prediction.name + "】的相似度为：" + prediction.score);
                    }
                }
                if (result.size() > 0){
                    ArrayAdapter adapter = new ArrayAdapter(MatchGestureActivity.this, android.R.layout.simple_dropdown_item_1line, result.toArray());
                    new AlertDialog.Builder(MatchGestureActivity.this)
                            .setAdapter(adapter, null)
                            .setPositiveButton("确定", null).show();
                } else{
                    Toast.makeText(MatchGestureActivity.this, "无法找到相匹配的手势", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
