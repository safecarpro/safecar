package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class griddriveritem extends AppCompatActivity {

    TextView dvname,dvaddress,dvage,dvgender,dvprice,dvbadge,dvlocation,dvyoe,dvphno,dvemail,driverid,driver_review;
    ImageView imgdriver;
    DatabaseHelper db;
    Button rentd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_griddriveritem);

        db = new DatabaseHelper(this);
        dvname = (TextView)findViewById(R.id.dname);
        dvaddress = (TextView)findViewById(R.id.daddress);
        dvage = (TextView)findViewById(R.id.dage);
        dvgender = (TextView)findViewById(R.id.dgender);
        dvprice = (TextView)findViewById(R.id.dprice);
        dvbadge = (TextView)findViewById(R.id.dbadge);
        dvlocation = (TextView)findViewById(R.id.dlocation);
        dvyoe = (TextView)findViewById(R.id.dyoe);
        dvphno = findViewById(R.id.dphno);
        dvemail = findViewById(R.id.demail);
        imgdriver = findViewById(R.id.imgdriver);
        rentd = findViewById(R.id.rentd);
        driverid = findViewById(R.id.drivid);
        driver_review = findViewById(R.id.driver_review);

        Intent intent = getIntent();
        String sid = intent.getStringExtra("did");
        String sname= intent.getStringExtra("dname");
        String saddress= intent.getStringExtra("daddress");
        String sage= intent.getStringExtra("dage");
        String sgender= intent.getStringExtra("dgender");
        String sdprice= intent.getStringExtra("dprice");
        String sbadge= intent.getStringExtra("dbadge");
        String slocation= intent.getStringExtra("dlocation");
        String syoe= intent.getStringExtra("dyoe");
        String sphno= intent.getStringExtra("dphno");
        String semail= intent.getStringExtra("demail");
        Bitmap bitmap = intent.getParcelableExtra("dbitmap");


        driverid.setText(sid);
        dvname.setText(sname);
        dvaddress.setText(saddress);
        dvage.setText(sage);
        dvgender.setText(sgender);
        dvprice.setText(sdprice);
        dvbadge.setText(sbadge);
        dvlocation.setText(slocation);
        dvyoe.setText(syoe);
        dvphno.setText(sphno);
        dvemail.setText(semail);
        byte[] bytes = db.driverImage(sid);
        imgdriver.setImageBitmap(getImage(bytes));

        driver_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rs = new Intent(griddriveritem.this, Rating.class);
                startActivity(rs);
            }
        });

        rentd.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(griddriveritem.this, confirmdriver.class);
                String drivid = driverid.getText().toString();
                String drivname = dvname.getText().toString();
                // Intent s = new Intent(Gridselecteditem.this,confirmcar.class);
                r.putExtra("drivid",drivid);
                r.putExtra("drivname",drivname);
                startActivity(r);

            }
        });

    }
    public static Bitmap getImage(byte[] image) {

        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), DriverView.class);
        startActivity(in);
        finish();
    }
}