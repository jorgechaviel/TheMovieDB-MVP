package com.jchaviel.themoviedb.tvshowlist.di;

import com.jchaviel.themoviedb.adapters.OnItemClickListener;
import com.jchaviel.themoviedb.adapters.TVShowListAdapter;
import com.jchaviel.themoviedb.tvshowlist.mvp.interactors.TVShowListInteractor;
import com.jchaviel.themoviedb.tvshowlist.mvp.interactors.TVShowListInteractorImpl;
import com.jchaviel.themoviedb.libs.base.EventBus;
import com.jchaviel.themoviedb.libs.base.ImageLoader;
import com.jchaviel.themoviedb.model.TVShowInfo;
import com.jchaviel.themoviedb.network.TVShowClient;
import com.jchaviel.themoviedb.network.TVShowService;
import com.jchaviel.themoviedb.tvshowlist.mvp.presenters.TVShowListPresenter;
import com.jchaviel.themoviedb.tvshowlist.mvp.presenters.TVShowListPresenterImpl;
import com.jchaviel.themoviedb.tvshowlist.mvp.repositories.TVShowListRepository;
import com.jchaviel.themoviedb.tvshowlist.mvp.repositories.TVShowListRepositoryImpl;
import com.jchaviel.themoviedb.tvshowlist.mvp.views.TVShowListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jchaviel on 6/7/17.
 */
@Module
public class TVShowListModule {
    TVShowListView view;
    OnItemClickListener onItemClickListener;

    public TVShowListModule(TVShowListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides @Singleton
    TVShowListView provideTVShowListView() {
        return this.view;
    }

    @Provides @Singleton
    TVShowListPresenter provideTVShowListPresenter(EventBus eventBus, TVShowListView view, TVShowListInteractor listInteractor) {
        return new TVShowListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides @Singleton
    TVShowListInteractor provideTVShowListInteractor(TVShowListRepository repository) {
        return new TVShowListInteractorImpl(repository);
    }


    @Provides @Singleton
    TVShowListRepository provideTVShowListRepository(EventBus eventBus, TVShowService service) {
        return new TVShowListRepositoryImpl(eventBus, service);
    }

    @Provides @Singleton
    TVShowListAdapter provideTVShowsAdapter(List<TVShowInfo> tvShows, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new TVShowListAdapter(tvShows, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides @Singleton
    List<TVShowInfo> provideTVShowsList() {
        return new ArrayList<TVShowInfo>();
    }

    @Provides @Singleton
    TVShowService providesTVShowService(){
        return new TVShowClient().getTVShowClient();
    }
}
