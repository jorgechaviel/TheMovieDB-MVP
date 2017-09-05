package com.jchaviel.themoviedb;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jchaviel on 6/9/2017.
 */

public class SharedPreferencesManager {

    public static final String TAG = SharedPreferencesManager.class.getSimpleName();
    public static final int PRIVATE_MODE = 0;

    public static final String BASE_IMAGE_URL = TAG + ".BASE_IMAGE_URL ";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(TAG, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void storeBaseImageURL(String url) {
        editor.putString(BASE_IMAGE_URL, url);
        editor.commit();
    }

    public boolean hasBaseImageURL() {
        return sharedPreferences.contains(BASE_IMAGE_URL);
    }

    public String getBaseImageURL() {
        return sharedPreferences.getString(BASE_IMAGE_URL, "");
    }
}
