package com.example.root.ngabensin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.ngabensin.Vechile.Featured;
import com.example.root.ngabensin.Vechile.Vechile;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link VehicleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    Button btnMyFuel , btnFuelCost;
    public static Home newInstance(int page, String title) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Home fragment = new Home();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        btnMyFuel = (Button) view.findViewById(R.id.btnMyFuel);
        btnMyFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseVehicle.class);
                startActivity(intent);
            }
        });


        btnFuelCost = (Button) view.findViewById(R.id.btnFuelCost);
        btnFuelCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FuelCost.class);
                startActivity(intent);

            }
        });
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //        TextView tvTitle = (TextView) view.findViewById(R.id.vehiclefragment);
        return inflater.inflate(R.layout.activity_home, container, false);

    }
}