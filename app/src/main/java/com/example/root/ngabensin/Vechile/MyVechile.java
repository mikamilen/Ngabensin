package com.example.root.ngabensin.Vechile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.root.ngabensin.Adapter.VechileListAdapter;
import com.example.root.ngabensin.Model.Kendaraan;
import com.example.root.ngabensin.Model.detilBensin;
import com.example.root.ngabensin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyVechile extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";


    private int mPage;
    GridView gridView;
    ArrayList<Kendaraan> list;
    VechileListAdapter adapter;


    public static MyVechile newInstance(int page, String title) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyVechile fragment = new MyVechile();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mPage = getArguments().getInt(ARG_PAGE);


    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_vechile, container, false);
        Button Tambah = (Button) view.findViewById(R.id.btnTambah);
        Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FrmVechile.class);
                startActivity(i);
            }
        });
//
        list = new ArrayList<>();
//        dataOperations = new SQLiteOperation(getActivity());
//        dataOperations.open();
//        list = dataOperations.getAllVechile(null,null);
//
//        gridView = (GridView) view.findViewById(R.id.gridView);
//        adapter = new VechileListAdapter(getActivity() , R.layout.activity_vechile_item, list);
//        gridView.setAdapter(adapter);
//
//        adapter.notifyDataSetChanged();
        return view;



    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
