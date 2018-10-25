package id.beneranindonesia.pusaka.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Session implements Serializable {

    private static volatile Session instance;
    private static final String USER_ID_KEY       = "USER_ID";
    private static final String USER_SESSION      = "USER_SESSION";
    private static final String USER_NICKNAME_KEY = "USER_NICKNAME";
    private static final String USER_IMAGEFILE    = "USER_IMAGEFILE";

    private String userID;
    private String nickname;
    private String userImage;

    private Session() {
        if (instance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            synchronized (Session.class) {
                if (instance == null) instance = new Session();
            }
        }
        return instance;
    }

    public void initalizeSession(Context context) {
        userID    = getSharedPreferences(context).getString(USER_ID_KEY, null);
        nickname  = getSharedPreferences(context).getString(USER_NICKNAME_KEY, null);
        userImage = getSharedPreferences(context).getString(USER_IMAGEFILE, null);
    }

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
    }

    public String getUserID() {
        return userID;
    }

    public String getNickname() {
        return nickname;
    }

    public  String getUserImage() {
        return userImage;
    }

    public void saveUser(Context context, String userID, String nickname, String imageFile) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_ID_KEY, userID);
        editor.putString(USER_IMAGEFILE, imageFile);
        editor.putString(USER_NICKNAME_KEY, nickname);
        editor.apply();
    }

    public boolean isLogin(Context context) {
        return getUserID() != null;
    }

    public void signOut(Context context) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(USER_ID_KEY);
        editor.remove(USER_IMAGEFILE);
        editor.remove(USER_NICKNAME_KEY);
        editor.apply();
    }

    protected Session readResolve() {
        return getInstance();
    }

}
































