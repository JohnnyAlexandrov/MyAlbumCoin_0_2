package com.johnny.myalbumcoin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.IOException;

public class MainActivity extends AppCompatActivity{

    ListView lvData;
    DBmain db;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;
    private final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 911;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBmain(this);
        db.open();
        final SearchView searchtextt = findViewById(R.id.menu_search);
        //listen to you change text in searchview, oh shit
        searchtextt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
            findcoin(s); //add find function
            return false;
            }
        }); 
        // получаем курсор
        cursor = db.getAllData();
        startManagingCursor(cursor);
        // формируем столбцы сопоставления
        String[] from = new String[]{DBmain.COLUMN_cphoto, DBmain.COLUMN_cdescr, DBmain.COLUMN_lname};
        int[] to = new int[]{R.id.tvPhoto, R.id.tvCoin, R.id.tvLands};
        // создааем адаптер и настраиваем список
         scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to);
         lvData = (ListView) findViewById(R.id.lvData);
        // photo
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
         scAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder(){
            /** Binds the Cursor column defined by the specified index to the specified view */
            public boolean setViewValue(View view, Cursor cursor, int columnIndex){
                if(view.getId() == R.id.tvPhoto) {
                    final int cphoto = cursor.getColumnIndex("cphoto");
                    final String cphotolink = cursor.getString(cphoto);
                    if (cphotolink != null){
                      //  Uri url = Uri.parse(cphotolink);
                        Bitmap bitmap = null;
                       // Picasso.with(getApplicationContext()).load(R.drawable.ic_launcher_background).into((ImageView) view);
                        try {

                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse("file://"+ cphotolink));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ((ImageView) view).setImageBitmap(bitmap);
                    } else {
                        ((ImageView) view).setImageResource(R.mipmap.ic_launcher_round);}
                    return true;
                }
                return false;
            }
        });
        lvData.setAdapter(scAdapter);
    }
    
    public void findcoin(String s){
        cursor = db.getSearchData(s);
        startManagingCursor(cursor);
    // формируем столбцы сопоставления
        String[] from = new String[]{DBmain.COLUMN_cphoto, DBmain.COLUMN_cdescr, DBmain.COLUMN_lname};
        int[] to = new int[]{R.id.tvPhoto, R.id.tvCoin, R.id.tvLands};
    // создааем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to);
        lvData = (ListView) findViewById(R.id.lvData);
    // photo
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        scAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder(){
        /** Binds the Cursor column defined by the specified index to the specified view */
        public boolean setViewValue(View view, Cursor cursor, int columnIndex){
            if(view.getId() == R.id.tvPhoto) {
                final int cphoto = cursor.getColumnIndex("cphoto");
                final String cphotolink = cursor.getString(cphoto);
                if (cphotolink != null){
                    //  Uri url = Uri.parse(cphotolink);
                    Bitmap bitmap = null;
                    // Picasso.with(getApplicationContext()).load(R.drawable.ic_launcher_background).into((ImageView) view);
                    try {

                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse("file://"+ cphotolink));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ((ImageView) view).setImageBitmap(bitmap);
                } else {
                    ((ImageView) view).setImageResource(R.mipmap.ic_launcher_round);}
                return true;
            }
            return false;
        }
    });
        lvData.setAdapter(scAdapter);
    }

    public void onButtonClickaddcoin(View view){
        db.close();
        Intent intent = new Intent(this, AddActivitycoin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onButtonClickaddland(View view){
        Intent intent = new Intent(this, AddActivityland.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

}
