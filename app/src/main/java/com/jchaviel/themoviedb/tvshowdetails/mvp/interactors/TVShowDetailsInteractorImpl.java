package com.jchaviel.themoviedb.tvshowdetails.mvp.interactors;

import com.jchaviel.themoviedb.tvshowdetails.mvp.repositories.TVShowDetailsRepository;

/**
 * Created by jchaviel on 6/10/2017.
 */

public class TVShowDetailsInteractorImpl implements TVShowDetailsInteractor {
    private TVShowDetailsRepository repository;

    public TVShowDetailsInteractorImpl(TVShowDetailsRepository repository) {
        this.repository = repository;
    }


    @Override
    public void executeGetTVShowInfo(String tvShowId) {
        repository.getTVShowInfo(tvShowId);
    }

    @Override
    public void executeGetTVShowCredits(String tvShowId) {
        repository.getTVShowCredits(tvShowId);
    }

}
