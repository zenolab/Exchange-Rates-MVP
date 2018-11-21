package com.exchange_rates.grd.exchangerates;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDialogFragment;
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


import com.exchange_rates.grd.exchangerates.screens.InfoDialogFragment;
import com.exchange_rates.grd.exchangerates.screens.about.AboutFragment;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.foreign_currency.CurrencyFragmentView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    Fragment fragment = null;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(LOG_TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(LOG_TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Override
    protected void onStop(){
        super.onStop();
        fragmentManager = null;
        fragment = null;
    }
    private void mainView() {
        Log.d(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());

       // fragment = new AllRatesFragmentView();
        fragment = new CurrencyFragmentView(); //sync
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
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
        if (id == R.id.action_info) {
            AppCompatDialogFragment infoDialog = new InfoDialogFragment();
            infoDialog.show(getSupportFragmentManager(), "Informer");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int id = item.getItemId();

        if (id == R.id.nav_currency) {
            fragment = new CurrencyFragmentView();
        }
        else if (id == R.id.nav_info) {
             fragment = new AboutFragment();
        }

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.e("LOG_TAG", "Error in creating fragment");
        }
        return true; //for close navDraw
    }


}
