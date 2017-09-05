package com.jchaviel.themoviedb.tvshowlist.mvp.presenters;

import com.jchaviel.themoviedb.tvshowlist.events.TVShowListEvent;
import com.jchaviel.themoviedb.tvshowlist.mvp.views.TVShowListView;

/**
 * Created by jchaviel on 6/6/2017.
 */

public interface TVShowListPresenter {
    void onCreate();
    void onDestroy();
    void getTVShowList();
    void onEventMainThread(TVShowListEvent event);

    TVShowListView getView();
}
