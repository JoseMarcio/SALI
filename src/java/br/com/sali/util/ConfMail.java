package br.com.sali.util;

/**
 *
 * @author SALI
 */
public abstract class ConfMail {

    private static final String HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_PORT = 465;
    private static final String MY_MAIL = "sali.testes@gmail.com";
    private static final String MY_PASSWORD = "salibody";
    private static final boolean SSL_CONNECT = true;
    private static final String FROM = "sali.testes@gmail.com";
    private static final String NAME_FROM = "SALI - Sistema de Auxílio no Aprendizado da Língua Ínglesa";

    public ConfMail() {
    }

    public static String getHOST_NAME() {
        return HOST_NAME;
    }

    public static int getSMTP_PORT() {
        return SMTP_PORT;
    }

    public static String getMY_MAIL() {
        return MY_MAIL;
    }

    public static String getMY_PASSWORD() {
        return MY_PASSWORD;
    }

    public static boolean isSSL_CONNECT() {
        return SSL_CONNECT;
    }

    public static String getFROM() {
        return FROM;
    }

    public static String getNAME_FROM() {
        return NAME_FROM;
    }

}
