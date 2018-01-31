package org.prajwalan.app.prajwalan;

/**
 * Created by Nilesh1 on 22-11-2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String eventid;

    public PagerAdapter(FragmentManager fm, int NumOfTabs,String eventid) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.eventid = eventid;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment tab;
        switch (position) {
            case 0:
                tab = new information();
                break;
            case 1:
                tab = new Rules();
                break;
            case 2:
                tab = new downloads();
                break;
            case 3:
                tab =new contacts();
                break;
            default:
                return null;
        }
        Bundle abc = new Bundle();
        abc.putString("eventid",eventid);
        tab.setArguments(abc);
        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}