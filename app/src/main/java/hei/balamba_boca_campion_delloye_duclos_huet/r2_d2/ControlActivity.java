package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import io.github.controlwear.virtual.joystick.android.JoystickView;

public class ControlActivity extends AppCompatActivity {

    private TextView mTextViewAngleLeft;
    private TextView mTextViewStrengthLeft;

    private TextView mTextViewAngleRight;
    private TextView mTextViewStrengthRight;
    private TextView mTextViewCoordinateRight;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        JoystickView joystick = findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                // do whatever you want
                if (46 < angle && angle < 136) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Forward"));
                } else if (136 < angle && angle < 225) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Left"));
                } else if (226 < angle && angle < 315) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Backward"));
                } else {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Right"));
                }
            }
        });

    }
}

