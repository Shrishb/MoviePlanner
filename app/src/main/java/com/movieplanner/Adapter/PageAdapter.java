package com.movieplanner.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movieplanner.View.ListViewFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    //constructor
    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    //todo: create new fragment for calendar view
    //use switch case to switch between the two tabs in tablayout
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListViewFragment();
            case 1:
                return new ListViewFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
