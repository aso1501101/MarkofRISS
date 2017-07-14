package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

public class result extends AppCompatActivity
    implements AdapterView.OnItemClickListener {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
       /* LinearLayout cardLinear = (LinearLayout)this.findViewById(R.id.cardLinear);
        cardLinear.removeAllViews();

        for (int i = 0; i < 5; i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.test_card,null);
                CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
                TextView textBox = (TextView) linearLayout.findViewById(R.id.textBox);
                textBox.setText("CardView" + i);
                cardView.setTag(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(result.this, String.valueOf(v.getTag()) + "番目のCardViewがクリックされました。", Toast.LENGTH_SHORT).show();
                    }
                });
                cardLinear.addView(linearLayout,i);
        } */
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        String counts = "";
        String counta = "";

        Intent intent = getIntent();
        counts = intent.getStringExtra("counts");
        counta = intent.getStringExtra("count");

        int countse = Integer.parseInt(counta);
        countse = countse - 1;
        counta = String.valueOf(countse);


        TextView tv = (TextView)findViewById(R.id.textViewexact);
        tv.setText(counts);

        TextView tv2 = (TextView)findViewById(R.id.textViewall);
        tv2.setText(counta);

        Button btntop = (Button)this.findViewById(R.id.btntop);
        btntop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(result.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //リストビュー
        ListView listMiss = (ListView)findViewById(R.id.listmiss);
        listMiss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        setValueToList(listMiss);
    }

    //リスト表示用
    private void setValueToList(ListView list) {

        Intent intent = getIntent();
        final String year = intent.getStringExtra("year");
        final String season = intent.getStringExtra("season");

        SQLiteCursor cursor = null;

        //データベース空間オープン
        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        //DBManager.javaで定義したメソッドを呼び出し
        cursor = dbm.miss(sqlDB,year,season);

        //dblayout : リストビューの表示形式を指定する
        int dblayout = android.R.layout.simple_list_item_1;
        //from : リストビューに表示する列
        String[] from = { "no"};
        //to : ListViewのどこにデータを表示するか
        int[] to = new int[]{android.R.id.text1};

        //ListViewに表示するためのアダプタを生成
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, dblayout, cursor,from,to,0);
        //アダプタをListViewにセット
        list.setAdapter(adapter);
    }
}
