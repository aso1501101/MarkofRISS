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

public class question extends AppCompatActivity {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm;
    String count = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        count = intent.getStringExtra("count");
        final String counts = intent.getStringExtra("counts");

        final String year = intent.getStringExtra("year");
        final String season = intent.getStringExtra("season");

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        ImageView imageView3 = (ImageView)findViewById(R.id.imageView3);
        //imageView3.setImageResource(R.drawable.s29_01);

        String path = dbm.selectcount(sqlDB,count,year,season);

        imageView3.setImageResource(this.getResources().getIdentifier(String.valueOf(path),"drawable", "jp.ac.asojuku.jousenb.markofriss"));

        TextView tv = (TextView)findViewById(R.id.textView5);
        String  count2 = year+"年度　問"+count;
        tv.setText(count2);

        final String answer = dbm.selectanswer(sqlDB,count,year,season);
        final String seikai = dbm.selectkigo(sqlDB,count,year,season);

        Button btnSelectA = (Button) this.findViewById(R.id.buttonA);
        btnSelectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ア";

                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                int countx = Integer.parseInt(count);
                countx = countx + 1;
                String countst = String.valueOf(countx);
                intent.putExtra("count",countst);
                intent.putExtra("counts",counts);

                intent.putExtra("season",season);
                intent.putExtra("year",year);

                startActivity(intent);
                finish();
            }
        });
        Button btnSelectB = (Button) this.findViewById(R.id.buttonB);
        btnSelectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "イ";
                //String answer = dbm.selectanswer(sqlDB,count);
                //String seikai = dbm.selectkigo(sqlDB,count);
                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                int countx = Integer.parseInt(count);
                countx = countx + 1;
                String countst = String.valueOf(countx);
                intent.putExtra("count",countst);
                intent.putExtra("counts",counts);

                intent.putExtra("season",season);
                intent.putExtra("year",year);

                startActivity(intent);
                finish();
            }
        });
        Button btnSelectC = (Button) this.findViewById(R.id.buttonC);
        btnSelectC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ウ";
                //String answer = dbm.selectanswer(sqlDB,count);
                //String seikai = dbm.selectkigo(sqlDB,count);
                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                int countx = Integer.parseInt(count);
                countx = countx + 1;
                String countst = String.valueOf(countx);
                intent.putExtra("count",countst);
                intent.putExtra("counts",counts);

                intent.putExtra("season",season);
                intent.putExtra("year",year);

                startActivity(intent);
                finish();

            }
        });
        Button btnSelectD = (Button) this.findViewById(R.id.buttonD);
        btnSelectD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "エ";
                //String answer = dbm.selectanswer(sqlDB,count);
                //String seikai = dbm.selectkigo(sqlDB,count);
                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                int countx = Integer.parseInt(count);
                countx = countx + 1;
                String countst = String.valueOf(countx);
                intent.putExtra("count",countst);
                intent.putExtra("counts",counts);

                intent.putExtra("season",season);
                intent.putExtra("year",year);

                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sqlDB.close();
    }
}
