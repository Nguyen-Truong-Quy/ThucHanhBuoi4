package com.nguyentruongquy.sharedpreference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeTextView;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);

        // Lấy email đã lưu từ PrefManager và hiển thị
        String email = new PrefManager(this).getEmail();
        welcomeTextView.setText("Chào mừng, " + email);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý đăng xuất, quay lại màn hình đăng nhập
                logout();
            }
        });
    }

    private void logout() {
        // Xóa thông tin đăng nhập
        new PrefManager(this).saveLoginDetails("", "");

        // Quay lại màn hình đăng nhập và xóa stack Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Kết thúc HomeActivity
    }
}
