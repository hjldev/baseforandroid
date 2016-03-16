package com.baiyyy.byhjl;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangjinlong on 2015/12/21.
 */
public class GatherActivity extends AppCompatActivity {
    private LinearLayout main_ll;
    private EditText et;
    private ImageView iv;
    private Timer timer;
    private ImageView iv_anim;
    private Button next_btn;
    private ImageView show_iv;
    private AssetManager asset = null;
    private String[] images = null;
    private int currentImage = 0;
    private TextView sdk_int;

    private List<Map<String, Object>> l = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather);
        et = (EditText) findViewById(R.id.et);
        iv = (ImageView) findViewById(R.id.iv);
        iv_anim = (ImageView) findViewById(R.id.iv_anim);
        next_btn = (Button) findViewById(R.id.next_btn);
        show_iv = (ImageView) findViewById(R.id.show_iv);
        sdk_int = (TextView) findViewById(R.id.sdk_int);
        sdk_int.setText("" + Build.VERSION.SDK_INT);
        setAnim();
        addListView();
        toNotifacation();
        setLevel();
        showNextImage();

    }

    private void showNextImage() {
        try {
            asset = getAssets();
            images = asset.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImage >= images.length){
                    currentImage = 0;
                }
                while (!images[currentImage].endsWith(".jpg") && !images[currentImage].endsWith(".png") && !images[currentImage].endsWith(".gif")){
                    currentImage++;
                    if (currentImage >= images.length){
                        currentImage = 0;
                    }
                }
                InputStream assetFile = null;
                try {
                    assetFile = asset.open(images[currentImage++]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BitmapDrawable bitmapDrawable = (BitmapDrawable) show_iv.getDrawable();
                if (bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled()){
                    bitmapDrawable.getBitmap().recycle();
                }
                show_iv.setImageBitmap(BitmapFactory.decodeStream(assetFile));
            }
        });
    }

    private void setAnim() {
        Animation animation = AnimationUtils.loadAnimation(GatherActivity.this, R.anim.set_other);
        animation.setFillAfter(true);
        iv_anim.setAnimation(animation);
    }

    private void setLevel() {
        final ClipDrawable drawable = (ClipDrawable) iv.getDrawable();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0){
                    drawable.setLevel(drawable.getLevel() + 500);
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
                if (drawable.getLevel() >= 10000){
                    timer.cancel();
                }
            }
        }, 0, 300);
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    private void addListView() {
        String[] string_arrays = getResources().getStringArray(R.array.string_array);
        main_ll = (LinearLayout) findViewById(R.id.main_ll);
        final ListView lv = new ListView(this);
        for(int i = 0; i < string_arrays.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("text", string_arrays[i]);
//            map.put("title", int_array[i]);
            l.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(this,l, android.R.layout.simple_list_item_1, new String[]{"text"}, new int[]{android.R.id.text1});
        lv.setAdapter(sa);
        main_ll.addView(lv);
    }

    private void toNotifacation() {

        findViewById(R.id.start_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNotification();
            }
        });
        findViewById(R.id.stop_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopNotification();
            }
        });
    }

    private void stopNotification() {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(0);
    }
    private void startNotification() {
        Intent intent = new Intent(GatherActivity.this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("启动MainActivity");
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(icon);
        builder.setContentText("快点启动吧，别墨迹啦");
        builder.setContentInfo("?");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setWhen(System.currentTimeMillis());
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);
        builder.setAutoCancel(true);
        builder.setContentIntent(pi);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
        findViewById(R.id.start_notification).setFocusable(true);
        et.setFocusable(false);
    }
}
