package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Client extends AsyncTask<Void, Void, String> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    OutputStreamWriter osw;
    String str = "ar";

    Client(String addr, int port, TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
    }


    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            Log.d("R2-D2", String.format("try to catch adress and port"));
            socket = new Socket(dstAddress, dstPort);
            socket.setKeepAlive(true);
            Log.d("R2-D2", String.format("the socket catched address and port"));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];
            Log.d("R2-D2", String.format("1"));

            int bytesRead;
            InputStream inputStream = socket.getInputStream();
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

            /*
             * notice: inputStream.read() will block if no data return
             */
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                Log.d("R2-D2", String.format("3.1"));
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                Log.d("R2-D2", String.format("3.2"));
                response += byteArrayOutputStream.toString("UTF-8");
                Log.d("R2-D2", String.format("3.3"));

                osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                osw.write(str, 0, str.length());
                osw.flush();
                Log.d("R2-D2", String.format("input stream " + response));


            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
            Log.d("R2-D2", String.format("UnknownHostException"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
            Log.d("R2-D2", String.format("IOException"));
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    Log.d("R2-D2", String.format("socket closed"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        Log.d("R2-D2", String.format("try return response"));
        return response;


    }


    @Override
    protected void onPostExecute(String result) {
        Log.d("R2-D2", String.format("tet response set to response"));

        textResponse.setText(response);
        super.onPostExecute(result);
    }










    /*private Socket socket;
    private Button onclick2;

    private static final int SERVERPORT = 51714;
    private static final String SERVER_IP = "10.127.0.118";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        onclick2 = (Button) findViewById(R.id.myButton1);
        onclick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        new Thread(new ClientThread()).start();
    }

    public void onClick(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.EditText01);
            String str = et.getText().toString();
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(str + "hello there");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                socket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/
}

