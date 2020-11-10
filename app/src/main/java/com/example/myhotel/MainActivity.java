package com.example.myhotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myhotel.adapter.HotelAdapter;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout _Drawer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar ApplicationToolbar = findViewById(R.id.main_toolbar);
        NavigationView navigationView = findViewById(R.id.main_navigation_view);

        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(ApplicationToolbar);

        _Drawer = findViewById(R.id.drawer);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,_Drawer,ApplicationToolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);
        _Drawer.addDrawerListener(toogle);
        toogle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
                    new HotelFragment()).commit();
            navigationView.setCheckedItem(R.id.user_menu_hotel);
        }

        View headerView  = navigationView.getHeaderView(0);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selected  = null;
        switch (item.getItemId()) {
            case R.id.user_menu_hotel :
                selected = new HotelFragment();
                break;
            case R.id.user_menu_profile :
                selected = new ProfileFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,selected).commit();
        _Drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}