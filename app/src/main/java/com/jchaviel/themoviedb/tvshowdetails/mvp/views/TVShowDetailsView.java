package com.jchaviel.themoviedb.tvshowdetails.mvp.views;

import com.jchaviel.themoviedb.model.Character;
import com.jchaviel.themoviedb.model.TVShowInfo;

import java.util.List;

/**
 * Created by jchaviel on 6/10/17.
 */

public interface TVShowDetailsView {
    void setTVShowInfo(TVShowInfo tvShowInfo);
    void setTVShowCredits(List<Character> tvShowCredits);
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void onGetTVShowDetailsError(String error);
}
