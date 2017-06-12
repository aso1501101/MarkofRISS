package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class rireki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rireki);
    }
    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String hitokoto = intent.getStringExtra("hitokoto");

        TextView tv = (TextView)findViewById(R.id.textViewhitokoto);
        tv.setText(hitokoto);
    }
}
