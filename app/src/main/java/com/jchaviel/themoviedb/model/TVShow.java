package com.jchaviel.themoviedb.model;

import com.google.gson.annotations.SerializedName;
import com.jchaviel.themoviedb.db.TVShowDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by jchaviel on 6/6/17.
 */
@Table(database = TVShowDatabase.class)
public class TVShow extends BaseModel {
    @SerializedName("id")
    @PrimaryKey private String id;

    @SerializedName("original_name")
    @Column private String original_name;

    @SerializedName("name")
    @Column private String name;

    @SerializedName("poster_path")
    @Column private String poster_path;

    @SerializedName("first_air_date")
    @Column private String first_air_date;

    @SerializedName("vote_count")
    @Column private int vote_count;

    @SerializedName("vote_average")
    @Column private float vote_average;

    @SerializedName("popularity")
    @Column private double popularity;

//    @SerializedName("genre_ids")
//    @Column private List<Integer> genre_ids;

    @SerializedName("original_language")
    @Column private String original_language;

    @SerializedName("backdrop_path")
    @Column private String backdrop_path;

    @SerializedName("overview")
    @Column private String overview;

//    @SerializedName("origin_country")
//    @Column private List<String> origin_country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

//    public List<Integer> getGenre_ids() {
//        return genre_ids;
//    }
//
//    public void setGenre_ids(List<Integer> genre_ids) {
//        this.genre_ids = genre_ids;
//    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

//    public List<String> getOrigin_country() {
//        return origin_country;
//    }
//
//    public void setOrigin_country(List<String> origin_country) {
//        this.origin_country = origin_country;
//    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof TVShow){
            TVShow tvShow = (TVShow) obj;
            equal = this.id.equals(tvShow.getId());
        }

        return equal;
    }
}
