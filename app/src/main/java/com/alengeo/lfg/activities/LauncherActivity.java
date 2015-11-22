package com.alengeo.lfg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alengeo.lfg.sessions.SessionManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.logout();

        Intent intent;
        if(sessionManager.isLoggedIn()) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, FacebookActivity.class);
        }
        startActivity(intent);
        finish();
    }



    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
