package com.baiyyy.byhjl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

/**
 * Created by huangjinlong on 2015/12/24.
 */
public class GestureCustomActivity extends AppCompatActivity{
    private GestureOverlayView gesture_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_custom);
        gesture_view = (GestureOverlayView) findViewById(R.id.gesture_view);
        // 设置手势的绘制颜色
        gesture_view.setGestureColor(Color.RED);
        // 设置手势的绘制宽度
        gesture_view.setGestureStrokeWidth(6);
        // 为gestrue的手势完成事件绑定监听器
        gesture_view.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, final Gesture gesture) {
                View dialog = getLayoutInflater().inflate(R.layout.dialog_add_gesture, null);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.show_iv);
                final EditText editText = (EditText) dialog.findViewById(R.id.add_et);
                // 根据Gesture包含的手势创建一个位图
                Bitmap bitmap = gesture.toBitmap(256, 256, 10, 0xFFFF0000);
                imageView.setImageBitmap(bitmap);
                new AlertDialog.Builder(GestureCustomActivity.this)
                        .setView(dialog)
                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GestureLibrary gestureLibrary = GestureLibraries.fromFile("/sdcard/mygesturestest");
                                gestureLibrary.addGesture(editText.getText().toString().trim(), gesture);
                                gestureLibrary.save();
                            }
                        }).setNegativeButton("取消", null)
                        .show();
            }
        });

    }
}
