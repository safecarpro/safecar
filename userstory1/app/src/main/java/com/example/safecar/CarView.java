package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

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
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(9);

            list.add(new Car( price, image, id));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}