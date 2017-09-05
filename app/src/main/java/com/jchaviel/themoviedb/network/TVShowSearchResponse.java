package com.jchaviel.themoviedb.network;

import com.jchaviel.themoviedb.model.TVShow;
import java.util.List;

/**
 * Created by jchaviel on 6/6/17.
 */


public class TVShowSearchResponse {

    private int total_results;

    private List<TVShow> results;

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public TVShow getFirstTVShow(){
        TVShow first = null;
        if (!results.isEmpty()) {
            first = results.get(0);
        }
        return first;
    }

    public List<TVShow> getResults() {
        return results;
    }

    public void setResults(List<TVShow> results) {
        this.results = results;
    }
}
