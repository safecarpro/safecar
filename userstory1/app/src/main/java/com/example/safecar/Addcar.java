package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Addcar extends AppCompatActivity {

    DatabaseHelper db;
    EditText brand,model,amount,agency,kms,phonenum,loc,emailcar;
    Button addcar,carreset,addimage;
    final int REQUEST_CODE_GALLERY = 999;

    ImageView iv;
    String  cbrand,cmodel,camount,cagency,ckms,cphone,cloc,cemailcar, caddcar,ccarreset,caddimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);


        db = new DatabaseHelper(this);
        brand = (EditText) findViewById(R.id.Brand);
        model = (EditText) findViewById(R.id.model);
        amount = (EditText) findViewById(R.id.amount);
        agency = (EditText) findViewById(R.id.agency);
        kms = (EditText) findViewById(R.id.kms);
        phonenum = (EditText) findViewById(R.id.phonenum);
        loc= findViewById(R.id.loc);
        emailcar =  findViewById(R.id.emailcar);
        addcar =  findViewById(R.id.addcar);
        carreset= findViewById(R.id.carreset);
        addimage =  findViewById(R.id.addimage);
        iv = findViewById(R.id.iv);


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions
                        (Addcar.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });

        carreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brand.getText().clear();
                model.getText().clear();
                amount.getText().clear();
                agency.getText().clear();
                kms.getText().clear();
                phonenum.getText().clear();
                loc.getText().clear();
                emailcar.getText().clear();;

            }
        });



        addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                cbrand = brand.getText().toString();
                cmodel = model.getText().toString();
                camount = amount.getText().toString();
                cagency = agency.getText().toString();
                ckms = kms.getText().toString();
                cphone = phonenum.getText().toString();
                cloc = loc.getText().toString();
                cemailcar = emailcar.getText().toString();
                caddcar = addcar.getText().toString();
                ccarreset = carreset.getText().toString();


                Toast.makeText(Addcar.this, "car added succesfully ", Toast.LENGTH_LONG).show();

                byte[] newentryimg = imageViewToByte(iv);

                Addcar2(cbrand,cmodel,camount,cagency,ckms,cphone,cloc,cemailcar,newentryimg);

                Intent main = new Intent(Addcar.this, Home.class);
                startActivity(main);




            }

            private void Addcar2(String cbrand,String cmodel,String camount,String cagency,String ckms,String cphone,String cloc,String cemailcar,byte[] newentryimg) {

                boolean insertcardata =  db.insertcardata(cbrand,cmodel,camount,cagency,ckms,cphone,cloc,cemailcar,newentryimg);
            }

            private byte[] imageViewToByte(ImageView iv) {

                Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Uri imageuri = data.getData();
        try {
            InputStream is = getContentResolver().openInputStream(imageuri);

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}