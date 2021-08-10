package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class chatwithmember extends AppCompatActivity {

    private static final int RESULT_PICK_CONTACT =1;
    private EditText editText_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatwithmember);
        getSupportActionBar().hide();

        Button button = findViewById(R.id.button_send);
        editText_mobile = findViewById(R.id.edit_mobile_no);
        EditText editText_msg = findViewById(R.id.edit_message);


        editText_mobile.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult (in, RESULT_PICK_CONTACT);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to get the edittext value
                String mobileNumber = editText_mobile.getText().toString();
                String message = editText_msg.getText().toString();


                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+""+mobileNumber + "&text="+message));
                    startActivity(intent);
                }else {
                    Toast.makeText(chatwithmember.this, "whatsapp not installed on your device", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    //Creating method appInstalledOrNot

    private boolean appInstalledOrNot(String url){
        PackageManager packageManager =getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;


    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String phoneNo = null;
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString (phoneIndex);

            editText_mobile.setText (phoneNo);


        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}