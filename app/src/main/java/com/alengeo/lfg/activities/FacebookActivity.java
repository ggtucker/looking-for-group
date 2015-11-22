package com.alengeo.lfg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alengeo.lfg.R;
import com.alengeo.lfg.services.LoginService;
import com.alengeo.lfg.sessions.SessionManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook);

        this.callbackManager = CallbackManager.Factory.create();

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        registerLoginCallback(sessionManager);
    }

    private void registerLoginCallback(final SessionManager sessionManager) {
        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String fbToken = loginResult.getAccessToken().getToken();
                LoginService.authenticate(fbToken, sessionManager, new Runnable() {
                    @Override
                    public void run() {
                        if (sessionManager.isLoggedIn()) {
                            Intent intent = new Intent(FacebookActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                });
                //Intent intent = new Intent(FacebookActivity.this, SplashActivity.class);
                //startActivity(intent);
            }

            @Override
            public void onCancel() {
                System.out.println("Facebook login cancelled");
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
