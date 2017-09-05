package com.jchaviel.themoviedb.tvshowdetails.di;

import com.jchaviel.themoviedb.libs.base.EventBus;
import com.jchaviel.themoviedb.libs.base.ImageLoader;
import com.jchaviel.themoviedb.model.Character;
import com.jchaviel.themoviedb.network.TVShowClient;
import com.jchaviel.themoviedb.network.TVShowService;
import com.jchaviel.themoviedb.adapters.TVShowDetailsAdapter;
import com.jchaviel.themoviedb.tvshowdetails.mvp.interactors.TVShowDetailsInteractor;
import com.jchaviel.themoviedb.tvshowdetails.mvp.interactors.TVShowDetailsInteractorImpl;
import com.jchaviel.themoviedb.tvshowdetails.mvp.presenters.TVShowDetailsPresenter;
import com.jchaviel.themoviedb.tvshowdetails.mvp.presenters.TVShowDetailsPresenterImpl;
import com.jchaviel.themoviedb.tvshowdetails.mvp.repositories.TVShowDetailsRepository;
import com.jchaviel.themoviedb.tvshowdetails.mvp.repositories.TVShowDetailsRepositoryImpl;
import com.jchaviel.themoviedb.tvshowdetails.mvp.views.TVShowDetailsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jchaviel on 6/10/17.
 */
@Module
public class TVShowDetailsModule {
    TVShowDetailsView view;

    public TVShowDetailsModule(TVShowDetailsView view) {
        this.view = view;
    }

    @Provides @Singleton
    TVShowDetailsView provideTVShowDetailsView() {
        return this.view;
    }

    @Provides @Singleton
    TVShowDetailsPresenter provideTVShowDetailsPresenter(EventBus eventBus, TVShowDetailsView view, TVShowDetailsInteractor listInteractor) {
        return new TVShowDetailsPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides @Singleton
    TVShowDetailsInteractor provideTVShowDetailsInteractor(TVShowDetailsRepository repository) {
        return new TVShowDetailsInteractorImpl(repository);
    }


    @Provides @Singleton
    TVShowDetailsRepository provideTVShowDetailsRepository(EventBus eventBus, TVShowService service) {
        return new TVShowDetailsRepositoryImpl(eventBus, service);
    }

    @Provides @Singleton
    TVShowDetailsAdapter provideTVShowsAdapter(List<Character> tvShowCredits, ImageLoader imageLoader) {
        return new TVShowDetailsAdapter(tvShowCredits, imageLoader);
    }

    @Provides @Singleton
    List<Character> provideTVShowsCredits() {
        return new ArrayList<Character>();
    }

    @Provides @Singleton
    TVShowService providesTVShowService(){
        return new TVShowClient().getTVShowClient();
    }
}
