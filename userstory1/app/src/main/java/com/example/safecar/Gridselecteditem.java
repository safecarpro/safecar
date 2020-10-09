package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Gridselecteditem extends AppCompatActivity {
    TextView br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridselecteditem);
        br = (TextView)findViewById(R.id.Br);

        Intent intent = getIntent();
        String sbrand= intent.getStringExtra("brand");
        br.setText(sbrand);
    }
}