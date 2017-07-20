/**
 * date:20170526
 * name:uema
 */
package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Ansewer_uema extends AppCompatActivity {

    String ans = "";
    String correct = "";
    String seikai = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ansewer_uema);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        ans = intent.getStringExtra("ans");
        correct = intent.getStringExtra("ANSWER");
        seikai = intent.getStringExtra("seikai");
        final String counts = intent.getStringExtra("counts");
        final String count = intent.getStringExtra("count");

        if (correct.equals(seikai)) {
            ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.answer);

        } else {
            ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.incorrect);

        }

        TextView tv = (TextView) findViewById(R.id.textViewseikai);
        tv.setText(seikai);

        TextView tv2 = (TextView) findViewById(R.id.textViewanswer);
        tv2.setText(ans);

        Button btnfinish = (Button) this.findViewById(R.id.btnfinish);
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ansewer_uema.this, result.class);
                intent.putExtra("count",count);
                intent.putExtra("counts",counts);
                startActivity(intent);
            }
        });

        Button btnnext = (Button) this.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ansewer_uema.this, result.class);
                intent.putExtra("count",count);
                intent.putExtra("counts",counts);

                startActivity(intent);
            }
        });

    }
}