package com.jchaviel.themoviedb.libs;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.jchaviel.themoviedb.libs.base.ImageLoader;

/**
 * Created by jchaviel on 6/6/2017.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;
    private RequestListener onFinishedLoadingListener;

    public void setLoaderContext(Activity activity) {
        this.glideRequestManager = Glide.with(activity);
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if(onFinishedLoadingListener != null){
            glideRequestManager.load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinishedLoadingListener)
                    .into(imageView);
        }
        else {
            glideRequestManager.load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        if(listener instanceof RequestListener) {
            this.onFinishedLoadingListener = (RequestListener) listener;
        }
    }
}
