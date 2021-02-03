package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Adminlogin extends AppCompatActivity {

    private Button button5;
    EditText Usernameadmin,inputpasswardadmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        getSupportActionBar().hide();

        Usernameadmin = findViewById(R.id.Usernameadmin);
        inputpasswardadmin = findViewById(R.id.inputpasswardadmin);

        //button admin login
        button5=(Button)findViewById(R.id.btnabminlogin);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password,username;

                password = inputpasswardadmin.getText().toString();
                username =  Usernameadmin.getText().toString();

                if( username.equals("shreyash123") && password.equals("12345678") ){
                    openMain_page1();



                }
                else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void openMain_page1(){
        Intent intent = new Intent(this, MainPage1.class);
        startActivity(intent);
    }
}