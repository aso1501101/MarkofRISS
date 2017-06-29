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
        Button btnsc = (Button)this.findViewById(R.id.button_sc);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","1");

                startActivity(intent);
            }
        });

        Button btnsck = (Button)this.findViewById(R.id.button_sck);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","2");

                startActivity(intent);
            }
        });

        Button btnscg = (Button)this.findViewById(R.id.button_scg);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","3");

                startActivity(intent);
            }
        });

        Button btnsct = (Button)this.findViewById(R.id.button_sct);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","4");

                startActivity(intent);
            }
        });

        Button btnscj = (Button)this.findViewById(R.id.button_scj);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","5");

                startActivity(intent);
            }
        });

        Button btndn = (Button)this.findViewById(R.id.button_dn);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","6");

                startActivity(intent);
            }
        });

        Button btnss = (Button)this.findViewById(R.id.button_ss);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","7");

                startActivity(intent);
            }
        });

        Button btnms = (Button)this.findViewById(R.id.button_ms);
        btnsc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genre.this,genrequestion.class);

                intent.putExtra("genre","8");

                startActivity(intent);
            }
        });
    }
}
