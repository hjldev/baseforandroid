package com.baiyyy.byhjl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private ProgressBar pb;
    private SeekBar sb;
    private RatingBar rb;
    private ListView lv;
    private ExpandableListView elv;

    private GridView gv;
    private ImageSwitcher is;

    private String[] books = new String[]{
      "aaaa","aaabb", "aaaaccc"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, books
        );
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.auto_tv);
        actv.setAdapter(aa);

        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setProgress(count);

        new Thread(){
            @Override
            public void run() {
                while (count <= 100){
                    count++;
                    Message msg = new Message();
                    msg.what = 0x111;
                    msg.arg1 = count;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();

        sb = (SeekBar) findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha=(float)progress/100;
                if (lp.alpha<0.2f){
                    lp.alpha = 0.2f;
                }
                getWindow().setAttributes(lp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rb = (RatingBar) findViewById(R.id.rb);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha=rating/5;
                if (lp.alpha<0.2f){
                    lp.alpha = 0.2f;
                }
                getWindow().setAttributes(lp);
            }
        });

        lv = (ListView) findViewById(R.id.lv);
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < books.length; i++){
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("name", books[i]);
            listItem.put("image", R.mipmap.ic_launcher);
            listItems.add(listItem);
        }
        SimpleAdapter sa = new SimpleAdapter(MainActivity.this, listItems, android.R.layout.activity_list_item, new String[]{"name", "image"}, new int[]{android.R.id.text1, android.R.id.icon});
        lv.setAdapter(sa);


        ExpandableListAdapter expandableListAdapter = new BaseExpandableListAdapter(){

            private String[] armTypes = new String[]{
                    "神族兵种", "虫族兵种", "人 族兵种"
            };
            private String[][] arms = new String[][]{
                    {"狂战士", "龙骑士", "黑暗圣堂", "点兵"},
                    {"狂战士", "龙骑士", "黑暗圣堂", "点兵"},
                    {"狂战士", "龙骑士", "黑暗圣堂", "点兵"}
            };

            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                ImageView logo = new ImageView(MainActivity.this);
                logo.setImageResource(R.mipmap.ic_launcher);
//                ll.addView(logo);
                TextView tv = new TextView(MainActivity.this);
                tv.setPadding(40,0,0,0);
                tv.setText(getGroup(groupPosition).toString());
                ll.addView(tv);
                return ll;
            }

            @Override
            public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText(getChild(groupPosition, childPosition).toString());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, childPosition + "", Toast.LENGTH_SHORT).show();
                    }
                });
                return tv;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };

        elv = (ExpandableListView) findViewById(R.id.elv);
        elv.setAdapter(expandableListAdapter);

        gv = (GridView) findViewById(R.id.gv);
        List<Map<String, Object>> gvs = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            Map<String, Object> gv = new HashMap<>();
            gv.put("image", R.mipmap.ic_launcher);
            gvs.add(gv);
        }
        SimpleAdapter saGv = new SimpleAdapter(MainActivity.this, gvs, R.layout.simple_grideview_item, new String[]{"image"}, new int[]{R.id.simple_iv});
        gv.setAdapter(saGv);

        is = (ImageSwitcher) findViewById(R.id.is);
        is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        is.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(MainActivity.this);
                iv.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.WRAP_CONTENT, ImageSwitcher.LayoutParams.WRAP_CONTENT));
                return iv;
            }
        });

        gv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                is.setImageResource(R.mipmap.ic_launcher);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                is.setImageResource(R.mipmap.ic_launcher);
            }
        });

    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x111){
                pb.setProgress(msg.arg1);
                if (msg.arg1 == 100){
                    Toast.makeText(MainActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        count = 101;
        super.onDestroy();
    }
}
