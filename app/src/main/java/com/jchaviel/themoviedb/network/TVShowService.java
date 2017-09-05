package com.jchaviel.themoviedb.network;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;


/**
 * Created by jchaviel on 6/6/17.
 */

public class TVShowService {

    public TVShowService() {

    }

    public void getApiConfigurations(RequestParams params, BaseJsonHttpResponseHandler baseJsonHttpResponseHandler) throws JSONException {
        TVShowClient.get("/configuration", params, baseJsonHttpResponseHandler);
    }

    public void searchPopularTVShows(RequestParams params, BaseJsonHttpResponseHandler baseJsonHttpResponseHandler) throws JSONException {
        TVShowClient.get("/tv/popular", params, baseJsonHttpResponseHandler);
    }

    public void searchTVShowDetail(String tvShowId, RequestParams params, BaseJsonHttpResponseHandler baseJsonHttpResponseHandler) throws JSONException {
        TVShowClient.get("/tv/" + tvShowId, params, baseJsonHttpResponseHandler);
    }

    public void searchTVShowCredits(String tvShowId, RequestParams params, BaseJsonHttpResponseHandler baseJsonHttpResponseHandler) throws JSONException {
        TVShowClient.get("/tv/" + tvShowId + "/credits", params, baseJsonHttpResponseHandler);
    }

}
