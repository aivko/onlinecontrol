package com.vizaco.onlinecontrol.constants;

public interface Authorization {

    //------------------SITE-----------------------

    final public static String SITE_ADDRESS = "http://localhost:10201/onlinecontrol/";


    //------------------FACEBOOK-----------------------

    final public static String FACEBOOK_API_KEY = "779752082073743";
    final public static String FACEBOOK_API_SECRET = "fb18b46825cec0cdbf1095047de60163";
    final public static String FACEBOOK_URL = "https://www.facebook.com/dialog/oauth";
    final public static String FACEBOOK_URL_ACCESS_TOKEN = "https://graph.facebook.com/oauth/access_token";
    final public static String FACEBOOK_URL_ME = "https://graph.facebook.com/me";
    final public static String FACEBOOK_URL_CALLBACK_REGISTRATION = SITE_ADDRESS + "callback/facebook/registration";


    //------------------GOOGLE+-----------------------

    final public static String GOOGLE_API_KEY = "403026701343-c2kkqlai9c0f297bl7d96ojot2tsfst3.apps.googleusercontent.com";
    final public static String GOOGLE_API_SECRET = "TwCvsg4VxUMuOr58seVAaQRA";
    final public static String GOOGLE_EMAIL = "403026701343-c2kkqlai9c0f297bl7d96ojot2tsfst3@developer.gserviceaccount.com";
    final public static String GOOGLE_URL = "https://accounts.google.com/o/oauth2/auth";
    final public static String GOOGLE_URL_ACCESS_TOKEN = "https://accounts.google.com/o/oauth2/token";
    final public static String GOOGLE_URL_ME = "https://www.googleapis.com/oauth2/v1/userinfo";
    final public static String GOOGLE_URL_CALLBACK_REGISTRATION = SITE_ADDRESS + "callback/google/registration";


}
