package com.example.safecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class Home extends AppCompatActivity  {

    DatabaseHelper db2;
   private DrawerLayout dl;
   private NavigationView nv;
   private Toolbar tb;
    EditText loc,pick,drop;
    Button car,driver;
    ImageView dateic1,dateic2,locic;
    private int mdate,mmonth,myear;
    String datepick,datedrop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db2 = new DatabaseHelper(this);
        dl = findViewById(R.id.drawer);
        nv = findViewById(R.id.nav);
        tb=findViewById(R.id.appbar);
        loc=findViewById(R.id.location);
        pick=findViewById(R.id.dates);
        drop=findViewById(R.id.datedrop);
        car=findViewById(R.id.findcar);
        driver=findViewById(R.id.finddriver);
        dateic1=findViewById(R.id.datepickic);
        dateic2=findViewById(R.id.datedropic);
        locic=findViewById(R.id.locic);


        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menuicon);



        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.rentcar:
                        Toast.makeText(Home.this, "rented car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.addcar:
                        Intent main = new Intent(Home.this, Addcar.class);
                        startActivity(main);
                        Toast.makeText(Home.this, "added car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.rentdriver:
                        Toast.makeText(Home.this, "rented driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.adddriver:
                        Toast.makeText(Home.this, "added driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.notification:
                        Toast.makeText(Home.this, "noti car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.updatecar:
                        Toast.makeText(Home.this, "updated car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.updatedriver:
                        Toast.makeText(Home.this, "update driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.about:
                        Toast.makeText(Home.this, "about", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return true;
            }
        });


        dateic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                mdate = cal.get(Calendar.DATE);
                mmonth = cal.get(Calendar.MONTH);
                myear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Home.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        pick.setText(date+"-"+month+"-"+year);


                    }
                },myear,mmonth,mdate);
                datePickerDialog.show();


            }
        });
        dateic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                mdate = cal.get(Calendar.DATE);
                mmonth = cal.get(Calendar.MONTH);
                myear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Home.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month,int date) {
                        drop.setText(date+"-"+month+"-"+year);

                    }
                },myear,mmonth,mdate);
                datePickerDialog.show();

            }
        });


        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepick = pick.getText().toString();
                datedrop = drop.getText().toString();
                db2.insertdatedata(datepick, datedrop);
                Toast.makeText(Home.this, "Date added ", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, Toast.LENGTH_LONG);

                Intent main = new Intent(Home.this, listcar.class);
                startActivity(main);
                finish();

            }
        });







    }


    public void onBackPressed(){
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(),Home.class);
        startActivity(in);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}