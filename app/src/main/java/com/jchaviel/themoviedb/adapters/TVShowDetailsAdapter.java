package com.jchaviel.themoviedb.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jchaviel.themoviedb.R;
import com.jchaviel.themoviedb.libs.base.ImageLoader;
import com.jchaviel.themoviedb.model.Character;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jchaviel on 6/10/2017.
 */

public class TVShowDetailsAdapter extends RecyclerView.Adapter<TVShowDetailsAdapter.ViewHolder> {

    private List<Character> tvShowCredits;
    private ImageLoader imageLoader;

    public TVShowDetailsAdapter(List<Character> tvShowCredits, ImageLoader imageLoader) {
        this.tvShowCredits = tvShowCredits;
        this.imageLoader = imageLoader;
    }

    public void setTVShowCredits(List<Character> tvShowCredits) {
        this.tvShowCredits = tvShowCredits;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_tvshow_details, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Character currentTVShowCredit = tvShowCredits.get(position);

        imageLoader.load(holder.imgTVShowCastProfilePath, currentTVShowCredit.getProfile_path());
        holder.txtTVShowCastName.setText(currentTVShowCredit.getName());
        holder.txtTVShowCastCharacter.setText(String.valueOf(currentTVShowCredit.getCharacter()));
    }

    @Override
    public int getItemCount() {
        return tvShowCredits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgTVShowCastProfilePath)
        ImageView imgTVShowCastProfilePath;
        @Bind(R.id.txtTVShowCastName)
        AppCompatTextView txtTVShowCastName;
        @Bind(R.id.txtTVShowCastCharacter)
        AppCompatTextView txtTVShowCastCharacter;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
