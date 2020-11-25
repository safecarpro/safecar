package com.example.safecar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ucstatus extends AppCompatActivity {
    ListView lvstatus;
    TextView cstatus, cid1, carname1;
    Button update;
    DatabaseHelper db;
    Toolbar tb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucstatus);
        db = new DatabaseHelper(this);

        update = findViewById(R.id.update);

        cstatus = findViewById(R.id.cstatus);
        lvstatus = findViewById(R.id.lvstatus);
        cid1 = findViewById(R.id.cid);
        carname1 = findViewById(R.id.cname);

        tb = findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("STATUS");

        Intent intent = getIntent();
        final String carid = intent.getStringExtra("cid");
        final String cname = intent.getStringExtra("cname");

        cid1.setText(carid);
        carname1.setText(cname);


        lvstatus = new ListView(this);
        List<String> data = new ArrayList<>();

        data.add("Booked");
        data.add("Available");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        lvstatus.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(ucstatus.this);
        builder.setCancelable(true);
        builder.setView(lvstatus);
        final AlertDialog dialog = builder.create();

        cstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                lvstatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        cstatus.setText(adapter.getItem(position));
                        dialog.dismiss();


                    }
                });
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String cid2 = cid1.getText().toString();
                String status = cstatus.getText().toString();
                db.ustatus(cid2, status);
                // db.getData("UPDATE  NOTIFICATION SET status ="+ "'"+status+"'"+" where scid =" +"'"+cid2+"'" );
                Toast.makeText(ucstatus.this, "added succesfully ", Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), notifview.class);
        startActivity(in);
        finish();
    }
}