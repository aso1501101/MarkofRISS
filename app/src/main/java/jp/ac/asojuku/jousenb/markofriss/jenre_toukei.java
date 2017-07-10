package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

        Intent intent = getIntent();

        dbm2 = new DBManager2(this);
        sqlDB = dbm2.getWritableDatabase();

        Genremodel model = new Genremodel();
        model = dbm2.genre1(sqlDB);

        TextView tv = (TextView)findViewById(R.id.seikai_1);
        tv.setText(model.get_Seikai());
        TextView tv2 = (TextView)findViewById(R.id.kaitou_1);
        tv2.setText(model.get_Count());
        int res = model.get_Seikai() / model.get_Count() *100;

        model = dbm2.genre2(sqlDB);
        tv = (TextView)findViewById(R.id.seikai_2);
        tv.setText(model.get_Seikai());
        tv2 = (TextView)findViewById(R.id.kaitou_2);
        tv2.setText(model.get_Count());
        res = model.get_Seikai() / model.get_Count() *100;

        model = dbm2.genre3(sqlDB);
        tv = (TextView)findViewById(R.id.seikai_3);
        tv.setText(model.get_Seikai());
        tv2 = (TextView)findViewById(R.id.kaitou_3);
        tv2.setText(model.get_Count());
        res = model.get_Seikai() / model.get_Count() *100;

        model = dbm2.genre4(sqlDB);
        tv = (TextView)findViewById(R.id.seikai_4);
        tv.setText(model.get_Seikai());
        tv2 = (TextView)findViewById(R.id.kaitou_4);
        tv2.setText(model.get_Count());
        res = model.get_Seikai() / model.get_Count() *100;

        model = dbm2.genre5(sqlDB);
        tv = (TextView)findViewById(R.id.seikai_5);
        tv.setText(model.get_Seikai());
        tv2 = (TextView)findViewById(R.id.kaitou_5);
        tv2.setText(model.get_Count());
        res = model.get_Seikai() / model.get_Count() *100;

        model = dbm2.genre6(sqlDB);
        tv = (TextView)findViewById(R.id.seikai_6);
        tv.setText(model.get_Seikai());
        tv2 = (TextView)findViewById(R.id.kaitou_6);
        tv2.setText(model.get_Count());
        res = model.get_Seikai() / model.get_Count() *100;

        model = dbm2.genre7(sqlDB);
        tv = (TextView)findViewById(R.id.seikai_7);
        tv.setText(model.get_Seikai());
        tv2 = (TextView)findViewById(R.id.kaitou_7);
        tv2.setText(model.get_Count());
        res = model.get_Seikai() / model.get_Count() *100;

        model = dbm2.genre8(sqlDB);
        tv = (TextView)findViewById(R.id.seikai_8);
        tv.setText(model.get_Seikai());
        tv2 = (TextView)findViewById(R.id.kaitou_8);
        tv2.setText(model.get_Count());
        res = model.get_Seikai() / model.get_Count() *100;





    }
}



