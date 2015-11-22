package com.alengeo.lfg.services;

import com.alengeo.lfg.TestObjects;
import com.alengeo.lfg.client.BackendApiClient;
import com.alengeo.lfg.models.User;
import com.alengeo.lfg.sessions.SessionManager;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginService {

    private static final String ENDPOINT_LOGIN = "login";
    private static final String PARAM_ACCESS_TOKEN = "fb_access_token";

    private static final String FIELD_API_TOKEN = "api_token";
    private static final String FIELD_USER = "user";

    public static void authenticate(String fbToken, final SessionManager sessionManager) {
        RequestParams params = new RequestParams();
        System.out.println(PARAM_ACCESS_TOKEN + ": " + fbToken);
        params.put(PARAM_ACCESS_TOKEN, fbToken);
        BackendApiClient.post(ENDPOINT_LOGIN, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String apiToken = response.getString(FIELD_API_TOKEN);
                    String userJson = response.getJSONObject(FIELD_USER).toString();
                    Gson gson = new Gson();
                    User user = gson.fromJson(userJson, User.class);
                    sessionManager.createSession(apiToken, user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                String testApiToken = "test_api_token";
                sessionManager.createSession(testApiToken, TestObjects.getUser());
            }
        });
    }
}
