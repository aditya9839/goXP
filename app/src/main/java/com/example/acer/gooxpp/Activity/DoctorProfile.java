package com.example.acer.gooxpp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.acer.gooxpp.R;

public class DoctorProfile extends AppCompatActivity {

    android.support.v4.app.FragmentTransaction transaction;
    android.support.v4.app.FragmentManager fragmentManager;
    Button mbookappointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        mbookappointment = (Button) findViewById(R.id.bookappointment);
        mbookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorProfile.this,BookAnAppoinment.class);
                startActivity(intent);
            }
        });
        Map blankFragment = new Map();
        blankFragment.referance(DoctorProfile.this);
        transaction = fragmentManager.beginTransaction();

//                use addToBackStack to go
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_favorite) {
//            Toast.makeText(DoctorProfile.this, "Action clicked", Toast.LENGTH_LONG).show();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
