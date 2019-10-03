package com.autowish;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
LinearLayout linear_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        linear_back=(LinearLayout)findViewById(R.id.linear_back);
        linear_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linear_back:
                ForgotPasswordActivity.this.finish();
                break;
        }
    }
}
