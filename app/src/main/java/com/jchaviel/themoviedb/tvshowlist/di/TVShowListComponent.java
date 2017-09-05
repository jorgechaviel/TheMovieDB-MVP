package com.jchaviel.themoviedb.tvshowlist.di;

import com.jchaviel.themoviedb.adapters.TVShowListAdapter;
import com.jchaviel.themoviedb.tvshowlist.mvp.presenters.TVShowListPresenter;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by jchaviel on 6/7/17.
 */

@Singleton
@Component(modules = {TVShowListModule.class, LibsModule.class})
public interface TVShowListComponent {
        TVShowListPresenter getPresenter();
        TVShowListAdapter getAdapter();
}
