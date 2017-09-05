package com.jchaviel.themoviedb;

import android.app.Application;

import com.jchaviel.themoviedb.activities.TVShowDetailsActivity;
import com.jchaviel.themoviedb.activities.TVShowListActivity;
import com.jchaviel.themoviedb.adapters.OnItemClickListener;
import com.jchaviel.themoviedb.tvshowlist.di.DaggerTVShowListComponent;
import com.jchaviel.themoviedb.tvshowlist.di.LibsModule;
import com.jchaviel.themoviedb.tvshowlist.di.TVShowListComponent;
import com.jchaviel.themoviedb.tvshowlist.di.TVShowListModule;
import com.jchaviel.themoviedb.tvshowlist.mvp.views.TVShowListView;
import com.jchaviel.themoviedb.tvshowdetails.di.DaggerTVShowDetailsComponent;
import com.jchaviel.themoviedb.tvshowdetails.di.TVShowDetailsComponent;
import com.jchaviel.themoviedb.tvshowdetails.di.TVShowDetailsModule;
import com.jchaviel.themoviedb.tvshowdetails.mvp.views.TVShowDetailsView;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by jchaviel on 6/6/2017.
 */

public class TheMovieDBApp extends Application {

    public static SharedPreferencesManager localData;

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        configureLocalData();
    }

    private void configureLocalData() {
        localData = new SharedPreferencesManager(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        localData = null;
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    public TVShowListComponent getTVShowListComponent(TVShowListActivity activity, TVShowListView view, OnItemClickListener onItemClickListener) {
        return DaggerTVShowListComponent.builder()
                .libsModule(new LibsModule(activity))
                .tVShowListModule(new TVShowListModule(view, onItemClickListener))
                .build();
    }

    public TVShowDetailsComponent getTVShowDetailsComponent(TVShowDetailsActivity activity, TVShowDetailsView view) {
        return DaggerTVShowDetailsComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .tVShowDetailsModule(new TVShowDetailsModule(view))
                .build();
    }

}
