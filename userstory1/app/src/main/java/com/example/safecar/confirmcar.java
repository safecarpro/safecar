package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.R.*;

public class confirmcar extends AppCompatActivity {

    EditText pickloc,droplock,datepick,datedrop;
    Button submit,cancel;
    private int tdate,tmonth,tyear,fdate,fmonth,fyear;
    ListView lv,lv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmcar);

        datepick = findViewById(R.id.datepick);
        pickloc = findViewById(R.id.pickloc);
        droplock = findViewById(R.id.droploc);
        datedrop = findViewById(R.id.datedrop);
        lv = findViewById(R.id.listcity);
        submit = findViewById(R.id.submit);

        lv = new ListView(this);
        List<String> data = new ArrayList<>();
        data.add("calicut");
        data.add("malapuram");
        data.add("wayanad");
        data.add("kochi");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(confirmcar.this);
        builder.setCancelable(true);
        builder.setView(lv);
        final  AlertDialog dialog = builder.create();





        //pickdate listener #1
        pickloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dialog.show();


                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pickloc.setText(adapter.getItem(position));
                        dialog.dismiss();

                    }
                });


            }
        });

        //dropdate

        droplock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        droplock.setText(adapter.getItem(position));
                        dialog.dismiss();

                    }
                });


            }
        });


        //pickdatelistener #3
        datepick.setClickable(true);
        datepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                fdate = cal.get(Calendar.DATE);
                fmonth = cal.get(Calendar.MONTH);
                fyear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(confirmcar.this, style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String fdate = date+"-"+month+"-"+year;
                        datepick.setText(fdate);


                    }
                },fyear,fmonth,fdate);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        //datedrop

        datedrop.setClickable(true);
        datedrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                tdate = cal.get(Calendar.DATE);
                tmonth = cal.get(Calendar.MONTH);
                tyear = cal.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(confirmcar.this, style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String tdate = date+"-"+month+"-"+year;

                        datedrop.setText(tdate);


                    }
                },tyear,tmonth,tdate);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 1);
                datePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pickloc.length() == 0) {
                    pickloc.requestFocus();
                    pickloc.setError("please select pickup city");
                } else if (droplock.length() == 0) {
                    droplock.requestFocus();
                    droplock.setError("please select drop city");
                } else if (datepick.length() == 0) {
                    datepick.requestFocus();
                    datepick.setError("please select pick date");
                }else if (datedrop.length() == 0) {
                    datedrop.requestFocus();
                    datedrop.setError("please select drop date");
                }

                else

            {



                alert(view);
                
               /* Intent r = new Intent(confirmcar.this, Home.class);
                startActivity(r);
                finish();*/
            }

            }
        });


    }
    public void alert(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Happy renting!..");
        builder.setMessage("car rented succesfully..!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent r = new Intent(confirmcar.this, Home.class);
                startActivity(r);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}