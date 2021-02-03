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

public class loginpage extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    EditText Username,inputpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        getSupportActionBar().hide();

        Username = findViewById(R.id.Username);
        inputpassword = findViewById(R.id.inputpassword);


        //button1 login button
        button1=(Button) findViewById(R.id.btnLogin);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password,username;

                password = inputpassword.getText().toString();
                username = Username.getText().toString();



                 if( !username.equals("") && !password.equals("") ){

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];

                            field[0] = "username";
                            field[1] = "password";

                            //Creating array for data
                            String[] data = new String[2];

                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("http://192.168.43.96/splashscreen3/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        openMain_page1();
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }


                                }

                            }
                            //End Write and Read data with URL
                        }
                    });

                }
                 else {
                     Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                 }




            }
        });

        //button2 ragistar new user  openMain_page1();

        button2 = (Button) findViewById(R.id.btnragister);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNew_Ragista();

            }
        });

        //button3 admin login button
        button3 = (Button) findViewById(R.id.adminsignbtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openAdminlogin();

            }
        });
    }


    public void openMain_page1(){
        Intent intent = new Intent(this, MainPage1.class);
        startActivity(intent);
    }

    public void openNew_Ragista(){
        Intent intent = new Intent(this, NewRagistar.class);
        startActivity(intent);
    }

    public void openAdminlogin(){
        Intent intent = new Intent(this, Adminlogin.class);
        startActivity(intent);
    }

}