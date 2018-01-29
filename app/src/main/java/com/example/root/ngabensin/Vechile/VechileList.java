package com.example.root.ngabensin.Vechile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.root.ngabensin.Adapter.VechileListAdapter;
import com.example.root.ngabensin.Model.Kendaraan;
import com.example.root.ngabensin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by sep on 13/01/18.
 */

public class VechileList extends AppCompatActivity {


    GridView gridView;
    ArrayList<Kendaraan> list;
    VechileListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vechile);

        list = new ArrayList<>();

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new VechileListAdapter(VechileList.this , R.layout.activity_vechile_item, list);
        gridView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("vehicle");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue(Kendaraan.class));
                }
//                Kendaraan kendaraan = dataSnapshot.getValue(Kendaraan.class);
//                System.out.println(kendaraan);

                gridView = (GridView) findViewById(R.id.gridView);
                adapter = new VechileListAdapter(VechileList.this , R.layout.activity_vechile_item, list);
                gridView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
