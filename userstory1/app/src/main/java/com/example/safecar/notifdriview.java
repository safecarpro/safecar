package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class notifdriview extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences cu;
    String dnuid,username;
    TextView driverid,drivername;

    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{db.COL_dnid,db.COL_dusername,
            db.COL_dploc, db.COL_ddloc,db.COL_dpdate,db.COL_dddate,db.COL_dtime,db.COL_drivername,db.COL_dscid};

    final int[] to = new int[]{R.id.nuid,R.id.username,  R.id.locpick,R.id.locdrop,R.id.datepic,R.id.datedrop, R.id.time,R.id.driver,R.id.drivid};

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                driverid = view.findViewById(R.id.drivid);
                drivername = view.findViewById(R.id.driver);

                String did = driverid.getText().toString();
                String dname = drivername.getText().toString();

                Intent s = new Intent(notifdriview.this, udstaus.class);

                s.putExtra("did",did);
                s.putExtra("dname",dname);
                startActivity(s);

            }
        });
    }
}