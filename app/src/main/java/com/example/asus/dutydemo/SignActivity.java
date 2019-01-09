package com.example.asus.dutydemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_num;
    private Button bt_commit;
    private List<String> mTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_num = (EditText) findViewById(R.id.et_num);
        bt_commit = (Button) findViewById(R.id.bt_commit);

        bt_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_commit:
                try {
                    submit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void submit() throws InterruptedException {
        final String[] hou = new String[1];
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);

        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        Log.d("shijian", " " + hour);
        if ((hour >= 7 && hour < 8) || (hour >=13 && hour < 14)) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    synchronized (SignActivity.this) {


                        mTimes = new ArrayList<>();


                        DBAdapter adapter = new DBAdapter(SignActivity.this);
                        adapter.openDB();
                        String id = et_num.getText().toString().trim();

                        Cursor cursor = adapter.cursorById(Integer.valueOf(id));
                        // tv_count.setText("将要显示的数据");

                        while (cursor.moveToNext()) {
                            //每次都要new 一个对象  不然只有一个值
                            People people = new People();
                            String time = cursor.getString(cursor.getColumnIndex(adapter.keyTime));
                            // tv_count.append(people.toString());
                            mTimes.add(time);
                        }

                        adapter.closeDB();

                        for (int i = 0; i < mTimes.size(); i++) {

                            String year = mTimes.get(i).substring(0, 4);
                            String month = mTimes.get(i).substring(5, 7);
                            String day = mTimes.get(i).substring(8, 10);
                            hou[0] = mTimes.get(i).substring(11, 13);
                            String min = mTimes.get(i).substring(14, 16);
                            String se = mTimes.get(i).substring(17, 19);
                            Log.d("timetimetime", year + " " + month + " " + day + " " + hou[0] + " " + min + " " + se);
                        }
                        SignActivity.this.notify();
                    }
                }
            }).start();
            synchronized (SignActivity.this) {
                SignActivity.this.wait();
                for (int i = 0; i < mTimes.size(); i++) {
                    if (Integer.valueOf(hou[i]) >= 7 && Integer.valueOf(hou[i]) < 8) {
                        Toast.makeText(SignActivity.this, "你已经签到过了", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                DBAdapter adapter = new DBAdapter(SignActivity.this);
                adapter.openDB();
                String name = et_name.getText().toString().trim();
                String num = et_num.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(num)) {
                    Toast.makeText(this, "姓名或工号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean b = false;

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = dateFormat.format(new Date());
                Log.d("timetime", time);


                // Log.d("timetime", year + " " + month + " " + date + " " + hour + " " + minute + " " + second);

                b = adapter.insert(name, Integer.valueOf(num), time);

                if (b) {
                    Toast.makeText(SignActivity.this, "签到成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignActivity.this, "签到失败", Toast.LENGTH_SHORT).show();
                }
                adapter.closeDB();
            }
        } else {
            Toast.makeText(SignActivity.this, "现在不是签到时间", Toast.LENGTH_SHORT).show();
        }
    }
}
