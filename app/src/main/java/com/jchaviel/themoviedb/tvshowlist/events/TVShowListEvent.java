package com.jchaviel.themoviedb.tvshowlist.events;

import com.jchaviel.themoviedb.model.TVShowInfo;
import java.util.List;

/**
 * Created by jchaviel on 6/6/2017.
 */

public class TVShowListEvent {
    private int type;
    private String error;
    private TVShowInfo tvShowInfo;
    private List<TVShowInfo> tvShowList;

    public final static int READ_EVENT = 0;
    public final static int UPDATE_EVENT = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<TVShowInfo> getTVShowList() {
        return tvShowList;
    }

    public void setTVShowList(List<TVShowInfo> tvShowList) {
        this.tvShowList = tvShowList;
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
