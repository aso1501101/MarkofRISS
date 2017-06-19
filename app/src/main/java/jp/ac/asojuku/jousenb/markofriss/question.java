package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static jp.ac.asojuku.jousenb.markofriss.R.id.imageView2;

public class question extends AppCompatActivity {

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
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        String hitokoto = intent.getStringExtra("hitokoto");
        String hitokoto2 = "answer";
        String hitokoto3 = "s29_01";

        dbm = new DBManager2(this);
        sqlDB = dbm.getWritableDatabase();

        ImageView imageView3 = (ImageView)findViewById(R.id.imageView3);
        //imageView3.setImageResource(R.drawable.s29_01);

        //imageView3.setImageResource(R.drawable.s29_01);
        imageView3.setImageResource(this.getResources().getIdentifier(String.valueOf(hitokoto3),"drawable", "jp.ac.asojuku.jousenb.markofriss"));

        TextView tv = (TextView)findViewById(R.id.textView5);
        tv.setText(hitokoto);

        Button btnSelectA = (Button) this.findViewById(R.id.buttonA);
        btnSelectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = "a";
                String answer = dbm.selectanswer(sqlDB,"29s01");
                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",a);
                intent.putExtra("ans",answer);
                startActivity(intent);
            }
        });
        Button btnSelectB = (Button) this.findViewById(R.id.buttonB);
        btnSelectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b = "b";
                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",b);
                startActivity(intent);
            }
        });
        Button btnSelectC = (Button) this.findViewById(R.id.buttonC);
        btnSelectC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = "c";
                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",c);
                startActivity(intent);
            }
        });
        Button btnSelectD = (Button) this.findViewById(R.id.buttonD);
        btnSelectD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "d";
                Intent intent = new Intent(question.this, Answer.class);
                intent.putExtra("ANSWER",d);
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
