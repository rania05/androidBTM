package tn.esprit.btm.UI.app;

public class AppConfig {
    // Current address ip
    private static String IP_ADDRESS = "192.168.1.7";
  //  private static String IP_ADDRESS = "192.168.0.23";
    public static String URL_HOURS="http://"+ IP_ADDRESS +":3000/api/moyenTransport";
    ////////////////////////// USER /////////////////////////////////////
    public static String URL_AddAbn="http://"+IP_ADDRESS +":3000/api/abonnements";
    // Server user login url
    public static String URL_LOGIN = "http://"+ IP_ADDRESS +":3000/api/users/login";
  public  static String URL_LIGNE = "http://"+ IP_ADDRESS +":3000/api/moyenTransport/l/sud";
    public  static String URL_BUS= "http://"+ IP_ADDRESS +":3000/api/moyenTransport/t/bus";
    public  static String URL_TRAIN = "http://"+ IP_ADDRESS +":3000/api/moyenTransport/t/train";
    public  static String URL_METRO = "http://"+ IP_ADDRESS +":3000/api/moyenTransport/t/metro";


}
