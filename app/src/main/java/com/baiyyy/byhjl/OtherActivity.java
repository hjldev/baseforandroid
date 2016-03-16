package com.baiyyy.byhjl;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baiyyy.byhjl.recyclerview.AdapterSampleActivity;
import com.baiyyy.byhjl.recyclerview.AnimatorSampleActivity;

/**
 * Created by huangjinlong on 2015/12/21.
 */
public class OtherActivity extends LauncherActivity{

    String[] names = new String[]{"MainActivity", "GatherActivity", "MyViewActivity", "AnimationActivity", "FileInputOutputActivity", "GestureDetectorActivity", "GestureCustomActivity", "MatchGestureActivity", "ContentProviderActivity", "ServiceActivity", "MyBroadcastActivity", "URLTestActivity",
    "recyclerview.MainActivity", "AnimatorSampleActivity", "AdapterSampleActivity", "ToStringGsonTestActivity"};


    Class<?>[] clazzs = {MainActivity.class, GatherActivity.class, MyViewActivity.class, AnimationActivity.class, FileInputOutputActivity.class, GestureDetectorActivity.class, GestureCustomActivity.class, MatchGestureActivity.class, ContentProviderActivity.class, ServiceActivity.class, MyBroadcastActivity.class, URLTestActivity.class,
            com.baiyyy.byhjl.recyclerview.MainActivity.class, AnimatorSampleActivity.class, AdapterSampleActivity.class, ToStringGsonTestActivity.class};

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
        Context contextOther = null;
        try {
            contextOther = createPackageContext("com.baiyyy.byhjl", Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        SharedPreferences sp = contextOther.getSharedPreferences("config", MODE_WORLD_READABLE);
//        int count = sp.getInt("count", 0);
//        Toast.makeText(this,"MyProject已经被启动：" + count, Toast.LENGTH_SHORT).show();
        PackageInfo info = null;
        try {
            info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "包名为：" + info.packageName, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(OtherActivity.this, clazzs[position]);
    }
}
