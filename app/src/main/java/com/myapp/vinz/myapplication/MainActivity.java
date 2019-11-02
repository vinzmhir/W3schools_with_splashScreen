package com.myapp.vinz.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.util.Log;
import android.widget.Toast;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle drawTog;
    private NavigationView navView;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    // for avoiding using the defaults system fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        //for displaying the hamburger style navigation menu icon:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // for floating action button at the right lower part of the screen
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.show();

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawLay = (DrawerLayout) findViewById(R.id.drawer);
        drawTog = new ActionBarDrawerToggle(MainActivity.this, drawLay, tb, R.string.open, R.string.close);
        drawTog.setDrawerIndicatorEnabled(true);
        drawLay.addDrawerListener(drawTog);
        drawTog.syncState();
        getSupportActionBar().show();
        getSupportActionBar().setTitle("W3Schools");

        navView = (NavigationView) findViewById(R.id.nav);
        navView.setNavigationItemSelectedListener(this);
    }

    private void prepareMenuData() {

        /*MenuModel menuModel = new MenuModel("Android WebView Tutorial", true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }*/

        MenuModel menuModel = new MenuModel("TUTORIALS", true, true, ""); //Menu of Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Learn HTML", false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);

        childModel = new MenuModel("Learn CSS", false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);

        childModel = new MenuModel("Learn Bootstrap", false, false, "https://www.journaldev.com/19115/java-filereader");
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("REFERENCE", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Python AST – Abstract Syntax Tree", false, false, "https://www.journaldev.com/19243/python-ast-abstract-syntax-tree");
        childModelsList.add(childModel);

        childModel = new MenuModel("Python Fractions", false, false, "https://www.journaldev.com/19226/python-fractions");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        WebView webView = (WebView) findViewById(R.id.webView);
                        webView.loadUrl(headerList.get(groupPosition).url);
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.url.length() > 0) {
                        WebView webView = (WebView) findViewById(R.id.webView);
                        webView.loadUrl(model.url);
                        onBackPressed();
                    }
                }

                return false;
            }
        });
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
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawTog.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        /*if(id == R.id.html) {
            Intent html = new Intent(MainActivity.this, home.class);
            MainActivity.this.startActivity(html);
            MainActivity.this.finish();
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
        }*/
        navView.getMenu().setGroupVisible(R.id.tutsGroup,true);
        navView.getMenu().setGroupVisible(R.id.refersGroup,true);
        switch (item.getItemId()) {
            case R.id.tutsGroup:
                boolean b=!navView.getMenu().findItem(R.id.tutsGroup).isVisible();
                navView.getMenu().findItem(R.id.learn_html).isVisible();
                navView.getMenu().findItem(R.id.learn_css).isVisible();
                navView.getMenu().findItem(R.id.learn_bs).isVisible();
                navView.getMenu().findItem(R.id.learn_wc).isVisible();
                navView.getMenu().findItem(R.id.learn_colors).isVisible();
                navView.getMenu().findItem(R.id.learn_icons).isVisible();
                navView.getMenu().findItem(R.id.learn_graphics).isVisible();
                navView.getMenu().findItem(R.id.learn_svg).isVisible();
                navView.getMenu().findItem(R.id.learn_canvas).isVisible();
                navView.getMenu().findItem(R.id.learn_howTo).isVisible();
                navView.getMenu().findItem(R.id.learn_sass).isVisible();
                return true;
            case R.id.refersGroup:
        }

        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
