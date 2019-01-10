package com.example.asus.dutydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PeopleInfoActivity extends AppCompatActivity {

    private View Btn_people_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_info);
        Btn_people_return =findViewById(R.id.btn_people_return);

        Btn_people_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PeopleInfoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
