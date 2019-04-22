package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;


import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, String> {
    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    OutputStreamWriter osw;
    String str = "OK";
    public Socket socket;

    Client(String addr, int port, TextView textResponse) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
        socket = new Socket(dstAddress, dstPort);
    }

    public static void write(String s, Client client) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(client.socket.getOutputStream(), "UTF-8");
        outputStreamWriter.write(s);
        outputStreamWriter.flush();
        InputStream inputStream = client.socket.getInputStream();
        Log.d("R2D2",inputStream.toString());
        Log.d("r2d2", "that's good");
    }

    @Override
    protected String doInBackground(Void... arg0) {

        try {
            Socket socket = this.socket;
            Log.d("R2-D2", String.format("try to catch adress and port"));
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

                //               write(str);
//                osw.write(str, 0, str.length());
//                osw.flush();
                Log.d("R2-D2", String.format("input stream " + response));

            }
        } catch (
                UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
            Log.d("R2-D2", String.format("UnknownHostException"));
        } catch (
                IOException e) {
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


}

