package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by imsuyeon on 16. 4. 4..
 */
public class ExtraInfoActivity extends Activity implements View.OnClickListener {
    private EditText editTextCompany, editTextToeic;
    static boolean isFemaleClicked;
    static boolean isMaleClicked;
    private Button buttonMale;
    private Button buttonFemale;

    private int year;
    private int month;
    private int day;

    private TextView textViewBirthday;
    private Button buttonBirthday;

    static final int DATE_DIALOG_ID = 0;

    private TextView textViewUniSearch;
    private PopupWindow popupWindow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_info);
        initView();
    }

    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editTextCompany = (EditText) findViewById(R.id.wishcomp_extra_input);
        editTextToeic = (EditText) findViewById(R.id.editText_toeic);
        buttonMale = (Button) findViewById(R.id.button_male);
        buttonFemale = (Button) findViewById(R.id.button_female);
        isMaleClicked = false;
        isFemaleClicked = false;

        // buttonBirthday.setOnClickListener(this);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        textViewUniSearch = (TextView) findViewById(R.id.uni_extra_input);
        textViewUniSearch.setOnClickListener(this);

        updateDisplay();
    }

    private void updateDisplay() {
        // this.textViewBirthday.setText(new StringBuilder().append(year).append(".").append(month + 1).append(".").append(day));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int setYear, int setMonth, int setDay) {
                    year = setYear;
                    month = setMonth;
                    day = setDay;
                    updateDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, year, month, day);
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uni_extra_input:
                View viewPopup = getLayoutInflater().inflate(R.layout.activity_popup_university, null);
                popupWindow = new PopupWindow(viewPopup, RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setAnimationStyle(-1);  // 애니메이션 설정(-1:설정, 0:설정안함)
                /**
                 * showAtLocation(parent, gravity, x, y)
                 * @parent : PopupWindow가 생성될 parent View 지정
                 * View v = (View) findViewById(R.id.btn_click)의 형태로 parent 생성
                 * @gravity : parent View의 Gravity 속성 지정 Popupwindow 위치에 영향을 줌.
                 * @x : PopupWindow를 (-x, +x) 만큼 좌,우 이동된 위치에 생성
                 * @y : PopupWindow를 (-y, +y) 만큼 상,하 이동된 위치에 생성
                 */
                popupWindow.showAtLocation(viewPopup, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                break;
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
            case R.id.button_skip:
                Intent intentHome = new Intent(this, HomeActivity.class);
                startActivity(intentHome);
                this.overridePendingTransition(R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                this.finish();
                break;
            case R.id.button_save:
                boolean gender = false;
                // String birthday = textViewBirthday.getText().toString();

                if (isFemaleClicked)
                    gender = true;
                if (isMaleClicked)
                    gender = false;

                // SAVE "추가정보" to db

                Toast.makeText(getApplicationContext(), "추가정보 입력 완료", Toast.LENGTH_LONG).show();
                Intent intentHome2 = new Intent(this, HomeActivity.class);
                startActivity(intentHome2);
                this.overridePendingTransition(R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}
