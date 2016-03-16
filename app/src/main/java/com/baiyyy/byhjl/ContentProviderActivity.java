package com.baiyyy.byhjl;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjinlong on 2015/12/25.
 */
public class ContentProviderActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        initData();
        addData();
    }

    private void addData() {
        final EditText name_et = (EditText) findViewById(R.id.name_et);
        final EditText phone_et = (EditText) findViewById(R.id.phone_et);
        final EditText email_et = (EditText) findViewById(R.id.email_et);
        Button add_btn = (Button) findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_et.getText().toString();
                String phone = phone_et.getText().toString();
                String email = email_et.getText().toString();
                // 创建一个空的ContentValues， 用于添加到数据库中
                ContentValues contentValues = new ContentValues();
                // 向RawContacts.CONTENT_URL执行一个空值插入，目的是获取系统返回的rawContactId
                Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
                long rawContactId = ContentUris.parseId(rawContactUri);
                contentValues.clear();
                contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                // 设置内容类型
                contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                // 设置联系人姓名
                contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
                // 向联系人Uri添加联系人名字
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contentValues);
                contentValues.clear();
                contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                // 设置联系人的电话号码
                contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
                // 设置电话类型
                contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                // 想联系人电话号码Uri添加电话号码
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contentValues);
                contentValues.clear();
                contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
                // 设置联系人的E-mail地址
                contentValues.put(ContactsContract.CommonDataKinds.Email.DATA, email);
                // 设置该电子邮件的类型
                contentValues.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
                // 向联系人E-mail的Uri添加E-mail数据
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contentValues);
                Toast.makeText(ContentProviderActivity.this, "联系人数据添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        // 定义两个List来封装系统的联系人信息、指定联系人的电话号码、Email等详情
        final ArrayList<String> names =  new ArrayList<>();
        final ArrayList<ArrayList<String>> details = new ArrayList<>();
        // 使用ContentResolver查找联系人数据
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        // 遍历查询结果，获取系统中所有联系人
        while (cursor.moveToNext()){
            // 获取联系人ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // 获取联系人的姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            names.add(name);
            // 使用ContentResolver查找联系人的电话号码
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            ArrayList<String> detail = new ArrayList<>();
            // 遍历查询结果，获取该联系人的多个电话号码
            while (phones.moveToNext()){
                // 获取查询结果中电话号码列中的数据
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                detail.add("电话号码：" + phoneNumber);
            }
            phones.close();
            Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId, null, null);
            while (emails.moveToNext()){
                String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                detail.add("邮箱：" + emailAddress);
            }
            emails.close();
            details.add(detail);
        }
        cursor.close();

        // 加载视图，展示数据
        ExpandableListView list = (ExpandableListView) findViewById(R.id.list);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return names.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return details.get(groupPosition).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return names.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return details.get(groupPosition).get(childPosition);
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

            private TextView getTextView(){
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(ContentProviderActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setTextSize(20);
                textView.setPadding(36, 0, 0 , 0);
                return textView;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                return textView;

            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        list.setAdapter(adapter);
    }
}
