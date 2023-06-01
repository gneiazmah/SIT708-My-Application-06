package com.example.sit708_my_application_01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {

    public DBConnector(Context context){
        super(context,"DB_ITUBE",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table userInfo (UserID VARCHAR PRIMARY KEY NOT NULL, FullName VARCHAR, Password VARCHAR)");
        sqLiteDatabase.execSQL("create table PlayList (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
