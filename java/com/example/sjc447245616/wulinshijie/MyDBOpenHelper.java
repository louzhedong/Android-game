package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/12/12.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context, String name, CursorFactory factory,
                          int version)
    {
        super(context, "my.db", null, 1);
    }

    @Override
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(id VARCHAR(20) PRIMARY KEY, name VARCHAR(20), level INTEGER, money INTEGER, jingyanhave INTEGER, jingyanneed INTEGER, allattack INTEGER, allprotect INTEGER, allblood INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS hero(id VARCHAR(20) PRIMARY KEY,level INTEGER, jingyanhave INTEGER, jingyanneed INTEGER, place INTEGER, gzid INTEGER, fzid INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS thing(id VARCHAR(20) PRIMARY KEY, number INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS clothes(id VARCHAR(20) PRIMARY KEY, number INTEGER, place VARCHAR(20),type INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS flag(id INTEGER PRIMARY KEY)");
    }


    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
    }
}