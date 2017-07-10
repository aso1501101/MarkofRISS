package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class jenre_toukei extends AppCompatActivity {

    private static final long serialVersionUID = 1L;

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

        Intent intent = getIntent();

        dbm2 = new DBManager2(this);
        sqlDB = dbm2.getWritableDatabase();

        Genremodel model = new Genremodel();
        model = dbm2.genre1(sqlDB);
        String seikai = String.valueOf(model.get_Seikai());
        String count = String.valueOf(model.get_Count());
        TextView tv = (TextView) findViewById(R.id.seikai_1);
        tv.setText(seikai);
        TextView tv2 = (TextView) findViewById(R.id.kaitou_1);
        tv2.setText(count);
        int x = Integer.parseInt(seikai);
        int y = Integer.parseInt(count);
        int res = (x * 100) / y;
        String wariai = String.valueOf(res)+"%";
        TextView tv3 = (TextView) findViewById(R.id.wariai_1);
        tv3.setText(wariai);

        model = dbm2.genre2(sqlDB);
        seikai = String.valueOf(model.get_Seikai());
        count = String.valueOf(model.get_Count());
        tv = (TextView) findViewById(R.id.seikai_2);
        tv2 = (TextView) findViewById(R.id.kaitou_2);
        tv.setText(seikai);
        tv2.setText(count);
        x = Integer.parseInt(seikai);
        y = Integer.parseInt(count);
        res = (x * 100) / y;
        wariai = String.valueOf(res)+"%";
        tv3 = (TextView) findViewById(R.id.wariai_2);
        tv3.setText(wariai);

        model = dbm2.genre3(sqlDB);
        seikai = String.valueOf(model.get_Seikai());
        count = String.valueOf(model.get_Count());
        tv = (TextView) findViewById(R.id.seikai_3);
        tv2 = (TextView) findViewById(R.id.kaitou_3);
        tv.setText(seikai);
        tv2.setText(count);
        x = Integer.parseInt(seikai);
        y = Integer.parseInt(count);
        res = (x * 100) / y;
        wariai = String.valueOf(res)+"%";
        tv3 = (TextView) findViewById(R.id.wariai_3);
        tv3.setText(wariai);

        model = dbm2.genre4(sqlDB);
        seikai = String.valueOf(model.get_Seikai());
        count = String.valueOf(model.get_Count());
        tv = (TextView) findViewById(R.id.seikai_4);
        tv2 = (TextView) findViewById(R.id.kaitou_4);
        tv.setText(seikai);
        tv2.setText(count);
        x = Integer.parseInt(seikai);
        y = Integer.parseInt(count);
        res = (x * 100) / y;
        wariai = String.valueOf(res)+"%";
        tv3 = (TextView) findViewById(R.id.wariai_4);
        tv3.setText(wariai);

        model = dbm2.genre5(sqlDB);
        seikai = String.valueOf(model.get_Seikai());
        count = String.valueOf(model.get_Count());
        tv = (TextView) findViewById(R.id.seikai_5);
        tv2 = (TextView) findViewById(R.id.kaitou_5);
        tv.setText(seikai);
        tv2.setText(count);
        x = Integer.parseInt(seikai);
        y = Integer.parseInt(count);
        res = (x * 100) / y;
        wariai = String.valueOf(res)+"%";
        tv3 = (TextView) findViewById(R.id.wariai_5);
        tv3.setText(wariai);

        model = dbm2.genre6(sqlDB);
        seikai = String.valueOf(model.get_Seikai());
        count = String.valueOf(model.get_Count());
        tv = (TextView) findViewById(R.id.seikai_6);
        tv2 = (TextView) findViewById(R.id.kaitou_6);
        tv.setText(seikai);
        tv2.setText(count);
        x = Integer.parseInt(seikai);
        y = Integer.parseInt(count);
        res = (x * 100) / y;
        wariai = String.valueOf(res)+"%";
        tv3 = (TextView) findViewById(R.id.wariai_6);
        tv3.setText(wariai);

        model = dbm2.genre7(sqlDB);
        seikai = String.valueOf(model.get_Seikai());
        count = String.valueOf(model.get_Count());
        tv = (TextView) findViewById(R.id.seikai_7);
        tv2 = (TextView) findViewById(R.id.kaitou_7);
        tv.setText(seikai);
        tv2.setText(count);
        x = Integer.parseInt(seikai);
        y = Integer.parseInt(count);
        res = (x * 100) / y;
        wariai = String.valueOf(res)+"%";
        tv3 = (TextView) findViewById(R.id.wariai_7);
        tv3.setText(wariai);

        model = dbm2.genre8(sqlDB);
        seikai = String.valueOf(model.get_Seikai());
        count = String.valueOf(model.get_Count());
        tv = (TextView) findViewById(R.id.seikai_8);
        tv2 = (TextView) findViewById(R.id.kaitou_8);
        tv.setText(seikai);
        tv2.setText(count);
        x = Integer.parseInt(seikai);
        y = Integer.parseInt(count);
        res = (x * 100) / y;
        wariai = String.valueOf(res)+"%";
        tv3 = (TextView) findViewById(R.id.wariai_8);
        tv3.setText(wariai);

    }
}



