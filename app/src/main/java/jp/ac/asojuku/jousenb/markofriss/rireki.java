package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.list;

public class rireki extends AppCompatActivity {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rireki);
    }
    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String hitokoto = intent.getStringExtra("hitokoto");

       TextView tv = (TextView)findViewById(R.id.textViewhitokoto);
        tv.setText(hitokoto);

/*

        ListView list = (ListView)findViewById(R.id.list);

        SQLiteCursor cursor = null;

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        cursor = dbm.selectHitokotoList(sqlDB);

        int dblayout = android.R.layout.simple_list_item_1;

        String[] from = {"genre_id"};

        int[] to = new int[]{android.R.id.text1};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,dblayout,cursor,from,to,0);

        list.setAdapter(adapter);*/
    }
}
