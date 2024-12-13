package com.example.w14_lab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// 繼承 SQLiteOpenHelper 類別
public class MyDBHelper extends SQLiteOpenHelper {
    private static final String name = "mdatabase.db"; // 資料庫名稱
    private static final int version = 1; // 資料庫版本

    // 自訂建構子，只需傳入一個 Context 物件即可
    MyDBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 需要加入建立資料表的 SQL 語法
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 需要加入刪除資料表的 SQL 語法
    }
}
