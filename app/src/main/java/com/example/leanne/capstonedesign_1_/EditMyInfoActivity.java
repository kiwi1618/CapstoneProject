package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Chloe on 4/12/2016.
 */
public class EditMyInfoActivity extends Activity implements View.OnClickListener {

    private Button saveEditMyInfo, buttonMale, buttonFemale;
    static boolean isFemaleClicked, isMaleClicked;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_myinfo);

        initView();
    }

    public void initView() {
        saveEditMyInfo = (Button)findViewById(R.id.button_my_info_save);
        saveEditMyInfo.setOnClickListener(this);
        buttonMale = (Button)findViewById(R.id.button_male);
        buttonMale.setOnClickListener(this);
        buttonFemale = (Button)findViewById(R.id.button_female);
        buttonFemale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_male:
                if (buttonMale.getBackground() == buttonFemale.getBackground()) {
                    buttonMale.setBackgroundResource(R.drawable.button_border_after);
                    buttonMale.setTextColor(getResources().getColor(R.color.mint));
                    isMaleClicked = true;
                } else if (buttonMale.getBackground() != buttonFemale.getBackground()) {
                    buttonMale.setBackgroundResource(R.drawable.button_border_after);
                    buttonMale.setTextColor(getResources().getColor(R.color.mint));
                    isMaleClicked = true;
                    buttonFemale.setBackgroundResource(R.drawable.button_boarder_before);
                    buttonFemale.setTextColor(getResources().getColor(R.color.light_gray));
                    isFemaleClicked = false;
                }
                break;
            case R.id.button_female:
                if (buttonMale.getBackground() == buttonFemale.getBackground()) {
                    buttonFemale.setBackgroundResource(R.drawable.button_border_after);
                    buttonFemale.setTextColor(getResources().getColor(R.color.mint));
                    isFemaleClicked = true;
                } else if (buttonMale.getBackground() != buttonFemale.getBackground()) {
                    buttonFemale.setBackgroundResource(R.drawable.button_border_after);
                    buttonFemale.setTextColor(getResources().getColor(R.color.mint));
                    isFemaleClicked = true;
                    buttonMale.setBackgroundResource(R.drawable.button_boarder_before);
                    buttonMale.setTextColor(getResources().getColor(R.color.light_gray));
                    isMaleClicked = false;
                }
                break;
            case R.id.button_my_info_save:
                // save info on DB
                // if DB save success
                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                // if DB save failure
                // Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT.show());
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
