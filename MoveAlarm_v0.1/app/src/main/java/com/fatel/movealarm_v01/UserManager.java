package com.fatel.movealarm_v01;

/**
 * Created by FatelCypher on 9/10/2015.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class UserManager {


    private final String KEY_PREFS = "account_user";
    private final String KEY_USERNAME = "username";
    private final String KEY_PASSWORD = "password";
    public SharedPreferences mAccount;
    public SharedPreferences.Editor mEditor;

    public UserManager(Context context) {
        mAccount = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        mEditor = mAccount.edit();
    }

    public boolean checkLoginValidate(String username, String password) {
        String realUsername = mAccount.getString(KEY_USERNAME, "");
        String realPassword = mAccount.getString(KEY_PASSWORD, "");

        if ( (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) &&
                username.equals(realUsername) &&
                password.equals(realPassword)) {
            return true;
        }
        return false;
    }

    public boolean registerUser(String username, String password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }

        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_PASSWORD, password);
        return mEditor.commit();
    }
}