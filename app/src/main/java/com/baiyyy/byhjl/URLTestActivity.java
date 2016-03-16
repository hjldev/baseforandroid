package com.baiyyy.byhjl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by huangjinlong on 2015/12/29.
 */
public class URLTestActivity extends AppCompatActivity {

    private ImageView iv;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urltest);
        iv = (ImageView) findViewById(R.id.iv);
        try {
            URL url = new URL("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1183223528,3058066243&fm=116&gp=0.jpg");
            // 打开url对应的资源输入流
            InputStream is = url.openStream();
            // 从InputStream中解析出Bitmap图片
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(bitmap);
            is.close();
            // 再次打开输入流
            is = url.openStream();
            // 打开手机文件对应的输出流
            OutputStream os = openFileOutput("iv.png", MODE_WORLD_READABLE);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            // 将URL对应的资源下载到本地
            while((hasRead = is.read(buff)) > 0){
                os.write(buff, 0, hasRead);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
