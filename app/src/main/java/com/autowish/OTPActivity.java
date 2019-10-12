package com.autowish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autowish.CommonUtils.CommonMethods;
import com.autowish.CommonUtils.UserSessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linear_submit, linear_button_resend;
    TextView text_timer;
    TextView timer;
    EditText edit_six, edit_five, edit_four, edit_three, edit_two, edit_one;
    Timer t = new Timer();
    int time = 0, total_time = 120;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId = "", phonenumber = "";
    ProgressDialog progressDialog;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        sessionManager = new UserSessionManager(OTPActivity.this);
        text_timer = (TextView) findViewById(R.id.text_timer);
        text_timer.setText("02:00");
        linear_button_resend = (LinearLayout) findViewById(R.id.linear_button_resend);
        linear_button_resend.setVisibility(View.GONE);
        linear_submit = (LinearLayout) findViewById(R.id.linear_submit);
        linear_submit.setOnClickListener(this);

        edit_one = (EditText) findViewById(R.id.edit_one);
        edit_one.setSelection(edit_one.getText().length());
        edit_two = (EditText) findViewById(R.id.edit_two);
//        edit_two.setSelection(edit_two.getText().length());
        edit_three = (EditText) findViewById(R.id.edit_three);
        edit_three.setSelection(edit_three.getText().length());
        edit_four = (EditText) findViewById(R.id.edit_four);
        edit_four.setSelection(edit_four.getText().length());
        edit_five = (EditText) findViewById(R.id.edit_five);
        edit_five.setSelection(edit_five.getText().length());
        edit_six = (EditText) findViewById(R.id.edit_six);
        edit_six.setSelection(edit_six.getText().length());
        check_all_fields();
        edit_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1) {
                    edit_two.requestFocus();
                    edit_two.setSelection(edit_two.getText().length());
                }
                check_all_fields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_one.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit_two.requestFocus();
                edit_two.setSelection(edit_two.getText().length());
                check_all_fields();
                return false;
            }
        });

        edit_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1) {
                    edit_three.requestFocus();
                    edit_three.setSelection(edit_three.getText().length());
                }
                check_all_fields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_two.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit_three.requestFocus();
                edit_three.setSelection(edit_three.getText().length());
                check_all_fields();
                return false;
            }
        });
        edit_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1) {
                    edit_four.requestFocus();
                    edit_four.setSelection(edit_four.getText().length());
                }
                check_all_fields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_three.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit_four.requestFocus();
                edit_four.setSelection(edit_four.getText().length());
                check_all_fields();
                return false;
            }
        });
        edit_four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1) {
                    edit_five.requestFocus();
                    edit_five.setSelection(edit_five.getText().length());
                }
                check_all_fields();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_four.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit_five.requestFocus();
                edit_five.setSelection(edit_five.getText().length());
                check_all_fields();
                return false;
            }
        });
        edit_five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1) {
                    edit_six.requestFocus();
                }
                check_all_fields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_five.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit_six.requestFocus();
                edit_six.setSelection(edit_six.getText().length());
                check_all_fields();
                return false;
            }
        });
        edit_six.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1) {
                    linear_submit.setAlpha((float) 1);
                    linear_submit.setClickable(true);
                    linear_submit.setEnabled(true);
                }
                check_all_fields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edit_six.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit_six.requestFocus();
                check_all_fields();
                return false;
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.processing_dilaog));
        timer_for_otp();
        phoneauth();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_submit:
                String otp_ed = edit_one.getText().toString().trim() + edit_two.getText().toString().trim()
                        + edit_three.getText().toString().trim() + edit_four.getText().toString().trim() +
                        edit_five.getText().toString().trim() + edit_six.getText().toString().trim();
                if (!otp_ed.equalsIgnoreCase("")) {
                    if (progressDialog!=null && !progressDialog.isShowing()) {
                        progressDialog.show();
                    }
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp_ed);
                    signInWithPhoneAuthCredential(credential);
                } else {
                    CommonMethods.Error_Alert_Dialog(OTPActivity.this, getResources().getString(R.string.enter_otp_warn));
                }

                break;

            case R.id.linear_button_resend:
                edit_one.setText("");
                edit_two.setText("");
                edit_three.setText("");
                edit_four.setText("");
                edit_five.setText("");
                edit_six.setText("");
                edit_one.requestFocus();
                timer.setText("02:00");
                timer_for_otp();
                send_otpcode();
                phoneauth();
                break;
        }
    }

    private void timer_for_otp() {
        text_timer.setVisibility(View.VISIBLE);
        total_time = 120;
        t = new Timer();
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

    private void phoneauth() {
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
                String otp = phoneAuthCredential.getSmsCode();
                if (otp != null) {
                    Toast.makeText(OTPActivity.this, otp, Toast.LENGTH_SHORT).show();
                    if (otp.length() == 6) {
                        edit_one.setText(Character.toString(otp.charAt(0)));
                        edit_two.setText(Character.toString(otp.charAt(1)));
                        edit_three.setText(Character.toString(otp.charAt(2)));
                        edit_four.setText(Character.toString(otp.charAt(3)));
                        edit_five.setText(Character.toString(otp.charAt(4)));
                        edit_six.setText(Character.toString(otp.charAt(5)));
                    }
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (progressDialog!=null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                CommonMethods.Error_Alert_Dialog(OTPActivity.this, getResources().getString(R.string.verification_failed));
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationId, forceResendingToken);
                mResendToken = forceResendingToken;
                mVerificationId = verificationId;
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        };

        send_otpcode();
    }

    private void send_otpcode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                sessionManager.getPhoneNumber(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (progressDialog!=null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            final FirebaseUser user = task.getResult().getUser();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    updateUI();
                                }
                            }, 300);


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                if (progressDialog!=null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                CommonMethods.Error_Alert_Dialog(OTPActivity.this, getResources().getString(R.string.invalid_code));
                            }
                        }
                    }
                });
    }

    private void updateUI() {
        t.cancel();
        sessionManager.createLoginSession("1","Demo User","","",sessionManager.getPhoneNumber());
        Intent i = new Intent(OTPActivity.this, HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        OTPActivity.this.finish();
    }

    //check empty fields-------------------------
    private void check_all_fields() {
        if (((edit_one.getText().toString().trim().equalsIgnoreCase("") || edit_two.getText().toString().trim().equalsIgnoreCase("")
                || edit_three.getText().toString().trim().equalsIgnoreCase("") || edit_four.getText().toString().trim().equalsIgnoreCase("")))
                || edit_five.getText().toString().trim().equalsIgnoreCase("") || edit_six.getText().toString().trim().equalsIgnoreCase("")) {
            linear_submit.setAlpha((float) .4);
            linear_submit.setClickable(false);
            linear_submit.setEnabled(false);
            linear_submit.setVisibility(View.VISIBLE);
        } else {
            linear_submit.setAlpha((float) 1);
            linear_submit.setClickable(true);
            linear_submit.setEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        t.cancel();
    }
}
