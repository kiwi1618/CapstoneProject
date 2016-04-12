package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Chloe on 4/13/2016.
 */
public class EditRankingsActivity extends Activity implements View.OnClickListener {

    Button editRankingsSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rankings);

        initView();
    }

    private void initView() {
        editRankingsSave = (Button)findViewById(R.id.button_edit_rankings_save);
        editRankingsSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // save info on DB
        // if DB save success
        Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
        // if DB save failure
        // Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT.show());
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
