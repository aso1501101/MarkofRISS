/**
 * date:20170526
 * name:uema
 */
package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Answer extends AppCompatActivity {

    String ans = "";
    String correct = "";
    String count = "";

    public void exacta(View v){
        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.answer);
    }


    public void noesexacto(View v){
        ((ImageView)findViewById(R.id.imageView)).setImageResource(R.drawable.incorrect);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        ans = intent.getStringExtra("ans");
        correct = intent.getStringExtra("ANSWER");
        count = intent.getStringExtra("count");

        TextView tv = (TextView)findViewById(R.id.TextViewseikai);
        tv.setText(correct);

        TextView tv2 = (TextView)findViewById(R.id.textViewanswer);
        tv2.setText(ans);


        Button btnfinish = (Button)this.findViewById(R.id.btnfinish);
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Answer.this, result.class);
                startActivity(intent);
            }
        });

        Button btnnext = (Button)this.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Answer.this,question.class);

                intent.putExtra("count" ,count);

                startActivity(intent);
            }
        });

    }
}
