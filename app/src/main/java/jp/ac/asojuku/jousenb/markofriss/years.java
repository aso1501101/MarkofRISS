package jp.ac.asojuku.jousenb.markofriss;

import java.util.ArrayList;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.id.list;

public class years extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);

        //Listの作成
        ArrayList<String> list = new ArrayList<>();
        // アイテムを追加します
        list.add("hide");
        list.add("ひで");
        list.add("HIDE");

        //Adapterの作成
        //第二引数のレイアウトにandroid付属のレイアウトを指定する
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        // ListViewにAdapterを関連付ける
        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);
    }
}
