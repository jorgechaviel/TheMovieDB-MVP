package com.jchaviel.themoviedb.tvshowlist.mvp.interactors;

import com.jchaviel.themoviedb.tvshowlist.mvp.repositories.TVShowListRepository;

/**
 * Created by jchaviel on 6/6/2017.
 */

public class TVShowListInteractorImpl implements TVShowListInteractor{
    private TVShowListRepository repository;

    public TVShowListInteractorImpl(TVShowListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void checkIfHasTheBaseImageURL() {
        repository.checkIfHasTheBaseImageURL();
    }

    @Override
    public void execute() {
        repository.getSavedTVShowList();
    }

}
