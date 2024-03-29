package com.autowish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autowish.CommonUtils.CommonMethods;
import com.autowish.CommonUtils.UserSessionManager;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linear_submit;
    TextView text_register;
    UserSessionManager sessionManager;
    EditText edit_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new UserSessionManager(LoginActivity.this);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        linear_submit = (LinearLayout) findViewById(R.id.linear_submit);
        linear_submit.setOnClickListener(this);

        text_register = (TextView) findViewById(R.id.text_register);
        text_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.linear_submit:
                CommonMethods.hideSoftKeyboard(LoginActivity.this);
                if (edit_phone.getText().toString().trim().equalsIgnoreCase("")) {
                    CommonMethods.Error_Alert_Dialog(LoginActivity.this, getResources().getString(R.string.enter_phone));
                } else if (!CommonMethods.isValidMobile(edit_phone.getText().toString().trim())) {
                    CommonMethods.Error_Alert_Dialog(LoginActivity.this, getResources().getString(R.string.valid_phone));
                } else {
                    sessionManager.savePhoneNumber(edit_phone.getText().toString().trim());
                    Intent i = new Intent(LoginActivity.this, OTPActivity.class);
                    startActivity(i);
//                    if (CommonMethods.checkConnection()) {
//                        postdata();
//                    } else {
//                        CommonMethods.Error_Alert_Dialog(LoginActivity.this, getResources().getString(R.string.internetconnection));
//                    }
                }


                break;

            case R.id.text_register:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
}
