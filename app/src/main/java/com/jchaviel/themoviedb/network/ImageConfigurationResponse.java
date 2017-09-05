package com.jchaviel.themoviedb.network;

import com.jchaviel.themoviedb.model.ConfigurationModel;

/**
 * Created by jchaviel on 6/9/17.
 */

public class ImageConfigurationResponse {
    private ConfigurationModel images;

    public ConfigurationModel getImages() {
        return images;
    }

    public void setImages(ConfigurationModel images) {
        this.images = images;
    }
}
