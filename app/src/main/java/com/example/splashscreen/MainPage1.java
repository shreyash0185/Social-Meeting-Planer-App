package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage1 extends AppCompatActivity {

    private Button addmeet;
    private Button logout;
    private Button history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1);
        getSupportActionBar().hide();

        addmeet=(Button)findViewById(R.id.btnaddmeet);
        history = (Button)findViewById(R.id.btnhistory);
        logout = (Button)findViewById(R.id.btnlogout);

        addmeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddmeeting();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openloginpage();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmeethistory();
            }
        });

    }

    public void openaddmeeting(){
        Intent intent = new Intent(this, addmeeting.class);
        startActivity(intent);
    }
    public void openloginpage(){
        Intent intent = new Intent(this, loginpage.class);
        startActivity(intent);
    }

    public void openmeethistory(){
        Intent intent = new Intent(this, meethistory.class);
        startActivity(intent);
    }

}