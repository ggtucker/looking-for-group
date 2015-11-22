package com.alengeo.lfg.services;

import com.alengeo.lfg.client.BackendApiClient;
import com.alengeo.lfg.models.LockedEvent;
import com.alengeo.lfg.models.TentativeEvent;
import com.alengeo.lfg.models.User;
import com.alengeo.lfg.sessions.SessionManager;
import com.alengeo.lfg.util.LFGCallback;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EventService {

    private static final String ENDPOINT_CATEGORY = "events/category";
    private static final String ENDPOINT_CREATE_GROUP = "events/create";
    private static final String PARAM_EVENT = "event";
    private static final String PARAM_CATEGORY = "category";

    public static void createEvent(LockedEvent event, final SessionManager sessionManager,
                                   final LFGCallback<TentativeEvent> callback) {
        Gson gson = new Gson();
        String eventJson = gson.toJson(event);

        RequestParams params = sessionManager.getApiRequestParams();
        params.put(PARAM_EVENT, eventJson);

        BackendApiClient.asyncPost(ENDPOINT_CREATE_GROUP, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Successfully created group");
                String eventJson = response.toString();
                Gson gson = new Gson();
                TentativeEvent event = gson.fromJson(eventJson, TentativeEvent.class);
                callback.execute(event);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(statusCode + " Failed to create group");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println(statusCode + " Failed to create group");
            }
        });
    }

    public static void getEventsByCategory(String category, final SessionManager sessionManager,
                                           final LFGCallback<String> callback) {

        RequestParams params = sessionManager.getApiRequestParams();
        params.put(PARAM_CATEGORY, category);

        BackendApiClient.asyncGet(ENDPOINT_CATEGORY, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                String eventsJson = response.toString();
                callback.execute(eventsJson);
            }
        });
    }
}
