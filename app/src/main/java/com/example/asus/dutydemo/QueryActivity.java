package com.example.asus.dutydemo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QueryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_query_num;
    private Button bt_qurey_signin;
    private Button bt_qurey_signBack;
    private TextView tv_show;
    List<People> peopleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        initView();


    }

    public void finishActivity(View view) {
        finish();
    }

    private void initView() {
        et_query_num = (EditText) findViewById(R.id.et_query_num);
        bt_qurey_signin = (Button) findViewById(R.id.bt_qurey_signin);
        bt_qurey_signBack = (Button) findViewById(R.id.bt_qurey_signBack);
        tv_show = (TextView) findViewById(R.id.tv_show);

        bt_qurey_signin.setOnClickListener(this);
        bt_qurey_signBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_qurey_signin:
                submit();
                break;
            case R.id.bt_qurey_signBack:

                break;
        }
    }

    private void submit() {
        // validate
        String num = et_query_num.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "num不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        DBAdapter adapter = new DBAdapter(QueryActivity.this);
        adapter.openDB();

        Cursor cursor = adapter.cursorById(Integer.valueOf(num));
        while (cursor.moveToNext()) {
            People people = new People();
            people.name = cursor.getString(cursor.getColumnIndex(adapter.keyName));
            people.num = cursor.getInt(cursor.getColumnIndex(adapter.keyNum));
            people.time = cursor.getString(cursor.getColumnIndex(adapter.keyTime));
            tv_show.append(people.toString());
        }

        adapter.closeDB();


    }
}
