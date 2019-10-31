package com.autowish;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.autowish.CommonUtils.UserSessionManager;
import com.autowish.UIFragments.AccountFragment;
import com.autowish.UIFragments.ChatFragment;
import com.autowish.UIFragments.MyAdsFragment;
import com.autowish.UIFragments.SellFragment;
import com.autowish.UIFragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    TextView heading;
    public static ImageView image_nav;
    BottomNavigationView bottomNavigationView;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sessionManager = new UserSessionManager(HomeActivity.this);
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

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment frag = new HomeFragment();
        fragmentTransaction.replace(R.id.main_linear, frag);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_follower:

                break;
            case R.id.nav_setting:
                Intent i = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            case R.id.nav_help:
                Intent i_help = new Intent(HomeActivity.this, HelpSupportActivity.class);
                startActivity(i_help);
                break;
            case R.id.nav_about:
                Intent i_about = new Intent(HomeActivity.this, AboutUsActivity.class);
                startActivity(i_about);
                break;
            case R.id.nav_logout:
                sessionManager.clear_session();
                break;

            case R.id.nav_notification:
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                HomeFragment frag = new HomeFragment();
//                fragmentTransaction.replace(R.id.main_linear, frag);
//                fragmentTransaction.commit();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_home:
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    HomeFragment frag = new HomeFragment();
                    fragmentTransaction.replace(R.id.main_linear, frag);
                    fragmentTransaction.commit();
                    return true;
                case R.id.action_chats:

                    return true;

                case R.id.action_sell:
                    Intent i_add = new Intent(HomeActivity.this, AddAdActivity.class);
                    startActivity(i_add);
                    return true;

                case R.id.action_myads:
                    FragmentTransaction ft_ads = getSupportFragmentManager().beginTransaction();
                    MyAdsFragment frag_ads = new MyAdsFragment();
                    ft_ads.replace(R.id.main_linear, frag_ads);
                    ft_ads.addToBackStack(null);
                    ft_ads.commit();


                    return true;

                case R.id.action_accounts:
                    FragmentTransaction ft_account = getSupportFragmentManager().beginTransaction();
                    AccountFragment frag_Account = new AccountFragment();
                    ft_account.replace(R.id.main_linear, frag_Account);
                    ft_account.addToBackStack(null);
                    ft_account.commit();
                    return true;

            }
            return false;
        }
    };


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
