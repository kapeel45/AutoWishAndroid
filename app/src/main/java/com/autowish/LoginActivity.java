package com.autowish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linear_submit;
    TextView text_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linear_submit = (LinearLayout) findViewById(R.id.linear_submit);
        linear_submit.setOnClickListener(this);

        text_register = (TextView) findViewById(R.id.text_register);
        text_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.linear_submit:
               Intent i=new Intent(LoginActivity.this,OTPActivity.class);
               startActivity(i);
                break;

            case R.id.text_register:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
}
