package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String counts = "";
        String counta = "";

        Intent intent = getIntent();
        counts = intent.getStringExtra("counts");
        counta = intent.getStringExtra("count");

        int countse = Integer.parseInt(counta);
        countse = countse - 1;
        counta = String.valueOf(countse);


        TextView tv = (TextView)findViewById(R.id.textViewexact);
        tv.setText(counts);

        TextView tv2 = (TextView)findViewById(R.id.textViewall);
        tv2.setText(counta);
    }
}
