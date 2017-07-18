package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class question_uema extends AppCompatActivity {

    private static final long serialVersionUID = 1L;
    private SQLiteDatabase sqlDB;
    DBManager2 dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String mondai_id = intent.getStringExtra("uema");

        final String counts = intent.getStringExtra("counts");
        final String count = intent.getStringExtra("count");

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        ImageView imageView3 = (ImageView)findViewById(R.id.imageView3);
        //imageView3.setImageResource(R.drawable.s29_01);

        Mondaimodel uema = dbm.questionuema(sqlDB,mondai_id);

        imageView3.setImageResource(this.getResources().getIdentifier(String.valueOf(uema.get_Path()),"drawable", "jp.ac.asojuku.jousenb.markofriss"));

        TextView tv = (TextView)findViewById(R.id.textView5);
        tv.setText(mondai_id);

        final String answer = uema.get_Ans();
        final String seikai = uema.get_Ansk();

       Button btnSelectA = (Button) this.findViewById(R.id.buttonA);
        btnSelectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ア";

                Intent intent = new Intent(question_uema.this, Ansewer_uema.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                intent.putExtra("count",count);
                intent.putExtra("counts",counts);

                startActivity(intent);
            }
        });
        Button btnSelectB = (Button) this.findViewById(R.id.buttonB);
        btnSelectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "イ";
                //String answer = dbm.selectanswer(sqlDB,count);
                //String seikai = dbm.selectkigo(sqlDB,count);
                Intent intent = new Intent(question_uema.this, Ansewer_uema.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                intent.putExtra("count",count);
                intent.putExtra("counts",counts);

                startActivity(intent);
            }
        });
        Button btnSelectC = (Button) this.findViewById(R.id.buttonC);
        btnSelectC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "ウ";
                //String answer = dbm.selectanswer(sqlDB,count);
                //String seikai = dbm.selectkigo(sqlDB,count);
                Intent intent = new Intent(question_uema.this, Ansewer_uema.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                intent.putExtra("count",count);
                intent.putExtra("counts",counts);

                startActivity(intent);
            }
        });
        Button btnSelectD = (Button) this.findViewById(R.id.buttonD);
        btnSelectD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "エ";
                //String answer = dbm.selectanswer(sqlDB,count);
                //String seikai = dbm.selectkigo(sqlDB,count);
                Intent intent = new Intent(question_uema.this, Ansewer_uema.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                intent.putExtra("seikai",seikai);

                intent.putExtra("count",count);
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
