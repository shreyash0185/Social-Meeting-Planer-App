package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.time.Instant;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();

        Thread thread =new Thread(){
            public void run(){
                 try{
                     sleep(4000);
                 }
                 catch (Exception e){

                 }
                 finally {
                     Intent intent=new Intent(splashscreen.this , loginpage.class);
                     startActivity(intent);

                 }

            }
        };thread.start();
    }
}