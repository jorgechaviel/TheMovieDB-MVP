package com.jchaviel.themoviedb.tvshowdetails.mvp.interactors;

/**
 * Created by jchaviel on 6/10/2017.
 */

public interface TVShowDetailsInteractor {
    void executeGetTVShowInfo(String tvShowId);
    void executeGetTVShowCredits(String tvShowId);
}
