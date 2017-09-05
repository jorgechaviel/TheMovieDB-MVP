package com.jchaviel.themoviedb.tvshowlist.mvp.views;

import com.jchaviel.themoviedb.model.TVShowInfo;

import java.util.List;

/**
 * Created by jchaviel on 6/6/17.
 */

public interface TVShowListView {
    void setTVShowList(List<TVShowInfo> data);
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void onGetTVShowError(String error);

    void setPopularTVShowsType();
}
