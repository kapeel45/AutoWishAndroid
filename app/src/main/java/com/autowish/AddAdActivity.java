package com.autowish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AddAdActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image_back;
    EditText edit_vnumber;
    Spinner  spinner_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ad);
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        edit_vnumber = (EditText) findViewById(R.id.edit_vnumber);
        spinner_color=(Spinner)findViewById(R.id.spinner_color);


        ArrayAdapter<String> coloradapter = new ArrayAdapter<String>
                (AddAdActivity.this, R.layout.spinner_text_transparent,
                        getResources().getStringArray(R.array.v_color));
        spinner_color.setAdapter(coloradapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                AddAdActivity.this.finish();
                break;
        }
    }
}
