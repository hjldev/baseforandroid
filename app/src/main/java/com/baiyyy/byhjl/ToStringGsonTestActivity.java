package com.baiyyy.byhjl;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjinlong on 2016/2/16.
 */
public class ToStringGsonTestActivity extends AppCompatActivity{
    private List<String> history;
    private EditText et;
    private ListView lv;
    private Button btn;
    private ArrayAdapter adapter;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_string_gson_test);
        gson = new Gson();
        // 创建history表
        sp = getSharedPreferences("history", MODE_PRIVATE);
        editor = sp.edit();
        et = (EditText) findViewById(R.id.et);
        lv = (ListView) findViewById(R.id.lv);
        btn = (Button) findViewById(R.id.btn);
        // 得到表中history字段的String字符串
        String list = sp.getString("history", "");
        if (list != null && !list.isEmpty()){
            // 如果不为空，解析字符串为List
            history = gson.fromJson(list, new TypeToken<List<String>>(){}.getType());
        } else {
            history = new ArrayList<>();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history.add(0, et.getText().toString());
                // 将List对象保存为json对象（具体细节由json处理）并保存为history字段的String
                editor.putString("history", gson.toJson(history));
                editor.apply();
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, history);
        lv.setAdapter(adapter);
    }
}
