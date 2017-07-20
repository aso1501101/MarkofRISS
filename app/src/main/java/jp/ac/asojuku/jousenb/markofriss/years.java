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

        dbm.goodbyuema(sqlDB);

        setContentView(R.layout.activity_years);

        final ListView listView = (ListView) findViewById(R.id.listview1);
        //(;_;)
        //Listの作成
        ArrayList<String> items = new ArrayList<>();
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
        items.add("24年度秋期試験");
        items.add("24年度春期試験");

        //Adapterの作成
        //第二引数のレイアウトにandroid付属のレイアウトを指定する
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        // ListViewに表示
        listView.setAdapter(adapter);

        //タッチしたとこのデータを取得
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView list = (ListView)parent;
                String item = (String)listView.getItemAtPosition(position);

                if (item.equals("29年度春期試験")) {
                    // 通知ダイアログを表示
                    Toast.makeText(years.this,
                            item, Toast.LENGTH_LONG
                    ).show();
                    String year = item.substring(0,2);
                    String season = item.substring(4,5);

                    Intent intent = new Intent(years.this, question.class);
                    intent.putExtra("year" , year);

                    if(season.equals("秋")){
                        season = "f";
                    }else {
                        season = "s";
                    }
                    intent.putExtra("season" , season);

                    intent.putExtra("count" , "1");
                    intent.putExtra("counts" , "0");
                    startActivity(intent);
                }else if(item.equals("28年度秋期試験")){
                    // 通知ダイアログを表示
                    Toast.makeText(years.this,
                            item, Toast.LENGTH_LONG
                    ).show();
                    String year = item.substring(0,2);
                    String season = item.substring(4,5);

                    Intent intent = new Intent(years.this, question.class);
                    intent.putExtra("year" , year);

                    if(season.equals("秋")){
                        season = "f";
                    }else {
                        season = "s";
                    }
                    intent.putExtra("season" , season);

                    intent.putExtra("count" , "1");
                    intent.putExtra("counts" , "0");
                    startActivity(intent);
                }else {
                    item="Coming soon...";
                    Toast.makeText(years.this,
                            item, Toast.LENGTH_LONG
                    ).show();
                }


            }
        });
    }
}
