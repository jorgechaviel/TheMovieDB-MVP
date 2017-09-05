package com.jchaviel.themoviedb.tvshowdetails.di;

import com.jchaviel.themoviedb.tvshowlist.di.LibsModule;
import com.jchaviel.themoviedb.libs.base.ImageLoader;
import com.jchaviel.themoviedb.adapters.TVShowDetailsAdapter;
import com.jchaviel.themoviedb.tvshowdetails.mvp.presenters.TVShowDetailsPresenter;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by jchaviel on 6/10/17.
 */

@Singleton
@Component(modules = {TVShowDetailsModule.class, LibsModule.class})
public interface TVShowDetailsComponent {
        ImageLoader getImageLoader();
        TVShowDetailsPresenter getPresenter();
        TVShowDetailsAdapter getAdapter();
}
