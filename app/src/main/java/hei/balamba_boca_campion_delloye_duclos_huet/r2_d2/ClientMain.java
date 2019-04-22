package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;


public class ClientMain extends Activity {


    private Socket socket;
    TextView response;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);




        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);
        buttonConnect = (Button) findViewById(R.id.connectButton);
        buttonClear = (Button) findViewById(R.id.clearButton);
        response = (TextView) findViewById(R.id.responseTextView);

        buttonConnect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Log.d("R2-D2", String.format("button connect clicked"));
                try {
                    Singleton.getINSTANCE().client = new Client(editTextAddress.getText()
                            .toString(), Integer.parseInt(editTextPort
                            .getText().toString()), response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Singleton.getINSTANCE().client.execute();
                Log.d("R2-D2", String.format("Client executed"));
                openMainActivity();
                Log.d("R2-D2", String.format("Main Activity Opened"));
            }

        });




        buttonClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                response.setText("");
                Log.d("R2-D2", String.format("text set"));
            }
        });

    }


    public void openMainActivity() {
        Intent intent = new Intent(this, ControlActivity.class);
        startActivity(intent);
    }
}
