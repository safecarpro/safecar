package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class DriverView extends AppCompatActivity {

    GridView gridView;
    ArrayList<Driver> list;
    DriverListAdapter adapter = null;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view);
        db=new DatabaseHelper(this);
        gridView = (GridView) findViewById(R.id.gv_driver);
        list = new ArrayList<>();
        adapter = new DriverListAdapter(this,R.layout.driveritem, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite

        Cursor cursor = db.getData("SELECT * FROM drivertable");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String age = cursor.getString(3);
            String gender = cursor.getString(4);
            String price = cursor.getString(5);
            String badge = cursor.getString(6);
            String location = cursor.getString(7);
            String yoe = cursor.getString(8);
            String phno = cursor.getString(9);
            String email = cursor.getString(10);
            byte[] image = cursor.getBlob(12);

            list.add(new Driver( id,name, address, age, gender, price, badge, location, yoe, phno, email, image));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView did = view.findViewById(R.id.did);
                TextView dname = (TextView) view.findViewById(R.id.dname);
                TextView daddress = (TextView) view.findViewById(R.id.daddress);
                TextView dage = (TextView) view.findViewById(R.id.dage);
                TextView dgender = (TextView) view.findViewById(R.id.dgender);
                TextView dprice = (TextView) view.findViewById(R.id.dprice);
                TextView dbadge = (TextView) view.findViewById(R.id.dbadge);
                TextView dlocation = (TextView) view.findViewById(R.id.dlocation);
                TextView dyoe = (TextView) view.findViewById(R.id.dyoe);
                TextView dphno = (TextView) view.findViewById(R.id.dphno);
                TextView demail = (TextView) view.findViewById(R.id.demail);
                ImageView ivimage = view.findViewById(R.id.imgdriver);



                String sid = did.getText().toString();
                String sname = dname.getText().toString();
                String saddress = daddress.getText().toString();
                String sage = dage.getText().toString();
                String sgender = dgender.getText().toString();
                String sdprice = dprice.getText().toString();
                String sbadge = dbadge.getText().toString();
                String slocation = dlocation.getText().toString();
                String syoe = dyoe.getText().toString();
                String sphno = dphno.getText().toString();
                String semail = demail.getText().toString();


                Intent s = new Intent(DriverView.this,griddriveritem.class);
                s.putExtra("did",sid);
                s.putExtra("dname",sname);
                s.putExtra("daddress",saddress);
                s.putExtra("dage",sage);
                s.putExtra("dgender",sgender);
                s.putExtra("dprice",sdprice);
                s.putExtra("dbadge",sbadge);
                s.putExtra("dlocation",slocation);
                s.putExtra("dyoe",syoe);
                s.putExtra("dphno",sphno);
                s.putExtra("demail",semail);


                startActivity(s);
                finish();


            }
        });
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}