package com.jchaviel.themoviedb.tvshowlist.di;

import android.app.Activity;

import com.jchaviel.themoviedb.libs.GlideImageLoader;
import com.jchaviel.themoviedb.libs.GreenRobotEventBus;
import com.jchaviel.themoviedb.libs.base.EventBus;
import com.jchaviel.themoviedb.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jchaviel on 6/7/17.
 */
@Module
public class LibsModule {
    Activity activity;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Activity activity) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (activity != null) {
            imageLoader.setLoaderContext(activity);
        }
        return imageLoader;
    }

    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }

}
