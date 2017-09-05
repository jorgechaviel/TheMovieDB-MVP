package com.jchaviel.themoviedb.model;

import java.util.List;

/**
 * Created by jchaviel on 6/6/17.
 */

public class TVShowInfo {

    String tv_id;
    String title;
    float userScore;
    String yearOfRelease;
    double popularity;
    String overview;
    String gender;
    String bigCover;
    String smallCover;
    List<String> created_by;
    String homepage;
    String tagline;

    public List<Character> getCast() {
        return cast;
    }

    public void setCast(List<Character> cast) {
        this.cast = cast;
    }

    private List<Character> cast;

    public String getTv_id() {
        return tv_id;
    }

    public void setTv_id(String tv_id) {
        this.tv_id = tv_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getUserScore() {
        return userScore;
    }

    public void setUserScore(float userScore) {
        this.userScore = userScore;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBigCover() {
        return bigCover;
    }

    public void setBigCover(String bigCover) {
        this.bigCover = bigCover;
    }

    public String getSmallCover() {
        return smallCover;
    }

    public void setSmallCover(String smallCover) {
        this.smallCover = smallCover;
    }

    public List<String> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<String> created_by) {
        this.created_by = created_by;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
