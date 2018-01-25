package com.johnny.myalbumcoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


///Класс выполнения функций работы с бд для сейрвиса активити
public class DBmain {

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



    private static final String DB_CREATECOIN =
            "create table " + DB_TABLECOIN + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_cdescr + " text, " +
                    COLUMN_cphoto + " text, " +
                    COLUMN_clands + " integer " +
                    ");";
    private static final String DB_CREATELANDS =
            "create table " + DB_TABLELANDS + "(" +
                    COLUMN_LID + " integer primary key autoincrement, " +
                    COLUMN_lname + " text " +
                    ");";

    private final MainActivity mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DBmain(MainActivity ctx) {
        mCtx = ctx;
    }


    // открыть подключение
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    // закрыть подключение
    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }

    // получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        String table = "Myalbumcointable as C inner join Myalbumcoinlandstable as L on C.clands = L._id Order by C._id Desc ";
        return mDB.query(table, null, null, null, null, null, null, null);
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
            db.execSQL(DB_CREATECOIN);
            db.execSQL(DB_CREATELANDS);
          //  ContentValues cv = new ContentValues();
           // cv.put(COLUMN_cdescr, "First coin this is my revenge circle of death");
            //cv.put(COLUMN_cphoto, "https://www.1shilling.ru/wp-content/uploads/2014/12/01.jpg");
            //cv.put(COLUMN_clands, "1");
            //db.insert(DB_TABLECOIN, null, cv);
                ContentValues cvv = new ContentValues();
                cvv.put(COLUMN_lname, "Россия");
                db.insert(DB_TABLELANDS, null, cvv);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}