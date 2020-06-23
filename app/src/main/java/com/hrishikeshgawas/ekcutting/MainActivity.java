package com.hrishikeshgawas.ekcutting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String Tag = "MainActivity";
    private final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private static double latitude;
    private static double longitude;
    private static double Lat;
    private static double Lon;
    public DrawerLayout drawer;
    public NavigationView navigationView;
    public ActionBarDrawerToggle toggle;
    public Toolbar toolbar;


    public static double getLat() {

        return Lat;
    }

    public static void setLat(double lati) {
        Lat = lati;
    }
    public static double getLon() {
        return Lon;
    }

    public static void setLon(double longi) {
        Lon = longi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);

        }
        else{
            getCurrentLocation();
        }

        SharedPreferences prefs = this.getSharedPreferences(
                getPackageName(), Context.MODE_PRIVATE);
        boolean hasVisited = prefs.getBoolean("HAS_VISISTED_BEFORE", false);
        if(!hasVisited) {
            int TIMER = 5000;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,"We take your location details to tell you about popular food outlets near you.",Toast.LENGTH_LONG).show();
                }
            }, TIMER);
            prefs.edit().putBoolean("HAS_VISISTED_BEFORE", true).apply();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
                Log.v("Explore","msg 01 "+ latitude);

            }
            else {
                Toast.makeText(MainActivity.this, "Permission Denied!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void  getCurrentLocation(){
      //  progressBar.setVisibility(View.VISIBLE);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(4000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationServices.getFusedLocationProviderClient(MainActivity.this).requestLocationUpdates(locationRequest, new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                latitude = locationResult.getLastLocation().getLatitude();
                longitude = locationResult.getLastLocation().getLongitude();
                MainActivity.setLat(latitude);
                MainActivity.setLon(longitude);
                Log.v("Explore","msg 0 "+ getLat());
               // progressBar.setVisibility(View.GONE);


            }
        } , Looper.getMainLooper());


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Download this app to check out some amazing food destinations in Mumbai and popular food outlets around you! \n http://play.google.com/store/apps/details?id=" + getPackageName();
                String shareSub = "Ek Cutting App";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Share Using"));
                return true;
            case R.id.attribution:
                Intent attributionIntent = new Intent(MainActivity.this, AttributionActivity.class);
                startActivity(attributionIntent);
                return true;
            case R.id.rate_us:
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }

                return true;
            case R.id.feedback:
                Intent feedbackIntent = new Intent(Intent.ACTION_SEND);
                feedbackIntent.setData(Uri.parse("mailto:"));
                String[] to ={"hrishikesh.gawas99@gmail.com"};
                feedbackIntent.putExtra(Intent.EXTRA_EMAIL,to);
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Ek Cutting App");
                feedbackIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(feedbackIntent, "Send Email"));
                return true;
            case R.id.upgrade:
                Toast.makeText(MainActivity.this, "This is the latest version!",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.version:
                Toast.makeText(MainActivity.this, "Version: 1.0",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.privacy_policy:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sites.google.com/view/ekcuttingprivacypolicy")));
                return true;
            case R.id.terms_and_conditions:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sites.google.com/view/ekcutting-termsandconditions")));
                return true;


        }

        return false;
    }
}
