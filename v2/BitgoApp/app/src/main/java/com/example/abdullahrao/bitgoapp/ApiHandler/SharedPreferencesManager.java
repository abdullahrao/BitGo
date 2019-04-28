package com.example.abdullahrao.bitgoapp.ApiHandler;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class SharedPreferencesManager {
    private static SharedPreferencesManager mInstance;
    private static Context mCtx;
    SharedPreferences sharedPreferences;

    private static final String Shared_Pref_Name = "CurrentUserData";
    private static final String Key_User_id = "user_id";
    private static final String Key_User_Name = "user_name";
    private static final String Key_User_Email = "user_email";
    private static final String Key_User_Age = "user_age";

    private SharedPreferencesManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferencesManager(context);
        }
        return mInstance;
    }

    public boolean UserLogin(int user_id,String user_name,String user_email,int user_age){

        sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Key_User_id,user_id);
        editor.putString(Key_User_Name,user_name);
        editor.putString(Key_User_Email,user_email);
        editor.putInt(Key_User_Age,user_age);

        editor.apply();

        return true;
    }
    public boolean isLoggedIn(){
        sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        if (sharedPreferences.getString(Key_User_Name,null) != null){
            return true;
        }
        return false;
    }
    public boolean User_Logout(){
        sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public Integer getUser_ID(){
        sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_User_id,0);
    }
    public String getUser_Name(){
        sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_User_Name,null);
    }
    public String getUser_Email(){

        sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_User_Email,null);
    }
    public Integer getUser_Age(){
        sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_User_Age,0);
    }

}
