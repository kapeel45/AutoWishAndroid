package com.autowish;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.autowish.UIFragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    NavigationView navigationView;
    TextView heading;
    public static ImageView image_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        heading = (TextView) findViewById(R.id.heading);

        image_nav = (ImageView) findViewById(R.id.image_nav);
        image_nav.setOnClickListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment frag = new HomeFragment();
        fragmentTransaction.replace(R.id.main_linear, frag);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_home:
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                HomeFragment frag = new HomeFragment();
                fragmentTransaction.replace(R.id.main_linear, frag);
                fragmentTransaction.commit();
                break;
            case R.id.nav_routes:
                //Do some thing here
                // add navigation drawer item onclick method here
                break;
            case R.id.nav_orders:
//                Intent i = new Intent(HomeNavDrawerActivity.this, OrdersActivity.class);
//                startActivity(i);
                break;
            case R.id.nav_support:
//                Intent i_support = new Intent(HomeNavDrawerActivity.this, SupportActivity.class);
//                startActivity(i_support);
                break;
            case R.id.nav_logout:
                finish();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_nav:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                break;


        }
    }
}
