package com.jchaviel.themoviedb.tvshowdetails.mvp.presenters;

import com.jchaviel.themoviedb.tvshowdetails.events.TVShowDetailsEvent;
import com.jchaviel.themoviedb.libs.base.EventBus;
import com.jchaviel.themoviedb.tvshowdetails.mvp.interactors.TVShowDetailsInteractor;
import com.jchaviel.themoviedb.tvshowdetails.mvp.views.TVShowDetailsView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by jchaviel on 6/10/2017.
 */

public class TVShowDetailsPresenterImpl implements TVShowDetailsPresenter {

    private EventBus eventBus;
    private TVShowDetailsView view;
    private TVShowDetailsInteractor tvShowDetailsInteractor;

    public TVShowDetailsPresenterImpl(EventBus eventBus, TVShowDetailsView view, TVShowDetailsInteractor tvShowDetailsInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.tvShowDetailsInteractor = tvShowDetailsInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }


    @Override
    public void getTVShowDetails(String tvShowId) {
        if(this.view != null){
            view.showProgress();
            view.hideUIElements();
        }
        tvShowDetailsInteractor.executeGetTVShowInfo(tvShowId);
    }

    @Override
    @Subscribe
    public void onEventMainThread(TVShowDetailsEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case TVShowDetailsEvent.READ_EVENT_SHOWINFO:
                    String error = event.getError();
                    if(error == null) {
                        tvShowDetailsInteractor.executeGetTVShowCredits(event.getTvShowInfo().getTv_id());
                        view.setTVShowInfo(event.getTvShowInfo());
                    } else {
                        view.hideProgress();
                        view.onGetTVShowDetailsError(error);
                    }
                    break;
                case TVShowDetailsEvent.READ_EVENT_CAST:
                    view.hideProgress();
                    if(event.getTVShowCredits() != null) {
                        view.setTVShowCredits(event.getTVShowCredits());
                    }
                    view.showUIElements();
                    break;
            }
        }
    }

    @Override
    public TVShowDetailsView getView() {
        return this.view;
    }
}