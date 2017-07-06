package jp.ac.asojuku.jousenb.markofriss;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class years extends AppCompatActivity {
    private SQLiteDatabase sqlDB;
    DBManager2 dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        setContentView(R.layout.activity_years);

        final ListView listView = (ListView) findViewById(R.id.listview1);
        //(;_;)
        //Listの作成
        final ArrayList<String> items = new ArrayList<>();
        // データを準備
        items.add("29年度春期試験");

        //２９ｓ０１　　　リストに２５いれる

        items.add("28年度秋期試験");
        items.add("28年度春期試験");
        items.add("27年度秋期試験");
        items.add("27年度春期試験");
        items.add("26年度秋期試験");
        items.add("26年度春期試験");
        items.add("25年度秋期試験");
        items.add("25年度春期試験");

        //Adapterの作成
        //第二引数のレイアウトにandroid付属のレイアウトを指定する
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        // ListViewに表示
        listView.setAdapter(adapter);

        //タッチしたとこのデータが送られる
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView list = (ListView)parent;
                String item = (String)listView.getItemAtPosition(position);

                // 通知ダイアログを表示
                Toast.makeText(years.this,
                        item, Toast.LENGTH_LONG
                ).show();


                //String hitokoto  = dbm.selectHitokotoRandom2(sqlDB);

                //Log.v("取得データ", hitokoto);

                Intent intent = new Intent(years.this, question.class);
                intent.putExtra("count" , item);
                startActivity(intent);
            }
        });
    }
}
