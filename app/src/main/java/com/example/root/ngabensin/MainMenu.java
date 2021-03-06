package com.example.root.ngabensin;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.root.ngabensin.Vechile.MyVechile;
import com.example.root.ngabensin.Vechile.Vechile;

public class MainMenu extends AppCompatActivity {
Button btnMyFuel , btnFuelCost;
    BottomNavigationItemView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);

        loadFragment(new Home());

//        btnMyFuel = (Button) findViewById(R.id.btnMyFuel);
//        btnMyFuel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainMenu.this, ChooseVehicle.class);
//                startActivity(intent);
//            }
//        });
//
//        btnFuelCost = (Button) findViewById(R.id.btnFuelCost);
//        btnFuelCost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainMenu.this, Vechile.class);
//                startActivity(intent);
//
////                toolbar.setTitle("Shop");
//                loadFragment(new VehicleFragment());
//            }
//        });
//
//        toolbar = getSupportActionBar();
//
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new Home();
                    loadFragment(fragment);
                    return true;
                case R.id.vehicle:
                    fragment = new VehicleFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.account:
                    fragment = new Account();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
