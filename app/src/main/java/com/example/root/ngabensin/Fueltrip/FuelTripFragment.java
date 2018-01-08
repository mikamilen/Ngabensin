package com.example.root.ngabensin.Fueltrip;

/**
 * Created by root on 08/01/18.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.ngabensin.Model.FueltripModel;
import com.example.root.ngabensin.R;

import java.util.List;

public class FuelTripFragment extends Fragment {

    public static final String DATA = "data";
    private FueltripModel model;

    public static FuelTripFragment newInstance(FueltripModel model) {
        FuelTripFragment fragment = new FuelTripFragment();
        Bundle args = new Bundle();
        args.putSerializable(DATA, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_image_trip, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.image_trip);
        TextView text = (TextView) view.findViewById(R.id.text_image);

        text.setText(model.name);
        img.setImageResource(model.image);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = (FueltripModel) getArguments().getSerializable(DATA);
    }
}
