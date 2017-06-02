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

        //Listの作成
        ArrayList<String> items = new ArrayList<>();
        // データを準備
        items.add("hide");
        items.add("ひで");
        items.add("HIDE");

        //Adapterの作成
        //第二引数のレイアウトにandroid付属のレイアウトを指定する
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        // ListViewに表示
        listView.setAdapter(adapter);
    }
}
