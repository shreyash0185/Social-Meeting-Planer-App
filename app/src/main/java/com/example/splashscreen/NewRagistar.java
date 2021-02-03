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

public class NewRagistar extends AppCompatActivity {

    private Button button4;

    EditText inputemail,inputpassword,Username,fullnameuser;

    String emailpatern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ragistar);
        getSupportActionBar().hide();

        inputemail = findViewById(R.id.inputemail);
        inputpassword = findViewById(R.id.inputpassword);
        Username = findViewById(R.id.Username);
        fullnameuser = findViewById(R.id.fullnameuser);

        button4=(Button) findViewById(R.id.btnsignup);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,username,fullname;
                email = inputemail.getText().toString();
                password = inputpassword.getText().toString();
                username = Username.getText().toString();
                fullname = fullnameuser.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(NewRagistar.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }else if(!email.matches(emailpatern)){
                    Toast.makeText(NewRagistar.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(NewRagistar.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else if (password.length()<8){
                    Toast.makeText(NewRagistar.this, "Please Enter 8 Degit Password", Toast.LENGTH_SHORT).show();
                }else if (username.isEmpty()){
                    Toast.makeText(NewRagistar.this, "Please Enter UserName ", Toast.LENGTH_SHORT).show();
                }else if (fullname.isEmpty()){
                    Toast.makeText(NewRagistar.this, "Please Enter Your Name ", Toast.LENGTH_SHORT).show();
                }else if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")){

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://192.168.43.96/splashscreen3/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        openloginpage();
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



            }
        });



    }

    public void openloginpage(){
        Intent intent = new Intent(this, loginpage.class);
        startActivity(intent);
    }
}