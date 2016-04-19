package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by imsuyeon on 16. 4. 2..
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private BackPressCloseHandler backPressCloseHandler;
    private Button buttonLogin;
    private TextView textViewRegister, textViewFindPW;
    private EditText editTextID, editTextPW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        backPressCloseHandler = new BackPressCloseHandler(this);
        buttonLogin = (Button) findViewById(R.id.button_login);
        textViewRegister = (TextView) findViewById(R.id.textView_register);
        textViewFindPW = (TextView) findViewById(R.id.textView_find_pw);
        editTextID = (EditText) findViewById(R.id.editText_id);
        editTextPW = (EditText) findViewById(R.id.editText_pw);
        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
        textViewFindPW.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                String id = editTextID.getText().toString();
                String pw = editTextPW.getText().toString();

                if (id.equals("") || pw.equals("")) {
                    Toast.makeText(this, "아이디 혹은 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }

                // temp
                String dbID = "";
                String dbPW = "";
                // end temp
                RequestMsgSender loginMsgSender = (RequestMsgSender) new RequestMsgSender().execute("5;" + id + ";" + pw + ";");
                String loginResult = null;

                try {
                    loginResult = loginMsgSender.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (loginResult.equals(false)) {
                    Toast.makeText(this, "아이디 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    editTextID.setText("");
                    editTextPW.setText("");
                    break;
                } else {
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    ////////////////////////replyMsg내용///////////////
                    // true;id;userName;passWd;toeic;age;major;com_type;duty;com_name;gender;univ;certifi;isEmp;
                    //맞춤형major;맞춤형회사타입;맞춤형직무;맞춤형회사이름;맞춤형성별;맞춤형대학;맞춤형나이;찜아이디;
                    /////////////////////////////////////////
                    String[] tokens = loginResult.split(";");
                    for (int i = 1; i < tokens.length; i++) {
                        if (tokens[i].equals("!")) tokens[i] = "";
                    }
                    LoggedInUser.getLoggedinUser().setId(tokens[1]);
                    LoggedInUser.getLoggedinUser().setUserName(tokens[2]);
                    LoggedInUser.getLoggedinUser().setPassWd(tokens[3]);
                    LoggedInUser.getLoggedinUser().setToeic(Integer.parseInt(tokens[4]));
                    LoggedInUser.getLoggedinUser().setAge(Integer.parseInt(tokens[5]));
                    LoggedInUser.getLoggedinUser().setMajor(tokens[6]);
                    LoggedInUser.getLoggedinUser().setCom_type(tokens[7]);
                    LoggedInUser.getLoggedinUser().setDuty(tokens[8]);
                    LoggedInUser.getLoggedinUser().setCom_name(tokens[9]);
                    LoggedInUser.getLoggedinUser().setGender(Boolean.parseBoolean(tokens[10]));
                    LoggedInUser.getLoggedinUser().setUniv(tokens[11]);
                    LoggedInUser.getLoggedinUser().setCertifi(tokens[12]);
                    LoggedInUser.getLoggedinUser().setIsEmp(Boolean.parseBoolean(tokens[13]));
                    LoggedInUser.getLoggedinUser().setSearch_major(tokens[14]);
                    LoggedInUser.getLoggedinUser().setSearch_com_type(tokens[15]);
                    LoggedInUser.getLoggedinUser().setSearch_duty(tokens[16]);
                    LoggedInUser.getLoggedinUser().setSearch_com_name(tokens[17]);
                    LoggedInUser.getLoggedinUser().setSearch_gender(Boolean.parseBoolean(tokens[18]));
                    LoggedInUser.getLoggedinUser().setSearch_univ(tokens[19]);
                    LoggedInUser.getLoggedinUser().setSearch_age(Integer.parseInt(tokens[20]));
                    LoggedInUser.getLoggedinUser().setFav_ids(tokens[21]);

                    for (int i = 1; i < tokens.length; i++) {
                        Log.d("login 정보 받아온 것, 저장한 것: ", tokens[i]);
                    }

                    Intent intentHome = new Intent(this, HomeActivity.class);
                    startActivity(intentHome);
                    this.overridePendingTransition(R.anim.animation_enter_right2left,
                            R.anim.animation_leave_right2left);
                    editTextID.setText("");
                    editTextPW.setText("");
                }
                break;
            case R.id.textView_register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                this.overridePendingTransition(R.anim.animation_enter_right2left,
                        R.anim.animation_leave_right2left);
                break;
            case R.id.textView_find_pw:
                Intent intentFindPW = new Intent(this, FindPasswordActivity.class);
                startActivity(intentFindPW);
                this.overridePendingTransition(R.anim.animation_enter_right2left, R.anim.animation_leave_right2left);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
