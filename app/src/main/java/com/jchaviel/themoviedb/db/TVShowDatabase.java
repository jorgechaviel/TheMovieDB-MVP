package com.jchaviel.themoviedb.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by jchaviel on 6/6/17.
 */
@Database(name = TVShowDatabase.NAME, version = TVShowDatabase.VERSION)
public class TVShowDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "TVShows";
}
