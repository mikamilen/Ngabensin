package com.example.root.ngabensin.Vechile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.root.ngabensin.Adapter.VechileListAdapter;
import com.example.root.ngabensin.R;
import com.example.root.ngabensin.SQLiteOperation;

import java.util.ArrayList;

/**
 * Created by sep on 13/01/18.
 */

public class VechileList extends AppCompatActivity {


    GridView gridView;
    ArrayList<VechileItem> list;
    VechileListAdapter adapter;

    SQLiteOperation dataOperations;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vechile);

        list = new ArrayList<>();
        dataOperations = new SQLiteOperation(this);
        dataOperations.open();
        list = dataOperations.getAllVechile(null,null);

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new VechileListAdapter(VechileList.this , R.layout.activity_vechile_item, list);
        gridView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

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
