package com.alengeo.lfg.client;

import com.loopj.android.http.*;

public class BackendApiClient {
    private static final String BASE_URL = "http://allen.dev.bluepandasystems.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    private BackendApiClient() {}

    public static RequestHandle asyncGet(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        return client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static RequestHandle asyncPost(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        return client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
