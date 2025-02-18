package com.nguyentruongquy.sharedpreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

public class PrefManager {
    Context context;
    PrefManager(Context context){ this.context = context;}
    @SuppressLint("ApplySharedPref")
    public void saveLoginDetails(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Log.e("PrefManager", "Email hoặc mật khẩu không được để trống");
            return;
        }

        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Email", email);
            editor.putString("Password", password);
            editor.apply(); // Sử dụng apply() để lưu dữ liệu một cách không đồng bộ
        } catch (Exception e) {
            Log.e("PrefManager", "Lỗi khi lưu thông tin đăng nhập: " + e.getMessage());
        }
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
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        Boolean isEmailEmpty = sharedPreferences.getString("Email","").isEmpty();
        Boolean isPasswordEmpty = sharedPreferences.getString("Password","").isEmpty();
        return isPasswordEmpty && isEmailEmpty;

    }

}
