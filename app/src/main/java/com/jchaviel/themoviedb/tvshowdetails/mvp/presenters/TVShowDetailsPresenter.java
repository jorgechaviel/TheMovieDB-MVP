package com.jchaviel.themoviedb.tvshowdetails.mvp.presenters;

import com.jchaviel.themoviedb.tvshowdetails.events.TVShowDetailsEvent;
import com.jchaviel.themoviedb.tvshowdetails.mvp.views.TVShowDetailsView;

/**
 * Created by jchaviel on 6/10/2017.
 */

public interface TVShowDetailsPresenter {
    void onCreate();
    void onDestroy();
    void getTVShowDetails(String tvShowId);
    void onEventMainThread(TVShowDetailsEvent event);

    TVShowDetailsView getView();
}
