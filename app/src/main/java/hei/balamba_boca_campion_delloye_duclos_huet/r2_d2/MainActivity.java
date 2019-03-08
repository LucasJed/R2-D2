package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
private Button controlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlBtn = (Button) findViewById(R.id.ControlBtn);
        controlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openControlActivity();
            }
        });

    }

        public void openControlActivity() {
            Intent intent = new Intent(this, ControlActivity.class);
            startActivity(intent);
        }
}
