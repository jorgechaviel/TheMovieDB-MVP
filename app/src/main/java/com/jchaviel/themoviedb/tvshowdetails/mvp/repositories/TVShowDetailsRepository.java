package com.jchaviel.themoviedb.tvshowdetails.mvp.repositories;

/**
 * Created by jchaviel on 6/10/2017.
 */

public interface TVShowDetailsRepository {
    public final static int TOTAL_RESULT = 1; //cuantas recetas quiero obtener
    void getTVShowInfo(String tvShowId);
    void getTVShowCredits(String tvShowId);
}
