package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button btnPicUp = (Button) this.findViewById(R.id.button28);
        btnPicUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rireki.this, question_rireki.class);
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("year","28");
                startActivity(intent);
            }
        });
        Button btnPicUp2 = (Button) this.findViewById(R.id.button29);
        btnPicUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rireki.this, question_rireki.class);
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("year","29");
                startActivity(intent);
            }
        });

        Button btn_top = (Button)this.findViewById(R.id.btn_top);
        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(rireki.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
