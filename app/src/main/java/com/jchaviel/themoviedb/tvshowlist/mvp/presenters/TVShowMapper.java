package com.jchaviel.themoviedb.tvshowlist.mvp.presenters;

import android.text.TextUtils;
import com.jchaviel.themoviedb.TheMovieDBApp;
import com.jchaviel.themoviedb.model.Mapper;
import com.jchaviel.themoviedb.model.TVShow;
import com.jchaviel.themoviedb.model.TVShowInfo;

/**
 * Created by jchaviel on 8/7/17.
 */

public class TVShowMapper extends Mapper<TVShow, TVShowInfo> {

    @Override
    public TVShowInfo toModel(TVShow tvShow) {
        TVShowInfo tvShowInfo = new TVShowInfo();

        tvShowInfo.setTv_id(tvShow.getId());
        tvShowInfo.setTitle(tvShow.getName());
        tvShowInfo.setPopularity(tvShow.getPopularity());
        tvShowInfo.setOverview(tvShow.getOverview());
        tvShowInfo.setYearOfRelease(tvShow.getFirst_air_date());
        tvShowInfo.setUserScore((tvShow.getVote_average() / 10) * 100);

        if(!TextUtils.isEmpty(tvShow.getPoster_path())) {
            tvShowInfo.setSmallCover(buildCompleteImageURL(tvShow.getPoster_path(), "w154"));
            tvShowInfo.setBigCover(buildCompleteImageURL(tvShow.getBackdrop_path(), "w500"));
        }
        return tvShowInfo;
    }

    @Override
    public TVShowInfo deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, TVShowInfo.class);
    }

    public static String buildCompleteImageURL(String path, String size) {
        if(TheMovieDBApp.localData.hasBaseImageURL()) {
            return TheMovieDBApp.localData.getBaseImageURL() + size + path;
        } else {
            return path;
        }
    }
}
