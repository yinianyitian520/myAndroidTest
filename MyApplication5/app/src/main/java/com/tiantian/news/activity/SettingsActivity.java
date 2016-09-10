package com.tiantian.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantian.news.R;

public class SettingsActivity extends Activity{
	TextView title;
	TextView right_text;
    ImageView mback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

        mback = (ImageView) findViewById(R.id.setback);
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        finish();
            }
        });

    }




}
