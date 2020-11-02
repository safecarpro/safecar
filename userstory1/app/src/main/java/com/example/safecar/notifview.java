package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class notifview extends AppCompatActivity {


    DatabaseHelper db;
    SharedPreferences cu;
    String nuid,username;

    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{db.COL_nid,db.COL_username,
            db.COL_ploc, db.COL_dloc,db.COL_pdate,db.COL_ddate,db.COL_time};

    final int[] to = new int[]{R.id.nuid,R.id.username,  R.id.locpick,R.id.locdrop,R.id.datepic,R.id.datedrop, R.id.time};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifview);

        cu = getSharedPreferences("user_details",MODE_PRIVATE);
        nuid = cu.getString("uid",null);
        username = cu.getString("username", null);

        db = new DatabaseHelper(this);
        Cursor cursor = db.notiflist(nuid);

        listView = findViewById(R.id.notiflv);

        adapter = new SimpleCursorAdapter(this,R.layout.notiflist, cursor, from, to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }
}