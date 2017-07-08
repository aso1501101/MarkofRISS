package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class jenre_toukei extends AppCompatActivity {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm2;
    String kaitou1 = "";
    String seikai = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenre_toukei);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_jenre_toukei);

        Intent intent = getIntent();


    }
}



