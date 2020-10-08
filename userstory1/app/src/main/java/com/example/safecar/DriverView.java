package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;




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
            String price = cursor.getString(5);
            byte[] image = cursor.getBlob(11);
            list.add(new Driver( price, image, id));
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