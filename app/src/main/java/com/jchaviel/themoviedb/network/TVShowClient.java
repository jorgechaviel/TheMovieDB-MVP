package com.jchaviel.themoviedb.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by jchaviel on 6/6/17.
 */

public class TVShowClient {
    private final static String BASE_URL = "https://api.themoviedb.org/3";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        String finalURL = AsyncHttpClient.getUrlWithQueryString(true,getAbsoluteUrl(url),params);
        client.get(finalURL, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static TVShowService service;

    public static TVShowService getTVShowClient()
    {
        if (service == null){
            service = new TVShowService();
        }
        return service;
    }
}
