package com.baiyyy.byhjl.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by huangjinlong on 2015/12/28.
 */
public class MyBroadcast2 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = getResultExtras(true);
        Toast.makeText(context, bundle.getString("msg"), Toast.LENGTH_SHORT).show();
    }
}
