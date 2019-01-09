package com.example.asus.dutydemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBAdapter {

    public static String DB_NAME = "person.db";
    private static String DB_TABLE = "personTable";
    private static int VERSION = 1;

    public static String keyId = "_id";
    public static String keyName = "name";
    public static String keyNum = "num";
    public static String keyTime = "time";
    public static Context mContext;
    public SQLiteDatabase mDatabase;

    public DBAdapter(Context context) {
        mContext = context;
    }

    private static class DBadapterHelper extends SQLiteOpenHelper {

        String sql = " create table " + DB_TABLE + "(" + keyId + " integer primary key autoincrement, " + keyName + " text, " + keyNum + " integer, " + keyTime + " text " + ")";

        public DBadapterHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void openDB() {
        DBadapterHelper dBadapterHelper = new DBadapterHelper(mContext, DB_NAME, null, VERSION);
        try {
            mDatabase = dBadapterHelper.getWritableDatabase();
        } catch (Exception e) {
            mDatabase = dBadapterHelper.getReadableDatabase();
        }


    }

    public void closeDB() {
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }
    }

    //增

    /**
     * 只插入数据即可 id是自增长  不用插入id
     *
     * @param name
     * @param num
     * @return
     */
    public boolean insert(String name, int num, String time) {
        Cursor cursor = null;
        boolean isOk = false;
        try {
            cursor = mDatabase.query(DB_TABLE, new String[]{keyName}, keyName + "=" + name, null, null, null, null);
            if (cursor.moveToNext()) {
                return false;
            }
        } catch (Exception e) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(keyName, name);
            contentValues.put(keyNum, num);
            contentValues.put(keyTime, time);

            long l = mDatabase.insert(DB_TABLE, null, contentValues);
            if (l != -1) {
                isOk = true;
                return isOk;
            }

        }

        return isOk;
    }


    //删
    public boolean deleteById(int id) {
        boolean isOk = false;
        int i = mDatabase.delete(DB_TABLE, keyId + "=" + String.valueOf(id), null);
        if (i == 1) {
            isOk = true;
            return isOk;
        }
        return isOk;
    }

    public boolean deleteAll() {
        boolean isOk = false;
        int i = mDatabase.delete(DB_TABLE, null, null);
        if (i == 1) {
            isOk = true;
            return isOk;
        }
        return isOk;
    }

    //改
    public boolean update(int id, String name, int age) {
        boolean isOk = false;

        ContentValues contentValues = new ContentValues();
        contentValues.put(keyName, name);
        contentValues.put(keyNum, age);

        int i = mDatabase.update(DB_TABLE, contentValues, keyId + "=" + String.valueOf(id), null);
        if (i == 1) {
            isOk = true;
            return isOk;
        }

        return isOk;
    }


    //查

    public Cursor cursorById(int id) {
        Cursor cursor = null;
        cursor = mDatabase.query(DB_TABLE, new String[]{keyId, keyName, keyNum,keyTime}, keyNum + "=" + String.valueOf(id), null, null, null, null);

        return cursor;
    }

    public Cursor cursorAll() {
        Cursor cursor = null;
        cursor = mDatabase.query(DB_TABLE, new String[]{keyId, keyName, keyNum,keyTime}, null, null, null, null, null);
        return cursor;
    }

}
