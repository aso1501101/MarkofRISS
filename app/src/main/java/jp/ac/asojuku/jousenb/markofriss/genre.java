package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class genre extends AppCompatActivity {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
    }

    @Override
    protected void onResume() {
        super.onResume();

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        Intent intent = new Intent(genre.this,genrequestion.class);

        Button btnsc = (Button)this.findViewById(R.id.button_sc);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","1");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "1"));
                startActivity(intent);
            }
        });

        Button btnsck = (Button)this.findViewById(R.id.button_sck);
        btnsck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","2");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "2"));
                startActivity(intent);
            }
        });

        Button btnscg = (Button)this.findViewById(R.id.button_scg);
        btnscg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","3");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "3"));
                startActivity(intent);
            }
        });

        Button btnsct = (Button)this.findViewById(R.id.button_sct);
        btnsct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","4");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "4"));
                startActivity(intent);
            }
        });

        Button btnscj = (Button)this.findViewById(R.id.button_scj);
        btnscj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","5");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "5"));
                startActivity(intent);
            }
        });

        Button btndn = (Button)this.findViewById(R.id.button_dn);
        btndn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","6");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "6"));
                startActivity(intent);
            }
        });

        Button btnss = (Button)this.findViewById(R.id.button_ss);
        btnss.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","7");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "7"));
                startActivity(intent);
            }
        });

        Button btnms = (Button)this.findViewById(R.id.button_ms);
        btnms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);
                intent.putExtra("genre","8");
                intent.putExtra("counts" ,"0");
                intent.putExtra("count","1");
                intent.putExtra("path", dbm.selectgenre(sqlDB, "8"));
                startActivity(intent);
            }
        });
    }
}