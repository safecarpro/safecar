package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

public class registration extends AppCompatActivity {
    DatabaseHelper MyDB;
    EditText username;
    EditText email;
    EditText password;
    EditText address;
    EditText phnno;
    EditText location;
    String s_name,s_gen,s_address, s_mob,s_location, s_email, s_pass;
    RadioGroup gender;
    RadioButton selectedGender;
    Button btnAddData,clear;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_registration);
        MyDB = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.et_username);
        address = (EditText) findViewById(R.id.et_address);
        phnno = (EditText) findViewById(R.id.et_phnno);
        location = (EditText) findViewById(R.id.et_loc);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        gender=(RadioGroup) findViewById(R.id.radioGender);
        btnAddData = (Button) findViewById(R.id.bt_register);
       clear = (Button) findViewById(R.id.reset);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    selectedGender = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
                    s_name = username.getText().toString();
                    s_gen = selectedGender.getText().toString();
                    s_address = address.getText().toString();
                    s_mob = phnno.getText().toString();
                    s_email = email.getText().toString().trim();
                    s_pass = password.getText().toString();
                    s_location = location.getText().toString();
                if (username.length() == 0) {
                    username.requestFocus();
                    username.setError("please enter your name");
                }
                else if (address.length() == 0) {
                    address.requestFocus();
                    address.setError("please enter your address");
                }
                else if (email.length() == 0) {
                    email.requestFocus();
                    email.setError("please enter your email");
                }else if (!checkEmail(email.getText().toString())) {
                    email.requestFocus();
                    email.setError("please enter valid email address");
                }
                else if (phnno.length() == 0) {
                    phnno.requestFocus();
                    phnno.setError("please enter your phone number");
                }else if (phnno.length() != 10) {
                    phnno.requestFocus();
                    phnno.setError("please enter valid mobile number");
                }
                else if (password.length() == 0) {
                    password.requestFocus();
                    password.setError("please enter your password");
                }else if (password.length() < 8)  {
                    password.requestFocus();
                    password.setError("password must be 8 characters.");
                }
                else if (location.length() == 0) {
                    location.requestFocus();
                    location.setError("please enter your location");
                }
                else
                    {

                    MyDB.insertdata(s_name, s_address, s_gen, s_email, s_mob, s_pass, s_location);

                    Toast.makeText(registration.this, "User created successfully! Please Login ", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, Toast.LENGTH_LONG);

                    Intent main = new Intent(registration.this, Login.class);
                    startActivity(main);
                    finish();

                }

            }
        });

clear.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        username.getText().clear();
        password.getText().clear();
        location.getText().clear();
        address.getText().clear();
        phnno.getText().clear();
        email.getText().clear();

    }
});

                    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}
