package com.example.fundacion_dehvi;

public class LoginData {
    public static String accessToken = "";
    public static String refreshToken = "";
    public static int expiresIn = 0;
    public static String tokenType = "";
    public static  String scope = "";

    static void clearData(){
        accessToken="";
        refreshToken = "";
        expiresIn = 0;
        tokenType = "";
        scope = "";
    }

}
