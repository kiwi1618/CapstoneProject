package com.example.leanne.capstonedesign_1_;

/**
 * Created by Chloe on 4/11/2016.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment_MyPage tab1 = new TabFragment_MyPage();
                return tab1;
            case 1:
                TabFragment_Ranking tab2 = new TabFragment_Ranking();
                return tab2;
            case 2:
                TabFragment_Favs tab3 = new TabFragment_Favs();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}