package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static jp.ac.asojuku.jousenb.markofriss.R.id.imageView3;

public class genrequestion extends AppCompatActivity {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm;
    String genre = "";
    String count = "1";
    String countst = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genrequestion);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_genrequestion);

        Intent intent = getIntent();
        genre = intent.getStringExtra("genre");

        count = intent.getStringExtra("count");
        final String counts = intent.getStringExtra("counts");

        int countx = Integer.parseInt(count);
        countx = countx + 1;
        countst = String.valueOf(countx);

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        ImageView imageView3 = (ImageView)findViewById(R.id.imageView3);

        final String path = dbm.selectgenre(sqlDB,genre);

        imageView3.setImageResource(this.getResources().getIdentifier(String.valueOf(path),"drawable", "jp.ac.asojuku.jousenb.markofriss"));


        Button btnSelectA = (Button) this.findViewById(R.id.buttonA);
        btnSelectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ア";
                String answer = dbm.pathanswer(sqlDB,path);
                String seikai = dbm.pathkigo(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer_rireki.class);

                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);
                intent.putExtra("flg","g");
                intent.putExtra("genre",genre);

                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

                intent.putExtra("path",path);

                startActivity(intent);
            }
        });
        Button btnSelectB = (Button) this.findViewById(R.id.buttonB);
        btnSelectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "イ";
                String answer = dbm.pathanswer(sqlDB,path);
                String seikai = dbm.pathkigo(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer_rireki.class);

                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);
                intent.putExtra("flg","g");
                intent.putExtra("genre",genre);

                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

                intent.putExtra("path",path);
                startActivity(intent);
            }
        });
        Button btnSelectC = (Button) this.findViewById(R.id.buttonC);
        btnSelectC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ウ";
                String answer = dbm.pathanswer(sqlDB,path);
                String seikai = dbm.pathkigo(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer_rireki.class);

                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);
                intent.putExtra("flg","g");
                intent.putExtra("genre",genre);

                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

                intent.putExtra("path",path);
                startActivity(intent);
            }
        });
        Button btnSelectD = (Button) this.findViewById(R.id.buttonD);
        btnSelectD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "エ";
                String answer = dbm.pathanswer(sqlDB,path);
                String seikai = dbm.pathkigo(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer_rireki.class);

                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);
                intent.putExtra("flg","g");
                intent.putExtra("genre",genre);

                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

                intent.putExtra("path",path);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sqlDB.close();
    }
}
