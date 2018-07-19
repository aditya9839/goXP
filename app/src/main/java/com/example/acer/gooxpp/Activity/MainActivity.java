package com.example.acer.gooxpp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.acer.gooxpp.Activity.Msg91OtpAndVerification.Msg91OtpWithVerification;

import com.example.acer.gooxpp.Permission.CurrentLocation;
import com.example.acer.gooxpp.R;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.book_an_appointment) {
            Intent intent = new Intent(this,BookAnAppoinment.class);
            startActivity(intent);
        }
        else if (id == R.id.otp) {
            Otp otp = new Otp();
            otp.referance(this);
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.parent_content, otp).addToBackStack(null).commit();
        }
        else if (id == R.id.card) {
            ViewCardDeletable viewCardDeletable = new ViewCardDeletable();
            viewCardDeletable.referance(this);
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.parent_content, viewCardDeletable).addToBackStack(null).commit();
        }
        else if (id == R.id.sign_up) {
            SignUpp signUpp = new SignUpp();
            signUpp.referance(this);
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.parent_content, signUpp).addToBackStack(null).commit();

        }
         else if (id == R.id.login){
            Login login = new Login();
            login.referance(this);
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.parent_content, login).addToBackStack(null).commit();
        }
         else if (id == R.id.invite_and_earn){
            InviteAndEarn inviteAndEarn = new InviteAndEarn();
            inviteAndEarn.referance(this);
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.parent_content, inviteAndEarn).addToBackStack(null).commit();
        }
         else if (id == R.id.dashboard){
            DoctorList doctorList = new DoctorList();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.parent_content, doctorList).addToBackStack(null).commit();
        }
        else if (id == R.id.lat_lang){
            CurrentLocation.getLastKnownLocation(this);
        }
        else if (id == R.id.Msg91){
            Intent intent = new Intent(MainActivity.this, Msg91OtpWithVerification.class);
            startActivity(intent);
        }

//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //result of current location
        if (requestCode == CurrentLocation.REQUEST_LOCATION){
            Log.d("Asdas","asd");
            CurrentLocation.getLastKnownLocation(this);
        }

    }

}
