package com.jchaviel.themoviedb.tvshowlist.mvp.repositories;

/**
 * Created by jchaviel on 6/6/2017.
 */

public interface TVShowListRepository {
    public final static int TOTAL_RESULT = 1; //cuantas recetas quiero obtener
    void getSavedTVShowList();
    void checkIfHasTheBaseImageURL();
}
