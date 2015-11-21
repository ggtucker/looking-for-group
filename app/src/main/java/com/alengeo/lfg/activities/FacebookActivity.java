package com.alengeo.lfg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alengeo.lfg.R;
import com.facebook.FacebookSdk;

public class FacebookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook);
    }
}
