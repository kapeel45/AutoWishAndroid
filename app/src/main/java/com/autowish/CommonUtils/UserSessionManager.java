package com.autowish.CommonUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.autowish.HomeActivity;
import com.autowish.LoginActivity;
import java.util.HashMap;

/**
 *
 */
public class UserSessionManager {
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AutoWish";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_FIRSTTIME = "IsFirstTime";
    public static final String KEY_ID = "user_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ACTIVE = "active";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_BASETOKEN = "token";
    public static final String KEY_LOGINTYPE = "logintype";
    public static final String KEY_FCM_TOEKN = "fcmtoken";
    public static final String KEY_NATIONALITY = "nationality";
    public static final String KEY_AGE = "age";
    public static final String KEY_IS_AVAILABLE = "is_available";
    public static final String KEY_PROFILE_IMAGE = "profile_image";

    public UserSessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(String id, String username, String email, String token,String phone) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_BASETOKEN, token);
        editor.putString(KEY_PHONE, phone);
        editor.commit();
    }


    public void savePhoneNumber(String phonenumber) {
        editor.putString(KEY_PHONE, phonenumber);
        editor.commit();
    }

    public String getPhoneNumber() {
        return pref.getString(KEY_PHONE, "");
    }

    public void checkLogin() {
        if (this.isLoggedIn()) {
            Intent intent = new Intent(_context, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        } else {
            Intent intent = new Intent(_context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, ""));
        user.put(KEY_ID, pref.getString(KEY_ID, ""));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, ""));
        user.put(KEY_BASETOKEN, pref.getString(KEY_BASETOKEN, ""));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, ""));
        return user;
    }

    public void clear_session() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void saveFCMToken(String fcm_token) {
        editor.putString(KEY_FCM_TOEKN, fcm_token);
        editor.commit();
    }

    public String getFCMToken() {
        return pref.getString(KEY_FCM_TOEKN, "");
    }

}