/**
 * date:20170526
 * name:uema
 */
package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Answer_rireki extends AppCompatActivity {

    String ans = "";
    String correct = "";
    String count = "";
    String seikai = "";
    //String path = "";
    int counts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_rireki);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        ans = intent.getStringExtra("ans");
        correct = intent.getStringExtra("ANSWER");
        count = intent.getStringExtra("count");
        counts = Integer.parseInt(intent.getStringExtra("counts"));
        //path = intent.getStringExtra("path");
        seikai = intent.getStringExtra("seikai");
        final String genre = intent.getStringExtra("genre");
        final String flg = intent.getStringExtra("flg");

        if (correct.equals(seikai)) {
            ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.answer);
            counts = counts + 1;
        } else {
            ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.incorrect);
        }

        TextView tv = (TextView)findViewById(R.id.TextViewseikai);
        tv.setText(seikai);

        TextView tv2 = (TextView)findViewById(R.id.textViewanswer);
        tv2.setText(ans);


        Button btnfinish = (Button)this.findViewById(R.id.btnfinish);
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Answer_rireki.this, result.class);

                String countst = String.valueOf(counts);
                intent.putExtra("counts",countst);
                intent.putExtra("count",count);
                startActivity(intent);
            }
        });

        Button btnnext = (Button)this.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  null;
                if ("g".equals(flg)){
                    intent = new Intent(Answer_rireki.this, genrequestion.class);
                    intent.putExtra("genre",genre);
                }else {
                    intent = new Intent(Answer_rireki.this, question_rireki.class);
                }
                String countst = String.valueOf(counts);
                intent.putExtra("count" ,count);
                intent.putExtra("counts" ,countst);
                startActivity(intent);
            }
        });

    }
}
