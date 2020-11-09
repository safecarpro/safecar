package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class notifdriview extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences cu;
    String dnuid,username;

    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{db.COL_dnid,db.COL_dusername,
            db.COL_dploc, db.COL_ddloc,db.COL_dpdate,db.COL_dddate,db.COL_dtime,db.COL_drivername};

    final int[] to = new int[]{R.id.nuid,R.id.username,  R.id.locpick,R.id.locdrop,R.id.datepic,R.id.datedrop, R.id.time,R.id.driver};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifdriview);

        cu = getSharedPreferences("user_details",MODE_PRIVATE);
        dnuid = cu.getString("uid",null);
        username = cu.getString("username", null);

        db = new DatabaseHelper(this);
        Cursor cursor = db.dnotiflist(dnuid);

        listView = findViewById(R.id.notifdlv);

        adapter = new SimpleCursorAdapter(this,R.layout.notifdrivlist, cursor, from, to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }
}