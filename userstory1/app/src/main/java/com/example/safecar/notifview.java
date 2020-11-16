package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class notifview extends AppCompatActivity {


    DatabaseHelper db;
    SharedPreferences cu;
    String nuid,username;
    TextView carid1,carname1;

    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{db.COL_nid,db.COL_username,
            db.COL_ploc, db.COL_dloc,db.COL_pdate,db.COL_ddate,db.COL_time,db.COL_carname,db.COL_scid};

    final int[] to = new int[]{R.id.nuid,R.id.username,  R.id.locpick,R.id.locdrop,R.id.datepic,R.id.datedrop, R.id.time,R.id.carname,R.id.carid3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifview);




        cu = getSharedPreferences("user_details",MODE_PRIVATE);
        nuid = cu.getString("uid",null);
        username = cu.getString("username", null);
        //String cid = carid.getText().toString();

        //carid.getText().toString()
        db = new DatabaseHelper(this);
        Cursor cursor = db.notiflist(nuid);

        listView = findViewById(R.id.notiflv);

        adapter = new SimpleCursorAdapter(this,R.layout.notiflist, cursor, from, to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                carid1 = view.findViewById(R.id.carid3);
                carname1 = view.findViewById(R.id.carname);

                String cid = carid1.getText().toString();
                String cname = carname1.getText().toString();

                Intent s = new Intent(notifview.this, ucstatus.class);
                s.putExtra("cid",cid);
                s.putExtra("cname",cname);
                startActivity(s);
                finish();

            }
        });
    }
}