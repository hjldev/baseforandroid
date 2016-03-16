package com.baiyyy.byhjl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by huangjinlong on 2015/12/28.
 */
public class MyBroadcastActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setAction("com.baiyyy.byhjl.MYBROADCAST");
        intent.putExtra("msg", "我是消息");
        sendOrderedBroadcast(intent, null);
    }
}
