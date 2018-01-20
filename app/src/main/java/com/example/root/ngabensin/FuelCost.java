package com.example.root.ngabensin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.root.ngabensin.Model.User;
import com.example.root.ngabensin.Model.detilBensin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;


public class FuelCost extends AppCompatActivity {

    private static final String TAG = "gagal" ;
    private Spinner spChooseFuel;


//
    DatabaseReference dataBensin;
    String bensinku ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_cost);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spFuel);
        spinner.setItems("pertamax", "premium", "pertalite");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                bensinku = item;

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference tDataFuel = database.getReference("dataFuel").child(bensinku);

                tDataFuel.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        final TextView hargaBensin = (TextView)findViewById(R.id.hargaBensin);
                        final TextView tipeBensin = (TextView)findViewById(R.id.tipeBensin);

                        detilBensin detail = dataSnapshot.getValue(detilBensin.class);
                        int harga = detail.getHarga();
                        int tipe = detail.getTipe();


                        hargaBensin.setText(String.valueOf(harga));
                        tipeBensin.setText(String.valueOf(tipe));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());


                    }
                });

            }
        });







    }







}
