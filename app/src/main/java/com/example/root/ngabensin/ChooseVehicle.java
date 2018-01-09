package com.example.root.ngabensin;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.root.ngabensin.Adapter.ListViewPagerAdapter;
import com.example.root.ngabensin.Model.FueltripModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseVehicle extends AppCompatActivity {

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_vehicle);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ListViewPagerAdapter(getSupportFragmentManager(), getImage()));

        btnNext = (Button)findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseVehicle.this, ChooseFuel.class));
            }
        });

    }



    private List<FueltripModel> getImage() {
        List<FueltripModel> models = new ArrayList<>();
        models.add(new FueltripModel(R.drawable.ic_mr_button_connecting_06_dark, "Hello"));
        models.add(new FueltripModel(R.drawable.ic_mr_button_connecting_06_dark, "Hello1"));
        models.add(new FueltripModel(R.drawable.ic_mr_button_connecting_06_dark, "Hello2"));
        return models;
    }


}
