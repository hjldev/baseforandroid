package com.baiyyy.byhjl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by huangjinlong on 2015/12/24.
 */
public class FileInputOutputActivity extends AppCompatActivity implements View.OnClickListener{
    private String FILESTORE = "byhjl";
    private String input;
    private String output;
    private EditText input_et;
    private EditText output_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_store);
        input_et = (EditText) findViewById(R.id.input_et);
        input = input_et.getText().toString().trim();
        output_et = (EditText) findViewById(R.id.output_et);
        output = output_et.getText().toString().trim();
        findViewById(R.id.input_btn).setOnClickListener(this);
        findViewById(R.id.out_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.input_btn:
                write(input);
                input_et.setText("");
                break;
            case R.id.out_btn:
                output_et.setText(read());
                break;
        }
    }

    private String read() {
        try {
            // 打开文件输入流
            FileInputStream fis = openFileInput(FILESTORE);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            try {
                while ((hasRead = fis.read(buff)) > 0){
                    sb.append(new String(buff, 0, hasRead));
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String content) {
        try {
            // 以追加模式打开文件输出流
            FileOutputStream fos = openFileOutput(FILESTORE, MODE_APPEND);
            // 将FileOutStream包装成PrintStream
            PrintStream ps = new PrintStream(fos);
            // 输出文件内容
            ps.print(content);
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
