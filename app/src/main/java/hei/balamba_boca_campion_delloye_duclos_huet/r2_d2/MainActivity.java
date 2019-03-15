package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
private Button controlBtn;
private Button discoverBtn;
private Button letdiscoverBtn;
private Button lookBtn;


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

        discoverBtn = (Button) findViewById(R.id.DiscoverBtn);
        discoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiscoverActivity();
            }
        });

        letdiscoverBtn = (Button) findViewById(R.id.LetDiscoverBtn);
        letdiscoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLetDiscoverActivity();
            }
        });

        lookBtn = (Button) findViewById(R.id.LookBtn);
        lookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLookActivity();
            }
        });

    }

    public void openControlActivity() {
        Intent intent = new Intent(this, ControlActivity.class);
        startActivity(intent);
    }
    public void openDiscoverActivity() {
        Intent intent = new Intent(this, DiscoverActivity.class);
        startActivity(intent);
    }
    public void openLetDiscoverActivity() {
        Intent intent = new Intent(this, LetDiscoverActivity.class);
        startActivity(intent);
    }
    public void openLookActivity() {
        Intent intent = new Intent(this, LookActivity.class);
        startActivity(intent);
    }

}
