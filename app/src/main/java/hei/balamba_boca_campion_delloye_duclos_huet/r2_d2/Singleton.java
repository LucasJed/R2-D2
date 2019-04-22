package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

import android.os.StrictMode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Singleton {

    public Client client;

    private Singleton(){

    }

    private static Singleton INSTANCE = new Singleton();

    public static Singleton getINSTANCE(){
        return INSTANCE;
    }



    public void write(String mvt) throws IOException {
        client.osw.write(mvt);
        client.osw.flush();
    }
}
