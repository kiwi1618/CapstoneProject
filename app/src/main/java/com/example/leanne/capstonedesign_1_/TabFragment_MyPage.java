package com.example.leanne.capstonedesign_1_;

/**
 * Created by Chloe on 4/11/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class TabFragment_MyPage extends Fragment implements View.OnClickListener {

    private Button editMyInfo, editRankingSettings;
    private ImageButton settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_mypage, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        editMyInfo = (Button)v.findViewById(R.id.button_edit_myinfo);
        editRankingSettings = (Button)v.findViewById(R.id.button_edit_rankings);
        settings = (ImageButton)v.findViewById(R.id.button_settings);
        editMyInfo.setOnClickListener(this);
        editRankingSettings.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_edit_myinfo:
                Intent goEditMyInfo = new Intent(getActivity(), EditMyInfoActivity.class);
                startActivity(goEditMyInfo);
                getActivity().overridePendingTransition(
                        R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
            case R.id.button_edit_rankings:
                Intent goEditRankings = new Intent(getActivity(), EditRankingsActivity.class);
                startActivity(goEditRankings);
                getActivity().overridePendingTransition(
                        R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
            case R.id.button_settings:
                Intent goSettings = new Intent(getActivity(), SettingsActivity.class);
                startActivity(goSettings);
                getActivity().overridePendingTransition(
                        R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
        }
    }

}