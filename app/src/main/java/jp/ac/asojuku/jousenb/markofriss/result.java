package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        LinearLayout cardLinear = (LinearLayout)this.findViewById(R.id.cardLinear);
        cardLinear.removeAllViews();

        for (int i = 0; i < 5; i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.test_card,null);
                CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
                TextView textBox = (TextView) linearLayout.findViewById(R.id.textBox);
                textBox.setText("CardView" + i);
                cardView.setTag(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(result.this, String.valueOf(v.getTag()) + "番目のCardViewがクリックされました。", Toast.LENGTH_SHORT).show();
                    }
                });
                cardLinear.addView(linearLayout,i);
        }
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

        Button btntop = (Button)this.findViewById(R.id.btntop);
        btntop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(result.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
