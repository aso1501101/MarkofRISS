package jp.ac.asojuku.jousenb.markofriss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPicUp = ( Button ) this.findViewById ( R.id.button2 );
        Button btnPicUp2 = ( Button ) this.findViewById ( R.id.button4 );
        Button btnPicUp3 = ( Button ) this.findViewById ( R.id.button3 );

        btnPicUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, genre.class);
                startActivity(intent);
            }
        });

        btnPicUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, jenre_toukei.class);
                startActivity(intent);
            }
        });

        btnPicUp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, rireki.class);
                startActivity(intent);
            }
        });

    }
}
