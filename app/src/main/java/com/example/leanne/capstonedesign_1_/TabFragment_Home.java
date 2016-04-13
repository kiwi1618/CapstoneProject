package com.example.leanne.capstonedesign_1_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Chloe on 4/13/2016.
 */
public class TabFragment_Home extends Fragment implements View.OnClickListener {

    private ImageButton homeSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_home, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        homeSearch = (ImageButton)v.findViewById(R.id.button_home_search);
        homeSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_home_search:
                Intent goSearch = new Intent(getActivity(), SearchActivity.class);
                startActivity(goSearch);
                getActivity().overridePendingTransition(
                        R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
        }
    }

}
