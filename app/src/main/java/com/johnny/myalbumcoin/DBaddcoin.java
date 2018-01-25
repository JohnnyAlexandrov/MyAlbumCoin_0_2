package com.johnny.myalbumcoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


///Класс выполнения функций работы с бд для сейрвиса активити
public class DBaddcoin {

    private static final String DB_NAME = "MyalbumcoinDB";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLECOIN = "Myalbumcointable";
    private static final String DB_TABLELANDS = "Myalbumcoinlandstable";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_cdescr = "cdescr";
    public static final String COLUMN_cphoto = "cphoto";
    public static final String COLUMN_clands = "clands";

    public static final String COLUMN_LID = "_id";
    public static final String COLUMN_lname = "lname";





    private final AddActivitycoin mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DBaddcoin(AddActivitycoin ctx) {
        mCtx = ctx;
    }


    // открыть подключение
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }
    public void addRec(String descr, String idcountry, String img) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_cdescr, descr);
        cv.put(COLUMN_clands, idcountry);
        cv.put(COLUMN_cphoto, img);
        mDB.insert(DB_TABLECOIN, null, cv);
    }
    public Cursor getCountry() {
        return mDB.query(DB_TABLELANDS, null, null, null, null, null, null, null);
    }
    // закрыть подключение
    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }


    // класс по созданию и управлению БД
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory,
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