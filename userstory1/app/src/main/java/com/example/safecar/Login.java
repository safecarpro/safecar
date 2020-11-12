package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper MyDB;

    EditText username, password;

    Button login,register;
    String s_username, s_password;
    private ProgressDialog mProgress;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // getSupportActionBar().hide();
        MyDB = new DatabaseHelper(this);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        mProgress =new ProgressDialog(this);
        String titleId="Signing in...";
        mProgress.setTitle(titleId);
        mProgress.setMessage("Please Wait...");


        username = (EditText) findViewById(R.id.et_uname);
        password = (EditText) findViewById(R.id.et_pass);
        login = (Button) findViewById(R.id.bt_login);
        register =  findViewById(R.id.tv_signup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Login.this, registration.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                s_username = username.getText().toString();
                s_password = password.getText().toString();


                mProgress.show();

                //Authenticate User
                UserModel currentUser = MyDB.Authenticate(new UserModel(s_username, s_password));


                //Check Authentication is successful or not
                if (currentUser != null) {
                    mProgress.dismiss();
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("uid", currentUser.user_id);
                    editor.putString("username",currentUser.username);
                    editor.commit();
                    Intent goToDash = new Intent(Login.this, Home.class);
                    startActivity(goToDash);
                    finish();
                    Toast.makeText(Login.this, " Login Successful!", Toast.LENGTH_SHORT).show();

                } else {
                    mProgress.dismiss();
                    Toast.makeText(Login.this, "Invalid mobile number or password.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
