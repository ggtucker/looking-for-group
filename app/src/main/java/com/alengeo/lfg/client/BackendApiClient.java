package com.alengeo.lfg.client;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.client.CookieStore;

public class BackendApiClient {
    private static final String BASE_URL = "http://allen.dev.bluepandasystems.com/";

    private static SyncHttpClient client = new SyncHttpClient();
    private static AsyncHttpClient asyncClient = new AsyncHttpClient();

    private BackendApiClient() {}

    public static RequestHandle get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        return client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static RequestHandle post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        return client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static RequestHandle asyncGet(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        return asyncClient.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static RequestHandle asyncPost(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        return asyncClient.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void setCookieStore(CookieStore cookieStore) {
        client.setCookieStore(cookieStore);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
