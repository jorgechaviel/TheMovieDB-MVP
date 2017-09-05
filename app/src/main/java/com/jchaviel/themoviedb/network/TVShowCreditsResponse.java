package com.jchaviel.themoviedb.network;
import com.jchaviel.themoviedb.model.Character;

import java.util.List;

/**
 * Created by jchaviel on 6/10/17.
 */

public class TVShowCreditsResponse {
    private int id;
    private List<Character> cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Character> getCast() {
        return cast;
    }

    public void setCast(List<Character> cast) {
        this.cast = cast;
    }

    public Character getFirstTVShowCredit(){
        Character first = null;
        if (!cast.isEmpty()) {
            first = cast.get(0);
        }
        return first;
    }
}
