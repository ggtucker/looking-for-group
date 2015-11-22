package com.alengeo.lfg.sessions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.alengeo.lfg.activities.FacebookActivity;
import com.alengeo.lfg.models.User;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

public class SessionManager {
    private Context context;
    private SharedPreferences pref;

    private static final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "SessionPrefs";

    private static final String LOGGED_IN_KEY = "LoggedIn";
    private static final String API_KEY = "ApiToken";
    private static final String USER_KEY = "User";

    private static final String API_TOKEN_PARAM = "api_token";

    public SessionManager(Context context){
        this.context = context;
        this.pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public void createSession(String apiToken, User user) {
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();

        editor.putBoolean(LOGGED_IN_KEY, true);
        editor.putString(API_KEY, apiToken);
        editor.putString(USER_KEY, gson.toJson(user));
        editor.commit();
    }

    public RequestParams getApiRequestParams() {
        RequestParams params = new RequestParams();
        params.put(API_TOKEN_PARAM, getApiToken());
        return params;
    }

    public String getApiToken() {
        return pref.getString(API_KEY, "");
    }

    public User getUser() {
        Gson gson = new Gson();
        String json = pref.getString(USER_KEY, "");
        return gson.fromJson(json, User.class);
    }

    public void persistUser(User user) {
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        editor.putString(USER_KEY, gson.toJson(user));
        editor.commit();
    }

    public void checkLogin() {
        if(!this.isLoggedIn()) {
            redirectToLoginPage();
        }
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        redirectToLoginPage();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(LOGGED_IN_KEY, false);
    }

    private void redirectToLoginPage() {
        Intent i = new Intent(context, FacebookActivity.class);

        // Close all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }
}
