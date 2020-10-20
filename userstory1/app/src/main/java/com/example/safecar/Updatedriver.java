package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Updatedriver extends AppCompatActivity {

    EditText dtname,dtaddress,dtage,dtprice,dtbadge,dtlocation,dtyoe,dtphno,dtemail;
    ImageView imgdriver;
    RadioButton dtgender1,dtgender2,selectmode;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedriver);



        db = new DatabaseHelper(this);
        dtname = findViewById(R.id.dtname);
        dtaddress = findViewById(R.id.dtaddress);
        dtage = findViewById(R.id.dtage);
        dtgender1 = findViewById(R.id.dtgender1);
        dtgender2=findViewById(R.id.dtgender2);
        dtprice = findViewById(R.id.dtcharge);
        dtbadge = findViewById(R.id.dtbadge);
        dtlocation = findViewById(R.id.dtlocation);
        dtyoe = findViewById(R.id.dtyoe);
        dtphno = findViewById(R.id.dtphno);
        dtemail = findViewById(R.id.dtemail);
       // imgdriver = findViewById(R.id.imgdriver);

        Intent intent = getIntent();
        String sid = intent.getStringExtra("duid");
        String sname= intent.getStringExtra("duname");
        String saddress= intent.getStringExtra("duaddress");
        String sage= intent.getStringExtra("duage");
        String sgender= intent.getStringExtra("dugender");
        String sdprice= intent.getStringExtra("duprice");
        String sbadge= intent.getStringExtra("dubadge");
        String slocation= intent.getStringExtra("dulocation");
        String syoe= intent.getStringExtra("duyoe");
        String sphno= intent.getStringExtra("duphno");
        String semail= intent.getStringExtra("duemail");
       // Bitmap bitmap = intent.getParcelableExtra("dubitmap");
        String female = "Female";



        dtname.setText(sname);
        dtaddress.setText(saddress);
        dtage.setText(sage);
        if(female.equals(intent.getStringExtra("dugender"))){
            dtgender2.setChecked(true);
            dtgender1.setChecked(false);
        }
        else {
            dtgender1.setChecked(true);
            dtgender2.setChecked(false);
        }
        dtprice.setText(sdprice);
        dtbadge.setText(sbadge);
        dtlocation.setText(slocation);
        dtyoe.setText(syoe);
        dtphno.setText(sphno);
        dtemail.setText(semail);
       // byte[] bytes = db.driverImage(sid);
        //imgdriver.setImageBitmap(getImage(bytes));






    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Driverview2.class);
        startActivity(in);
        finish();
    }



}
