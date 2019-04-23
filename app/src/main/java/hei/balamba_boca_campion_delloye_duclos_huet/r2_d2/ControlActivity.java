package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


import java.io.IOException;
import java.io.OutputStreamWriter;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class ControlActivity extends AppCompatActivity  {

    private TextView mTextViewAngleLeft;
    private TextView mTextViewStrengthLeft;
    private WebView  mWebView;
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
                Client client = Singleton.getINSTANCE().client;
                try {
                    client.socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Return in log the command (direction or rotation)
                if (46 <= angle && angle < 136) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Forward"));
                    try {
                        Client.write("av",Singleton.getINSTANCE().client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (136 <= angle && angle < 226) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Left"));
                    try {
                        Client.write("tg",Singleton.getINSTANCE().client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (226 <= angle && angle < 315) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Backward"));
                    try {
                        Client.write("rc",Singleton.getINSTANCE().client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Right"));
                    try {
                        Client.write("td",Singleton.getINSTANCE().client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Return in log the different levels of speed
                if (0< strength && strength <33) {
                    Log.d("R2-D2", String.format("Low"));
                } else if (strength == 0) {
                    Log.d("R2-D2", String.format("STOP"));
                    try {
                        Client.write("ar",Singleton.getINSTANCE().client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (33<= strength && strength <66) {
                    Log.d("R2-D2", String.format("Medium"));
                } else {
                    Log.d("R2-D2", String.format("High"));
                }
            }
        });

        mWebView = (WebView) findViewById(R.id.camera_view);
        mWebView.loadUrl("http://10.127.0.119:8080/stream_simple.html");
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setInitialScale(130);



    }
}

