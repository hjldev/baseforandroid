package com.baiyyy.byhjl.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by huangjinlong on 2015/12/28.
 */
public class MyBroadcast1 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("msg", "第一个存入的消息" + intent.getStringExtra("msg"));
        setResultExtras(bundle);
//        abortBroadcast();
    }
}
