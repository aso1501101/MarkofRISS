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

import static jp.ac.asojuku.jousenb.markofriss.R.id.imageView2;

public class genrequestion extends AppCompatActivity {

    private SQLiteDatabase sqlDB;
    DBManager2 dbm;
    String genre = "";

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
                String answer = dbm.selectanswer(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);

                startActivity(intent);
            }
        });
        Button btnSelectB = (Button) this.findViewById(R.id.buttonB);
        btnSelectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "イ";
                String answer = dbm.selectanswer(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);

                startActivity(intent);
            }
        });
        Button btnSelectC = (Button) this.findViewById(R.id.buttonC);
        btnSelectC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ウ";
                String answer = dbm.pathanswer(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);

                startActivity(intent);
            }
        });
        Button btnSelectD = (Button) this.findViewById(R.id.buttonD);
        btnSelectD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "エ";
                String answer = dbm.pathanswer(sqlDB,path);
                Intent intent = new Intent(genrequestion.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);

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
