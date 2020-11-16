package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class udstaus extends AppCompatActivity {
    ListView lvstatus;
    TextView dstatus,did,dname;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udstaus);

        update = findViewById(R.id.update);

        dstatus = findViewById(R.id.dstatus);
        lvstatus = findViewById(R.id.lvstatus);
        did = findViewById(R.id.did);
        dname = findViewById(R.id.dname);

        Intent intent = getIntent();
        String drivid = intent.getStringExtra("did");
        String drivname = intent.getStringExtra("dname");


        did.setText(drivid);
        dname.setText(drivname);

        lvstatus = new ListView(this);
        List<String> data = new ArrayList<>();

        data.add("booked");
        data.add("pending");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lvstatus.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(udstaus.this);
        builder.setCancelable(true);
        builder.setView(lvstatus);
        final  AlertDialog dialog = builder.create();


        dstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                lvstatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dstatus.setText(adapter.getItem(position));
                        dialog.dismiss();


                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}