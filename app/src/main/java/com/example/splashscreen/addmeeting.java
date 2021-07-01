package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addmeeting extends AppCompatActivity {

    private Button meetingsave;
    private Button btnpicker;

    int PLACE_PICKER_REQUEST = 1;


    EditText inputtopic,inputdatetime,inputplace,inputauthor,inputmember1,inputmember2,inputmember3;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeeting);
        getSupportActionBar().hide();

        inputtopic = findViewById(R.id.inputtopic);
        inputdatetime = findViewById(R.id.inputdatetime);
        inputplace = findViewById(R.id.inputplace);
        inputauthor = findViewById(R.id.inputauthor);
        inputmember1 = findViewById(R.id.inputmember1);
        inputmember2 = findViewById(R.id.inputmember2);
        inputmember3 = findViewById(R.id.inputmember3);
        meetingsave=(Button) findViewById(R.id.meetsave);
        btnpicker=(Button) findViewById(R.id.btnpicker);


       inputdatetime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showDateTimeDialog(inputdatetime);
           }
       });

      //////
        meetingsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String topic,datetime,place,author,member1,member2,member3;
                topic = inputtopic.getText().toString();
                datetime = inputdatetime.getText().toString();
                place = inputplace.getText().toString();
                author = inputauthor.getText().toString();
                member1 = inputmember1.getText().toString();
                member2 = inputmember2.getText().toString();
                member3 = inputmember3.getText().toString();

                if(!topic.equals("") &&  !datetime.equals("") && !place.equals("") && !author.equals("") && !member1.equals("") && !member2.equals("") &&!member3.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[7];
                            field[0] = "topic";
                            field[1] = "datetime";
                            field[2] = "place";
                            field[3] = "author";
                            field[4] = "member1";
                            field[5] = "member2";
                            field[6] = "member3";



                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = topic;
                            data[1] = datetime;
                            data[2] = place;
                            data[3] = author;
                            data[4] = member1;
                            data[5] = member2;
                            data[6] = member3;



                            PutData putData = new PutData("http://192.168.43.96/MeetRegister/addmeet.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Saved")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        openmainpage1();
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
                    Toast.makeText(getApplicationContext(), "All fiels required", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void showDateTimeDialog(EditText inputdatetime) {

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        inputdatetime.setText(simpleDateFormat.format(calendar.getTime()));


                    }
                };

                new TimePickerDialog(addmeeting.this,timeSetListener,calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),false).show();



            }
        };
        new DatePickerDialog(addmeeting.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //code of place picker to get the place with google api

    public void goPlacePicker(View view){

        //this is the place to call the place picker function

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {

            startActivityForResult(builder.build(addmeeting.this),PLACE_PICKER_REQUEST);
        }catch (GooglePlayServicesRepairableException e ){
            e.printStackTrace();

        }catch (GooglePlayServicesNotAvailableException e ){
            e.printStackTrace();
        }
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(addmeeting.this, data);
                inputplace.setText(place.getAddress());

            }
        }
    }



    public void openmainpage1(){
        Intent intent = new Intent(this, MainPage1.class);
        startActivity(intent);
    }


}