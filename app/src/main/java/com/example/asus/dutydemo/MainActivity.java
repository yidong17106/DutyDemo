package com.example.asus.dutydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button bt_time;
    private Button bt_people;
    private Button bt_sign_in;
    private Button bt_sign_back;
    private Button bt_qurey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        bt_time = (Button) findViewById(R.id.bt_time);
        bt_people = (Button) findViewById(R.id.bt_people);
        bt_sign_in = (Button) findViewById(R.id.bt_sign_in);
        bt_sign_back = (Button) findViewById(R.id.bt_sign_back);
        bt_qurey = (Button) findViewById(R.id.bt_qurey);

        bt_time.setOnClickListener(this);
        bt_people.setOnClickListener(this);
        bt_sign_in.setOnClickListener(this);
        bt_sign_back.setOnClickListener(this);
        bt_qurey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_time:
                startActivity(new Intent(MainActivity.this,NoticeActivity.class));
                break;
            case R.id.bt_people:
                startActivity(new Intent(MainActivity.this,PeopleInfoActivity.class));
                break;
            case R.id.bt_sign_in:
                startActivity(new Intent(MainActivity.this, SignActivity.class));
                break;
            case R.id.bt_sign_back:
                 startActivity(new Intent(MainActivity.this,SignBackActivity.class));
                break;
            case R.id.bt_qurey:
                startActivity(new Intent(MainActivity.this, QueryActivity.class));
                break;
        }
    }
}
