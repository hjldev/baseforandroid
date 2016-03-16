package com.baiyyy.byhjl.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by huangjinlong on 2015/12/28.
 * 定义一个Service，主要跟Activity进行数据交互
 */
public class BindService extends Service{

    // 定义一个count对象，与Activity交互测试数据
    private int count;
    // 定义一个boolean对象，用于控制Service
    private boolean quit;
    // 定义MyBind对象
    private MyBinder binder = new MyBinder();

    // 声明一个MyBinder对象，继承Binder,实现IBinder类
    public class MyBinder extends Binder{

        public int getCount(){
            return count;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("Service is Binder");
        return binder;
    }

    // Service被创建时回调该方法

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service is onCreate");
        // 启动一个线程，动态的修改count的值
        new Thread(){
            @Override
            public void run() {
                while(!quit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }
    // Service被断开连接时回调该方法

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Service is onUnbind");
        return true;
    }

    // Service关闭时回调

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        System.out.println("Service is onDestory");
    }
}
