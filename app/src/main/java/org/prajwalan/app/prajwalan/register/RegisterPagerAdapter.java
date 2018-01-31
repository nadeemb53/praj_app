package org.prajwalan.app.prajwalan.register;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Moiz on 09/01/2016.
 */
public class RegisterPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public RegisterPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new Login();
                break;
            case 1:
                fragment = new Signup();
                break;

            default:
                return null;
        }


        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
