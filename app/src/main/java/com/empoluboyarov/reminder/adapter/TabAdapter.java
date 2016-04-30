package com.empoluboyarov.reminder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.empoluboyarov.reminder.fragment.CurrentTaskFragment;
import com.empoluboyarov.reminder.fragment.DoneTaskFragment;

/**
 * Created by Evgeniy on 30.04.2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private  int numberOfTabs; //количество вкладок

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CurrentTaskFragment();
            case 1:
                return new DoneTaskFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
