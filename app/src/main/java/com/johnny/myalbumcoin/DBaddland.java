package com.johnny.myalbumcoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Администратор on 23.01.2018.
 */

public class DBaddland {

    private static final String DB_NAME = "MyalbumcoinDB";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLELANDS = "Myalbumcoinlandstable";

    public static final String COLUMN_LID = "_id";
    public static final String COLUMN_lname = "lname";


    private final AddActivityland mCtx;

    private DBaddland.DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DBaddland(AddActivityland ctx) {
        mCtx = ctx;
    }


    // открыть подключение
    public void open() {
        mDBHelper = new DBaddland.DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }
    public void addRec(String descr) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_lname, descr);
        mDB.insert(DB_TABLELANDS, null, cv);
    }

    // закрыть подключение
    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }


    // класс по созданию и управлению БД
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // создаем и заполняем БД
        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
