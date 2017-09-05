package com.jchaviel.themoviedb.tvshowdetails.events;

import com.jchaviel.themoviedb.model.Character;
import com.jchaviel.themoviedb.model.TVShowInfo;
import java.util.List;

/**
 * Created by jchaviel on 6/10/2017.
 */

public class TVShowDetailsEvent {
    private int type;
    private String error;
    private TVShowInfo tvShowInfo;
    private List<Character> tvShowCredits;

    public final static int READ_EVENT_SHOWINFO = 0;
    public final static int READ_EVENT_CAST = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Character> getTVShowCredits() {
        return tvShowCredits;
    }

    public void setTVShowCredits(List<Character> tvShowCredits) {
        this.tvShowCredits = tvShowCredits;
    }

    public TVShowInfo getTvShowInfo() {
        return tvShowInfo;
    }

    public void setTvShowInfo(TVShowInfo tvShowInfo) {
        this.tvShowInfo = tvShowInfo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
