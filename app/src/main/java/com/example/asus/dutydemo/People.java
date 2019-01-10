package com.example.asus.dutydemo;

public class People {
    public String name;
    public int num;
    public int id;
    public String time;

    @Override
    public String toString() {
        return
                "姓名：'" + name + '\'' +
                ",工号：" + num +
                ", 时间：'" + time + '\'' +
                "\n";
    }
}
