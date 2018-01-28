package com.example.root.ngabensin;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.root.ngabensin.Adapter.ListViewPagerAdapter;
import com.example.root.ngabensin.Model.FueltripModel;
import com.example.root.ngabensin.Model.Kendaraan;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class ChooseVehicle extends AppCompatActivity {

    Button btnNext;
    String kendaraanku = "";
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_vehicle);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ListViewPagerAdapter(getSupportFragmentManager(), getImage()));

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spVehicle);
        spinner.setItems("Featured", "My Vehicle");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                kendaraanku = item;

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference tDataKendaraan = database.getReference(mUser.getUid().toString()).child(kendaraanku);

                tDataKendaraan.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Kendaraan kendaraan = dataSnapshot.getValue(Kendaraan.class);
                        String namaKen = kendaraan.getNamaKendaraan();
                        String jenisKen = kendaraan.getJenisKendaraan();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        });

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
        models.add(new FueltripModel(R.drawable.motornew, "Hello"));
        models.add(new FueltripModel(R.drawable.motornew, "Hello1"));
        models.add(new FueltripModel(R.drawable.ic_mr_button_connecting_06_dark, "Hello2"));
        return models;
    }


}
