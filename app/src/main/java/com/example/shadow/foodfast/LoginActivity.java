package com.example.shadow.foodfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/*
 * @Abhishek Goyal
 * LoginActivity-For Login the Application using different means,
 *  but Sign-in and Sign-up Activity are yet to be made for authentication using live database like Firebase
 *  You can skip this Activity for now.
 */

public class LoginActivity extends AppCompatActivity
{
    Button login,regester,skip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags
                (
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                );
        getSupportActionBar().hide();
        login=findViewById(R.id.btn_login);
        regester=findViewById(R.id.btn_regester);
        skip=findViewById(R.id.skip);

        login.setOnClickListener(view -> Toast.makeText(LoginActivity.this, "Login Activity is not found", Toast.LENGTH_SHORT).show());

        regester.setOnClickListener(view -> Toast.makeText(LoginActivity.this, "Regester Activity is not found", Toast.LENGTH_SHORT).show());

        skip.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this,MainActivity.class)));
    }

}