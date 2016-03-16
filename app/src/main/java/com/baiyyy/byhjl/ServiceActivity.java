package com.baiyyy.byhjl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.baiyyy.byhjl.service.BindService;

/**
 * Created by huangjinlong on 2015/12/28.
 */
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{

    // 声明IBinder对象
    private BindService.MyBinder binder;

    private Intent intent;

    // 定义一个ServiceConnection对象，用于Activity与Service数据交互
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("Service Connection");
            binder = (BindService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("Service Disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.stop_btn).setOnClickListener(this);
        findViewById(R.id.getstatus_btn).setOnClickListener(this);
        intent = new Intent();
        intent.setAction("com.baiyyy.byhjl.MYSERVICE");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_btn:
                // 绑定指定的Service
                bindService(intent, sc, BIND_AUTO_CREATE);
                break;
            case R.id.stop_btn:
                // 解除绑定的Service
                unbindService(sc);
                break;
            case R.id.getstatus_btn:
                // 获取从Service里面获取到的值
                Toast.makeText(this, "从Service中获取的count为" + binder.getCount(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
