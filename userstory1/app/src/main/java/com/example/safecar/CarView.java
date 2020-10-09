package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarView extends AppCompatActivity {

    GridView gridView;
    ArrayList<Car> list;
    CarListAdapter adapter = null;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_view);
        db=new DatabaseHelper(this);
        gridView = (GridView) findViewById(R.id.gv_car);
        list = new ArrayList<>();
        adapter = new CarListAdapter(this, R.layout.car_items, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite

        Cursor cursor = db.getData("SELECT * FROM cartable");
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
            byte[] image = cursor.getBlob(9);

            list.add(new Car(   id , brand, model, price, agency, kms, phn, location, email, image));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvbrand = (TextView) view.findViewById(R.id.txtbrand);
                String sbrand = tvbrand.getText().toString();
                Intent s = new Intent(CarView.this,Gridselecteditem.class);
                s.putExtra("brand",sbrand);
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