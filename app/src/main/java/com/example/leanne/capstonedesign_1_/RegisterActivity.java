package com.example.leanne.capstonedesign_1_;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by imsuyeon on 16. 4. 4..
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText editTextId, editTextPassword, editTextConfirmPassword, editTextName;
    private Button buttonRegister, buttonCheckID;
    private boolean IDexistCheck;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        IDexistCheck = false;
    }

    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextId = (EditText) findViewById(R.id.editText_id);
        editTextPassword = (EditText) findViewById(R.id.editText_pw);
        editTextConfirmPassword = (EditText) findViewById(R.id.editText_pw_confirm);

        buttonRegister = (Button) findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(this);
        buttonCheckID = (Button) findViewById(R.id.button_check_id);
        buttonCheckID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_check_id:
                if(editTextId.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "아아디를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    // 중복확인 버튼을 누른 경우 해야 할 것
                    String checkInputID = null;
                    checkInputID = editTextId.getText().toString();
                    RequestMsgSender IDexistMsgSender = (RequestMsgSender) new RequestMsgSender().execute("2;" + checkInputID + ";");
                    try {
                        IDexistCheck = Boolean.parseBoolean(IDexistMsgSender.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if (IDexistCheck == true) {
                        Toast.makeText(getApplicationContext(), "사용할 수 있는 ID 입니다.", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "이미 존재하는 ID 입니다.", Toast.LENGTH_LONG).show();
                    }
                }
                    break;
                case R.id.button_register:
                    String inputID = editTextId.getText().toString();
                    String inputPW = editTextPassword.getText().toString();
                    String confirmPassword = editTextConfirmPassword.getText().toString();
                    String inputName = editTextName.getText().toString();

                    if (inputID.equals("") || inputPW.equals("") || confirmPassword.equals("")
                            || inputName.equals("")) {
                        Toast.makeText(getApplicationContext(), "입력되지 않은 부분이 있습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        if (IDexistCheck == false) {
                            Toast.makeText(getApplicationContext(), "ID 중복 여부를 확인해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            if (!inputPW.equals(confirmPassword)) {
                                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                            } else {
                                // SAVE USER INFO INTO DB
                                String registerResult = null;
                                RequestMsgSender RegisterMsgSender = (RequestMsgSender) new RequestMsgSender().execute("3;" + inputName + ";" + inputID + ";" + inputPW + ";" + confirmPassword + ";");
                                try {
                                    registerResult = RegisterMsgSender.get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }

                                if (registerResult.equals("true")) {
                                    LoggedInUser.getLoggedinUser().setUserName(inputName);
                                    LoggedInUser.getLoggedinUser().setId(inputID);
                                    Toast.makeText(getApplicationContext(), "계정만들기 성공!", Toast.LENGTH_LONG).show();
                                    Intent intentExtraInfo = new Intent(RegisterActivity.this, ExtraInfoActivity.class);
                                    startActivity(intentExtraInfo);
                                    overridePendingTransition(R.anim.animation_enter_right2left, R.anim.animation_leave_right2left);
                                    this.finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "계정만들기 실패", Toast.LENGTH_LONG).show();
                                    Intent intentGoLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intentGoLogin);
                                    overridePendingTransition(R.anim.animation_enter_right2left, R.anim.animation_leave_right2left);
                                    this.finish();
                                }

                                //Log.d("after background","taskIsDone == "+DataHolder.getTaskIsDone()+"");
                                //끝날때까지 반복

                                // this.finish();

                            }
                        }

                }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.animation_enter_left2right, R.anim.animation_leave_left2right);
    }
}
