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

    private Button saveEditMyInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_myinfo);

        initView();
    }

    public void initView() {
        saveEditMyInfo = (Button)findViewById(R.id.button_my_info_save);
        saveEditMyInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_my_info_save:
                // save info on DB
                // if DB save success
                Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                // if DB save failure
                // Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT.show());
                Intent goBackHome = new Intent(this, HomeActivity.class);
                startActivity(goBackHome);
                break;
        }
    }
}
