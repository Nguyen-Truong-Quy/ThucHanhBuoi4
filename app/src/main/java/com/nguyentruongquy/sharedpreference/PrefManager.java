package com.nguyentruongquy.sharedpreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    Context context;
    PrefManager(Context context){ this.context = context;}
    @SuppressLint("ApplySharedPref")
    public void saveLoginDetails(String email, String password) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.apply();

    }

    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }
    public String getPassword(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password","");
    }
    public boolean isUserLoggedOut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetail", Context.MODE_PRIVATE);
        Boolean isEmailEmpty = sharedPreferences.getString("Email","").isEmpty();
        Boolean isPasswordEmpty = sharedPreferences.getString("Password","").isEmpty();
        return isPasswordEmpty && isEmailEmpty;

    }

}
