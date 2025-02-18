package com.nguyentruongquy.sharedpreference;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;

public class MainActivity extends AppCompatActivity {

    EditText mEmailView;
    EditText mPasswordView;
    CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe); // Đảm bảo ID này đúng trong XML

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton =(Button) findViewById(R.id.button_login_email);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Log giá trị email và password
        Log.d("AttemptLogin", "Email: " + email);
        Log.d("AttemptLogin", "Password: " + password);

        // Kiểm tra mật khẩu hợp lệ
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));  // Sửa lỗi mật khẩu không hợp lệ
            focusView = mPasswordView;
            cancel = true;
        }

        // Kiểm tra email hợp lệ
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));  // Sửa lỗi email không hợp lệ
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // Nếu có lỗi, focus vào trường bị lỗi
            focusView.requestFocus();
        } else {
            if (checkBoxRememberMe.isChecked()) {
                saveLoginDetails(email, password);
                startHomeActivity();
            } else {
                if (getEmail().equals(email) && getPassword().equals(password)) {
                    startHomeActivity();
                } else {
                    startMainActivity();
                }
            }
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email, password);
    }

    private String getEmail() {
        return new PrefManager(this).getEmail();
    }

    private String getPassword() {
        return new PrefManager(this).getPassword();
    }

    private boolean isEmailValid(String email) {
        // Kiểm tra email hợp lệ với android.util.Patterns
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        // Mật khẩu phải dài hơn 7 ký tự
        return password.length() > 7;
    }
}
