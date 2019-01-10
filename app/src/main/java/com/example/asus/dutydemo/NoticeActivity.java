package com.example.asus.dutydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NoticeActivity extends AppCompatActivity {

    private View Btn_notice_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Btn_notice_return =findViewById(R.id.btn_notice_return);
        Btn_notice_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoticeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
