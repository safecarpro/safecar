package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class rating_driver extends AppCompatActivity {
    RatingBar ratingBar;
    EditText comment;
    TextView ratecount,showrating,driverid,user;
    Button submit;
    float ratevalue;
    String temp;
    SharedPreferences sd;
    DatabaseHelper db;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_driver);

        db = new DatabaseHelper(this);
        ratingBar= findViewById(R.id.ratingbar);
        comment= findViewById(R.id.comment);
        ratecount= findViewById(R.id.ratecount);
        submit= findViewById(R.id.submit);
        showrating= findViewById(R.id.showrating);
        driverid= findViewById(R.id.DRIVERID);
        user= findViewById(R.id.USER);


        sd = getSharedPreferences("user_details",MODE_PRIVATE);
         username  = sd.getString("username",null);


        Intent intent = getIntent();
        final String driverid2 = intent.getStringExtra("driverid");

        driverid.setText(driverid2);
        user.setText(username);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratevalue = ratingBar.getRating();
                if (ratevalue<=1 && ratevalue>0)
                    ratecount.setText(+ratevalue +"/5");
                else if (ratevalue<=2 && ratevalue>1)
                    ratecount.setText(+ratevalue +"/5");
                else if (ratevalue<=3 && ratevalue>2)
                    ratecount.setText(+ratevalue +"/5");
                else if (ratevalue<=4 && ratevalue>3)
                    ratecount.setText(+ratevalue +"/5");
                else if (ratevalue<=5 && ratevalue>4)
                    ratecount.setText(+ratevalue +"/5");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = ratecount.getText().toString();
                showrating.setText("rating: " + temp +"\n" + comment.getText());
                comment.setText("");
                ratingBar.setRating(0);
                ratecount.setText("");


                String rating=showrating.getText().toString();
                db.dreview(driverid2, username, rating);
                Toast.makeText(rating_driver.this, "reviwed succcesfully ", Toast.LENGTH_LONG).show();

            }
        });
    }
}