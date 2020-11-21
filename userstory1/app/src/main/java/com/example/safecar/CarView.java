package com.example.safecar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class CarView extends AppCompatActivity {

    GridView gridView;
    ArrayList<Car> list;
    CarListAdapter adapter = null;
    DatabaseHelper db;
    Button findall;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_view);
        db=new DatabaseHelper(this);
        gridView = (GridView) findViewById(R.id.gv_car);
        findall = findViewById(R.id.findall);
        tb = findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(" CARS");


        list = new ArrayList<>();
        adapter = new CarListAdapter(this, R.layout.car_items, list);
        gridView.setAdapter(adapter);

        Intent intent = getIntent();

        String loc = intent.getStringExtra("location");

        findall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // get location based data from sqlite
String status = "booked";
        Cursor cursor = db.getData("SELECT * FROM cartable WHERE loation ="+ '"'+loc+'"' + "AND cid not in (SELECT scid FROM NOTIFICATION WHERE status="+'"'+status+'"'+')');
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            String brand = cursor.getString(1);
            String model = cursor.getString(2);
            String price = cursor.getString(3);
            String agency = cursor.getString(4);
            String kms = cursor.getString(5);
            String phn = cursor.getString(6);
            String location = cursor.getString(7);
            String email = cursor.getString(8);
            byte[] image = cursor.getBlob(10);

            list.add(new Car(   id , brand, model, price, agency, kms, phn, location, email, image));
        }
        adapter.notifyDataSetChanged();

        findall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "";
                Cursor cursor = db.getData("SELECT * FROM cartable ");
                list.clear();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);

                    String brand = cursor.getString(1);
                    String model = cursor.getString(2);
                    String price = cursor.getString(3);
                    String agency = cursor.getString(4);
                    String kms = cursor.getString(5);
                    String phn = cursor.getString(6);
                    String location = cursor.getString(7);
                    String email = cursor.getString(8);
                    byte[] image = cursor.getBlob(10);

                    list.add(new Car(   id , brand, model, price, agency, kms, phn, location, email, image));
                }
                adapter.notifyDataSetChanged();

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                TextView tvid = view.findViewById(R.id.carid);
                TextView tvbrand = (TextView) view.findViewById(R.id.txtbrand);
                TextView tvmodel = (TextView) view.findViewById(R.id.txtmodel);
                TextView tvprice = (TextView) view.findViewById(R.id.txtPrice);
                TextView tvagency = (TextView) view.findViewById(R.id.txtagency);
                TextView tvkms = (TextView) view.findViewById(R.id.txtkms);
                TextView tvphn = (TextView) view.findViewById(R.id.txtphn);
                TextView tvlocation = (TextView) view.findViewById(R.id.txtloc);
                TextView tvemail = (TextView) view.findViewById(R.id.txtemail);
                ImageView ivimage = view.findViewById(R.id.imgFood);



                String sid = tvid.getText().toString();
                String sbrand = tvbrand.getText().toString();
                String smodel = tvmodel.getText().toString();
                String sprice = tvprice.getText().toString();
                String sagency = tvagency.getText().toString();
                String skms = tvkms.getText().toString();
                String sphn = tvphn.getText().toString();
                String slocation = tvlocation.getText().toString();
                String semail = tvemail.getText().toString();

                Intent s = new Intent(CarView.this,Gridselecteditem.class);
                s.putExtra("carid",sid);
                s.putExtra("brand",sbrand);
                s.putExtra("model",smodel);
                s.putExtra("price",sprice);
                s.putExtra("agency",sagency);
                s.putExtra("kms",skms);
                s.putExtra("phone",sphn);
                s.putExtra("location",slocation);
                s.putExtra("email",semail);

                startActivity(s);

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