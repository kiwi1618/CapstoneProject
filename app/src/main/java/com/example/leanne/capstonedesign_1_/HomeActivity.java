package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Adapter;

/**
 * Created by imsuyeon on 16. 4. 2..
 */
public class HomeActivity extends FragmentActivity {

   /* static final int NUMBER_OF_LISTS=3;
    myAdapter mAdapter;
    ViewPager mPager;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
