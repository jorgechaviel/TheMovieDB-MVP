package com.jchaviel.themoviedb.tvshowlist.mvp.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jchaviel.themoviedb.BuildConfig;
import com.jchaviel.themoviedb.TheMovieDBApp;
import com.jchaviel.themoviedb.tvshowlist.events.TVShowListEvent;
import com.jchaviel.themoviedb.libs.base.EventBus;
import com.jchaviel.themoviedb.model.TVShow;
import com.jchaviel.themoviedb.network.ImageConfigurationResponse;
import com.jchaviel.themoviedb.network.TVShowSearchResponse;
import com.jchaviel.themoviedb.network.TVShowService;
import com.jchaviel.themoviedb.tvshowlist.mvp.presenters.TVShowMapper;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jchaviel on 6/6/2017.
 */

public class TVShowListRepositoryImpl implements TVShowListRepository {

    private int tvShowPage = 1;

    private EventBus eventBus;
    private TVShowService service;

    public TVShowListRepositoryImpl(EventBus eventBus, TVShowService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getSavedTVShowList() {
        try {
            RequestParams params = new RequestParams();
            params.put("api_key", BuildConfig.TMDB_API_KEY);
            params.put("page", tvShowPage);
            service.searchPopularTVShows(params, new BaseJsonHttpResponseHandler<TVShowSearchResponse>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, TVShowSearchResponse tvShowSearchResponse) {
                    if (tvShowSearchResponse.getTotal_results() > 0){
                        TVShow tvShow = tvShowSearchResponse.getFirstTVShow();
                        if (tvShow != null) {
                            post(tvShowSearchResponse.getResults());
                        } else {
                            post(tvShowSearchResponse.toString());
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, TVShowSearchResponse errorResponse) {
                    post(errorResponse.toString());
                }

                @Override
                protected TVShowSearchResponse parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    Gson gson = new GsonBuilder().create();
                    TVShowSearchResponse tvShowSearchResponse = gson.fromJson(rawJsonData, TVShowSearchResponse.class);
                    return tvShowSearchResponse;
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }

                @Override
                public void onCancel() {
                    super.onCancel();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkIfHasTheBaseImageURL() {
        try {
            RequestParams params = new RequestParams();
            params.put("api_key", BuildConfig.TMDB_API_KEY);
            service.getApiConfigurations(params, new BaseJsonHttpResponseHandler<ImageConfigurationResponse>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, ImageConfigurationResponse imageConfigurationResponse) {
                    TheMovieDBApp.localData.storeBaseImageURL(imageConfigurationResponse.getImages().getBase_url());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, ImageConfigurationResponse errorResponse) {
                    post(errorResponse.toString());
                }

                @Override
                protected ImageConfigurationResponse parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    Gson gson = new GsonBuilder().create();
                    ImageConfigurationResponse imageConfigurationResponse = gson.fromJson(rawJsonData, ImageConfigurationResponse.class);
                    return imageConfigurationResponse;
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }

                @Override
                public void onCancel() {
                    super.onCancel();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void post(String error, int type, List<TVShow> tvShowList){
        TVShowListEvent event = new TVShowListEvent();
        event.setType(type);
        event.setError(error);
        if(tvShowList != null){
            TVShowMapper tvShowMapper = new TVShowMapper();
            tvShowMapper.serializeModels(tvShowMapper.toModels(tvShowList));
            event.setTVShowList(tvShowMapper.toModels(tvShowList));
        }
        eventBus.post(event);
    }

    private void post(List<TVShow> tvShowList) {
        post(null, TVShowListEvent.READ_EVENT, tvShowList);
    }

    private void post(String error) {
        post(error, TVShowListEvent.READ_EVENT, null);
    }

}
