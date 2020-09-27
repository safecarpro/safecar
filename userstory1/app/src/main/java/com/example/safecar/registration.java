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
    Button btnAddData,reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_registration);
        MyDB = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.username);
        address = (EditText) findViewById(R.id.address);
        phnno = (EditText) findViewById(R.id.phnno);
        location = (EditText) findViewById(R.id.loc);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        gender=(RadioGroup) findViewById(R.id.radioGender);
        btnAddData = (Button) findViewById(R.id.bt_register);
        reset = (Button) findViewById(R.id.reset);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username.getText().clear();
                email.getText().clear();
                password.getText().clear();
                address.getText().clear();
                phnno.getText().clear();
                location.getText().clear();




            }
        });


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
                MyDB.insertdata(s_name, s_address, s_gen,s_email, s_mob ,s_pass,s_location);
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
        });


                    }
                }
