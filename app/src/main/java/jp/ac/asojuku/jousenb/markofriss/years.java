package jp.ac.asojuku.jousenb.markofriss;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class years extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);

        ListView listView = (ListView) findViewById(R.id.listview1);
        //(;_;)
        //Listの作成
        ArrayList<String> items = new ArrayList<>();
        // データを準備
        items.add("29年度春期試験");
        items.add("28年度秋期試験");
        items.add("28年度春期試験");
        items.add("27年度秋期試験");
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
    }
}
