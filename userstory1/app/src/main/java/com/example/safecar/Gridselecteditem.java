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

public class Gridselecteditem extends AppCompatActivity {
    TextView br,model,price,agency,kms,phone,location,email,carid,carreview;
    ImageView imgcar;
    DatabaseHelper db;
    Button rentc,chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridselecteditem);
        db = new DatabaseHelper(this);
        br = (TextView)findViewById(R.id.Br);
        model = (TextView)findViewById(R.id.model);
        price = (TextView)findViewById(R.id.price);
        agency = (TextView)findViewById(R.id.agency);
        kms = (TextView)findViewById(R.id.kms);
        phone = (TextView)findViewById(R.id.phno);
        location = (TextView)findViewById(R.id.location);
        email = (TextView)findViewById(R.id.email);
        imgcar = findViewById(R.id.imgcar);
        rentc = findViewById(R.id.rentc);
        carid = findViewById(R.id.carid);
        carreview = findViewById(R.id.car_review);




        Intent intent = getIntent();
       // String carid = intent.getStringExtra("carid");
        String sid = intent.getStringExtra("carid");
        String sbrand= intent.getStringExtra("brand");
        String smodel= intent.getStringExtra("model");
        String sprice= intent.getStringExtra("price");
        String sagency= intent.getStringExtra("agency");
        String skms= intent.getStringExtra("kms");
        String sphone= intent.getStringExtra("phone");
        String slocation= intent.getStringExtra("location");
        String semail= intent.getStringExtra("email");
        Bitmap bitmap = intent.getParcelableExtra("bitmap");



       // String cid = carid.getText().toString();



        carid.setText(sid);
        br.setText(sbrand);
        model.setText(smodel);
        price.setText(sprice);
        agency.setText(sagency);
        kms.setText(skms);
        phone.setText(sphone);
        location.setText(slocation);
        email.setText(semail);
        byte[] bytes = db.carImage(sid);
       imgcar.setImageBitmap(getImage(bytes));


       /* String cid = carid.getText().toString();
        Intent s = new Intent(Gridselecteditem.this,confirmcar.class);
        s.putExtra("carid",cid);
        startActivity(s);*/

       carreview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent rs = new Intent(Gridselecteditem.this, Rating.class);
               String cid = carid.getText().toString();
               String username = br.getText().toString();
               // Intent s = new Intent(Gridselecteditem.this,confirmcar.class);
               rs.putExtra("carid",cid);

               startActivity(rs);
           }
       });




       rentc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent r = new Intent(Gridselecteditem.this, confirmcar.class);
               String cid = carid.getText().toString();
               String cname = br.getText().toString();
              // Intent s = new Intent(Gridselecteditem.this,confirmcar.class);
               r.putExtra("carid",cid);
               r.putExtra("carname",cname);
               startActivity(r);

           }
       });
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

   /* public void onBackPressed() {
       //super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), CarView.class);
        startActivity(in);
        finish();
    }*/
}