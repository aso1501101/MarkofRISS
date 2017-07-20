package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
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

    int selectedID = -1;
    int lastPosiotion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        final Intent intent = getIntent();
        final String counts = intent.getStringExtra("counts");
         String counta = intent.getStringExtra("count");

        final String year = intent.getStringExtra("year");
        final String season = intent.getStringExtra("season");

        int countse = Integer.parseInt(counta);
        countse = countse - 1;
        counta = String.valueOf(countse);
        final String countfinal = String.valueOf(countse+1);

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        TextView tv = (TextView) findViewById(R.id.textViewexact);
        tv.setText(counts);

        TextView tv2 = (TextView) findViewById(R.id.textViewall);
        tv2.setText(counta);

        Button btntop = (Button) this.findViewById(R.id.btntop);
        btntop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbm.goodbyuema(sqlDB);
                Intent intent = new Intent(result.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ListView listMiss = null;
        //flgがうんこだったらリストヴューつかわない
        String flg = "";
        flg = intent.getStringExtra("flg");
        if ("unk".equals(flg)) {

        } else {
            //リストビュー
            listMiss = (ListView) findViewById(R.id.list_miss);
        /*listMiss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
            setValueToList(listMiss, year, season);

            listMiss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);

                    selectedID = cursor.getInt(cursor.getColumnIndex("_id"));
                    String uemamozi = String.valueOf(selectedID);
                    uemamozi = dbm.whatuema(sqlDB, uemamozi);

                    Intent intent = new Intent(result.this, question_uema.class);

                    intent.putExtra("uema", uemamozi);
                    intent.putExtra("count", countfinal);
                    intent.putExtra("counts", counts);

                    startActivity(intent);
                }

            });
        }
    }

    //リスト表示用
    private void setValueToList(ListView list, String year, String season) {

        Intent intent = getIntent();

        SQLiteCursor cursor = null;

        //データベース空間オープン
        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        //DBManager.javaで定義したメソッドを呼び出し
        //cursor = dbm.miss(sqlDB, year, season);
        cursor = dbm.missuema(sqlDB);

        //dblayout : リストビューの表示形式を指定する
        int dblayout = android.R.layout.simple_list_item_2;
        //from : リストビューに表示する列
        String[] from = {"mondai_id"};
        //to : ListViewのどこにデータを表示するか
        int[] to = new int[]{android.R.id.text1};

        //ListViewに表示するためのアダプタを生成
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, dblayout, cursor, from, to, 0);
        //アダプタをListViewにセット
        list.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sqlDB.close();
    }
}
