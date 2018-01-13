package com.example.root.ngabensin.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.root.ngabensin.Vechile.Featured;
import com.example.root.ngabensin.Vechile.MyVechile;

/**
 * Created by sep on 13/01/18.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return MyVechile.newInstance(0, "My Vechile");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return Featured.newInstance(1, "Featured");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return "My Vechile";
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return "Featured";
            default:
                return null;
        }
    }
}