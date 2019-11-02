package com.myapp.vinz.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

public class home extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    private DrawerLayout drawLay;
    private ActionBarDrawerToggle drawTog;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawLay = (DrawerLayout) findViewById(R.id.drawer);
        drawTog = new ActionBarDrawerToggle(home.this, drawLay, tb, R.string.open, R.string.close);
        drawTog.setDrawerIndicatorEnabled(true);
        drawLay.addDrawerListener(drawTog);
        drawTog.syncState();
        getSupportActionBar().show();
        getSupportActionBar().setTitle("HTML");

        navView = (NavigationView) findViewById(R.id.nav);
        //navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        /*
         * if the navigation drawer is open and back option is pressed,
         * the drawer will close
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder alertBuild =new AlertDialog.Builder(this);
            alertBuild.setMessage("Are you sure you want to exit").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = alertBuild.create();
            alert.setTitle("Exit");
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub__main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawTog.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.home:
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                home.this.finish();
                return true;
            case R.id.about:
                Intent abt = new Intent(getApplicationContext(), about.class);
                startActivity(abt);
                return true;

            case R.id.contact:
                Intent cnt = new Intent(getApplicationContext(), contact.class);
                startActivity(cnt);
                return true;

            case R.id.feed:
                Intent fd = new Intent(getApplicationContext(), feedback.class);
                startActivity(fd);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.html) {
            Toast.makeText(this, "Welcome to HTML page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.css) {
            Toast.makeText(this, "Welcome to CSS page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.js) {
            Toast.makeText(this, "Welcome to JAVASCRIPT page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.php) {
            Toast.makeText(this, "Welcome to PHP page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.sql) {
            Toast.makeText(this, "Welcome to SQL and MySQL page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.bs) {
            Toast.makeText(this, "Welcome to BootStrap page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.jq) {
            Toast.makeText(this, "Welcome to JQuery page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.xml) {
            Toast.makeText(this, "Welcome to XML page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.wc) {
            Toast.makeText(this, "Welcome to W3CSS page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.ang) {
            Toast.makeText(this, "Welcome to Angular page!", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.asp) {
            Toast.makeText(this, "Welcome to ASP page!", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }*/
}