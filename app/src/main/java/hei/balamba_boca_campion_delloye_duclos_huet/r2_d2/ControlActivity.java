package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class ControlActivity extends AppCompatActivity {

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
        final Client singleton= Singleton.getINSTANCE().client;

        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                // do whatever you want

                //Return in log the command (direction or rotation)
                if (46 <= angle && angle < 136) {

                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Forward"));
                    try {
                        singleton.osw.write("av");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Singleton.getINSTANCE().client.execute();

                } else if (136 <= angle && angle < 226) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Left"));
                    try {
                        singleton.osw.write("tg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Singleton.getINSTANCE().client.execute();

                } else if (226 <= angle && angle < 315) {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Backward"));
                    try {
                        singleton.osw.write("rc");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                        Singleton.getINSTANCE().client.execute();

                } else {
                    Log.d("R2-D2", String.format("angle : %d force : %d", angle, strength));
                    Log.d("R2-D2", String.format("Right"));
                    try {
                        singleton.osw.write("td");
                        Singleton.getINSTANCE().client.execute();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Singleton.getINSTANCE().client.execute();

                }
                //Return in log the different levels of speed
                if (0< strength && strength <33) {
                    Log.d("R2-D2", String.format("Low"));
                } else if (strength == 0) {
                    Log.d("R2-D2", String.format("STOP"));
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
    public void onClick11 (View view) {
        Log.d("R2-D2", String.format("OnClick works"));
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    executeSSHcommand();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);
    }

    public void executeSSHcommand(){
        Log.d("R2-D2", String.format("executeSSHcommand launch"));
        String user = "pi";
        String password = "raspberry";
        String host = "10.127.0.119";
        int port=22;

        Log.d("R2-D2", String.format("before try"));
        try{
            Log.d("R2-D2", String.format("try is ok"));
            JSch jsch = new JSch();

            Log.d("R2-D2", String.format("Before session"));
            Session session = jsch.getSession(user, host, port);

            Log.d("R2-D2", String.format("Before password"));
            session.setPassword(password);
            Log.d("R2-D2", String.format("After password"));

            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(10000);

            Log.d("R2-D2", String.format("Before session connected"));
            session.connect();
            Log.d("R2-D2", String.format("session connected"));

            ChannelExec channel = (ChannelExec)session.openChannel("exec");
            channel.setCommand("python ~/AlphaBot2/python/Joystick.py");
            Log.d("R2-D2", String.format("cd ~/AlphaBot2/python/Joystick.py"));


            channel.connect();
            Log.d("R2-D2", String.format("connected"));
            try{Thread.sleep(10000);}catch(Exception ee){}
            channel.disconnect();

            Log.d("R2-D2", String.format("disconnected"));

        }
        catch(JSchException e){
            System.out.println(e);
        }
    }
}

