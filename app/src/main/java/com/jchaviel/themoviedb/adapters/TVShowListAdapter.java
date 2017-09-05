package com.jchaviel.themoviedb.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jchaviel.themoviedb.R;
import com.jchaviel.themoviedb.libs.base.ImageLoader;
import com.jchaviel.themoviedb.model.TVShowInfo;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jchaviel on 6/6/2017.
 */

public class TVShowListAdapter extends RecyclerView.Adapter<TVShowListAdapter.ViewHolder> {

    private List<TVShowInfo> tvShowList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public TVShowListAdapter(List<TVShowInfo> tvShowList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.tvShowList = tvShowList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    public void setTVShowList(List<TVShowInfo> tvShowList) {
        this.tvShowList = tvShowList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_tvshow_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TVShowInfo currentTVShow = tvShowList.get(position);

        imageLoader.load(holder.imgTVShow, currentTVShow.getSmallCover());
        holder.txtTVShowTitle.setText(currentTVShow.getTitle());
        holder.txtTVShowPopularity.setText("Popularity: " + String.valueOf(currentTVShow.getPopularity()));
        holder.setOnItemClickListener(currentTVShow, this.onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgTVShow)
        ImageView imgTVShow;
        @Bind(R.id.txtTVShowTitle)
        AppCompatTextView txtTVShowTitle;
        @Bind(R.id.txtTVShowPopularity)
        AppCompatTextView txtTVShowPopularity;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final TVShowInfo currentTVShow, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(currentTVShow);
                }
            });
        }
    }
}
