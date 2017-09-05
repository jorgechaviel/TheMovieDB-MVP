package com.jchaviel.themoviedb.tvshowlist.mvp.presenters;

import com.jchaviel.themoviedb.tvshowlist.events.TVShowListEvent;
import com.jchaviel.themoviedb.tvshowlist.mvp.interactors.TVShowListInteractor;
import com.jchaviel.themoviedb.libs.base.EventBus;
import com.jchaviel.themoviedb.tvshowlist.mvp.views.TVShowListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by jchaviel on 6/6/2017.
 */

public class TVShowListPresenterImpl implements TVShowListPresenter{

    private EventBus eventBus;
    private TVShowListView view;
    private TVShowListInteractor listInteractor;

    public TVShowListPresenterImpl(EventBus eventBus, TVShowListView view, TVShowListInteractor listInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.listInteractor = listInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
        listInteractor.checkIfHasTheBaseImageURL();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }


    @Override
    public void getTVShowList() {
        if(this.view != null){
            view.showProgress();
            view.hideUIElements();
        }
        listInteractor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(TVShowListEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case TVShowListEvent.READ_EVENT:
                    if(event.getTVShowList() != null) {
                        view.hideProgress();
                        view.setTVShowList(event.getTVShowList());
                        view.setPopularTVShowsType();
                        view.showUIElements();
                    }
                    break;
            }
        }
    }

    @Override
    public TVShowListView getView() {
        return this.view;
    }
}