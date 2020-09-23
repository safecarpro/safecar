package com.example.safecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;



import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity  {

    DatabaseHelper db;
   private DrawerLayout dl;
   private NavigationView nv;
   private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dl = findViewById(R.id.drawer);
        nv = findViewById(R.id.nav);
        tb=findViewById(R.id.appbar);

        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menuicon);



        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.rentcar:
                        Toast.makeText(Home.this, "rented car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.addcar:
                        Toast.makeText(Home.this, "added car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.rentdriver:
                        Toast.makeText(Home.this, "rented driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.adddriver:
                        Toast.makeText(Home.this, "added driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.notification:
                        Toast.makeText(Home.this, "noti car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.updatecar:
                        Toast.makeText(Home.this, "updated car", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.updatedriver:
                        Toast.makeText(Home.this, "update driver", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.about:
                        Toast.makeText(Home.this, "about", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return true;
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}