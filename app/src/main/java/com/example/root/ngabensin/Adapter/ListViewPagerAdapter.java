package com.example.root.ngabensin.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.View;

import com.example.root.ngabensin.Fueltrip.FuelTripFragment;
import com.example.root.ngabensin.Model.FueltripModel;
import com.example.root.ngabensin.Model.Kendaraan;

import java.util.List;

/**
 * Created by root on 08/01/18.
 */

public class ListViewPagerAdapter extends FragmentPagerAdapter{

    private List<Kendaraan> modelList;

    public ListViewPagerAdapter(FragmentManager fm, List<Kendaraan> modelList) {
        super(fm);
        this.modelList = modelList;
    }

    @Override
    public Fragment getItem(int position) {
        Kendaraan fueltripModel = modelList.get(position);
        return FuelTripFragment.newInstance(fueltripModel);
    }

    @Override
    public int getCount() {
        return modelList.size();
    }
}

