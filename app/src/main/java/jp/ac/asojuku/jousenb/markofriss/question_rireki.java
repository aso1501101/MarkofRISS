package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class question_rireki extends AppCompatActivity {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm;
    String path = "";
    String count = "1";
    String countst = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();

        count = intent.getStringExtra("count");
        final String counts = intent.getStringExtra("counts");

        int countx = Integer.parseInt(count);
        countx = countx + 1;
        countst = String.valueOf(countx);

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        path = dbm.selectrireki(sqlDB, "29");

        imageView3.setImageResource(this.getResources().getIdentifier(String.valueOf(path), "drawable", "jp.ac.asojuku.jousenb.markofriss"));

        Button btnSelectA = (Button) this.findViewById(R.id.buttonA);
        btnSelectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ア";

                String answer = dbm.pathanswer(sqlDB, path);
                String seikai = dbm.pathkigo(sqlDB, path);
                Intent intent = new Intent(question_rireki.this, Answer_rireki.class);

                intent.putExtra("ANSWER", a);
                intent.putExtra("ans", answer);
                intent.putExtra("seikai", seikai);

                //intent.putExtra("path", path);
                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

                startActivity(intent);
            }
        });
        Button btnSelectB = (Button) this.findViewById(R.id.buttonB);
        btnSelectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = "イ";

                String answer = dbm.pathanswer(sqlDB, path);
                String seikai = dbm.pathkigo(sqlDB, path);
                Intent intent = new Intent(question_rireki.this, Answer_rireki.class);

                intent.putExtra("ANSWER", b);
                intent.putExtra("ans", answer);
                intent.putExtra("seikai", seikai);

                //intent.putExtra("path", path);
                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

                startActivity(intent);
            }
        });
        Button btnSelectC = (Button) this.findViewById(R.id.buttonC);
        btnSelectC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = "ウ";

                Intent intent = new Intent(question_rireki.this, Answer_rireki.class);
                String answer = dbm.pathanswer(sqlDB, path);
                String seikai = dbm.pathkigo(sqlDB, path);

                intent.putExtra("ANSWER", c);
                intent.putExtra("ans", answer);
                intent.putExtra("seikai", seikai);

                //intent.putExtra("path", path);
                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

                startActivity(intent);
            }
        });
        Button btnSelectD = (Button) this.findViewById(R.id.buttonD);
        btnSelectD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "エ";

                Intent intent = new Intent(question_rireki.this, Answer_rireki.class);
                String answer = dbm.pathanswer(sqlDB, path);
                String seikai = dbm.pathkigo(sqlDB, path);

                intent.putExtra("ANSWER", d);
                intent.putExtra("ans", answer);
                intent.putExtra("seikai", seikai);

                //intent.putExtra("path", path);
                intent.putExtra("count", countst);
                intent.putExtra("counts",counts);

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
