package com.baiyyy.byhjl;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by huangjinlong on 2015/12/23.
 */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.stop_btn).setOnClickListener(this);
        anim = (AnimationDrawable) findViewById(R.id.iv).getBackground();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_btn:
                anim.start();
                break;
            case R.id.stop_btn:
                anim.stop();
                break;
        }
    }
}
