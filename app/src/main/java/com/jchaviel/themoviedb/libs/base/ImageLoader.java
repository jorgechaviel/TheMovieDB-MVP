package com.jchaviel.themoviedb.libs.base;

import android.widget.ImageView;

/**
 * Created by jchaviel on 6/6/2017.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);

}
