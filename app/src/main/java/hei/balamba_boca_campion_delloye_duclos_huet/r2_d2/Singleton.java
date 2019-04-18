package hei.balamba_boca_campion_delloye_duclos_huet.r2_d2;

public class Singleton {

    public Client client;

    private Singleton(){

    }

    private static Singleton INSTANCE = new Singleton();

    public static Singleton getINSTANCE(){
        return INSTANCE;
    }


}
