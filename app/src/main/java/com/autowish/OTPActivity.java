package com.autowish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autowish.CommonUtils.CommonMethods;

import java.util.Timer;
import java.util.TimerTask;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {
LinearLayout linear_submit,linear_button_resend;
TextView text_timer;
    TextView timer;
    int time = 0, total_time = 120;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        text_timer=(TextView)findViewById(R.id.text_timer);
        text_timer.setText("02:00");
        linear_button_resend=(LinearLayout)findViewById(R.id.linear_button_resend);
        linear_button_resend.setVisibility(View.GONE);
        linear_submit=(LinearLayout)findViewById(R.id.linear_submit);
        linear_submit.setOnClickListener(this);
        timer_for_otp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linear_submit:
                break;

            case R.id.linear_button_resend:
                break;
        }
    }
    private void timer_for_otp() {
        text_timer.setVisibility(View.VISIBLE);
        total_time = 120;
        final Timer t = new Timer();
//Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {
                                      //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                      runOnUiThread(new Runnable() {

                                          @Override
                                          public void run() {
                                              if (total_time != 0) {
                                                  text_timer.setText(CommonMethods.calculateTime(total_time));
                                                  total_time -= 1;
                                              } else {
                                                  t.cancel();
//                                                  edit_one.setText("");
//                                                  edit_two.setText("");
//                                                  edit_three.setText("");
//                                                  edit_four.setText("");
                                                  timer.setVisibility(View.GONE);
                                                  linear_button_resend.setVisibility(View.VISIBLE);
                                                  linear_submit.setVisibility(View.GONE);
                                              }
                                          }

                                      });
                                  }

                              },
//Set how long before to start calling the TimerTask (in milliseconds)
                0,
//Set the amount of time between each execution (in milliseconds)
                1000);
    }
}
