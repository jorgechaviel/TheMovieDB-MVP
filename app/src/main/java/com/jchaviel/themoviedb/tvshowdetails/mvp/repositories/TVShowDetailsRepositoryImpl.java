package com.jchaviel.themoviedb.tvshowdetails.mvp.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jchaviel.themoviedb.BuildConfig;
import com.jchaviel.themoviedb.model.Character;
import com.jchaviel.themoviedb.tvshowdetails.events.TVShowDetailsEvent;
import com.jchaviel.themoviedb.libs.base.EventBus;
import com.jchaviel.themoviedb.model.TVShow;
import com.jchaviel.themoviedb.tvshowlist.mvp.presenters.TVShowMapper;
import com.jchaviel.themoviedb.network.TVShowCreditsResponse;
import com.jchaviel.themoviedb.network.TVShowService;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jchaviel on 6/10/2017.
 */

public class TVShowDetailsRepositoryImpl implements TVShowDetailsRepository {

    private EventBus eventBus;
    private TVShowService service;

    public TVShowDetailsRepositoryImpl(EventBus eventBus, TVShowService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getTVShowInfo(String tvShowId) {
        try {
            RequestParams params = new RequestParams();
            params.put("api_key", BuildConfig.TMDB_API_KEY);
            params.put("tv_id", tvShowId);
            service.searchTVShowDetail(tvShowId, params, new BaseJsonHttpResponseHandler<TVShow>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, TVShow tvShowResponse) {
                    if (tvShowResponse != null) {
                        post(tvShowResponse);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, TVShow errorResponse) {
                    post(throwable.getMessage());
                }

                @Override
                protected TVShow parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    Gson gson = new GsonBuilder().create();
                    TVShow tvShowResponse = gson.fromJson(rawJsonData, TVShow.class);
                    return tvShowResponse;
                }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTVShowCredits(String tvShowId) {
        try {
            RequestParams params = new RequestParams();
            params.put("api_key", BuildConfig.TMDB_API_KEY);
            params.put("tv_id", tvShowId);
            service.searchTVShowCredits(tvShowId, params, new BaseJsonHttpResponseHandler<TVShowCreditsResponse>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, TVShowCreditsResponse tvShowCreditsResponse) {
                    if (!tvShowCreditsResponse.getCast().isEmpty()){
                        Character character = tvShowCreditsResponse.getFirstTVShowCredit();
                        if (character != null) {
                            postTVShowCredits(tvShowCreditsResponse.getCast());
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, TVShowCreditsResponse errorResponse) {
                    postTVShowCredits(throwable.getMessage());
                }

                @Override
                protected TVShowCreditsResponse parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    Gson gson = new GsonBuilder().create();
                    TVShowCreditsResponse tvShowCreditsResponse = gson.fromJson(rawJsonData, TVShowCreditsResponse.class);
                    return tvShowCreditsResponse;
                }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void post(String error, int type, TVShow tvShow){
        TVShowDetailsEvent event = new TVShowDetailsEvent();
        event.setType(type);
        event.setError(error);
        if(tvShow != null){
            event.setTvShowInfo(new TVShowMapper().toModel(tvShow));
        }
        eventBus.post(event);
    }

    private void post(TVShow tvShow) {
        post(null, TVShowDetailsEvent.READ_EVENT_SHOWINFO, tvShow);
    }

    private void post(String error) {
        post(error, TVShowDetailsEvent.READ_EVENT_SHOWINFO, null);
    }

    private void postTVShowCredits(String error, int type, List<Character> tvShowCredits){
        TVShowDetailsEvent event = new TVShowDetailsEvent();
        event.setType(type);
        event.setError(error);
        if(tvShowCredits != null){
            List<Character> castList = new ArrayList<>();
            for(Character cast : tvShowCredits) {
                cast.setProfile_path(new TVShowMapper().buildCompleteImageURL(cast.getProfile_path(), "w154"));
                castList.add(cast);
            }
            event.setTVShowCredits(castList);
        }
        eventBus.post(event);
    }

    private void postTVShowCredits(String error) {
        postTVShowCredits(error, TVShowDetailsEvent.READ_EVENT_CAST, null);
    }

    private void postTVShowCredits(List<Character> tvShowCredits) {
        postTVShowCredits(null, TVShowDetailsEvent.READ_EVENT_CAST, tvShowCredits);
    }

}
