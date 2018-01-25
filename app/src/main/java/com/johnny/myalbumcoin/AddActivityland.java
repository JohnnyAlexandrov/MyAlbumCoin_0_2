package com.johnny.myalbumcoin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Администратор on 10.01.2018.
 */

public class AddActivityland extends AppCompatActivity {

    private EditText Descr;
    DBaddland db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addland);
        db = new DBaddland(this);
        db.open();
    }

    public void onButtonClickSave(View view) {
        Descr = findViewById(R.id.NameLands);
        String descr = Descr.getText().toString();
        db.addRec(descr);
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
    }
}
