package com.jchaviel.themoviedb.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jchaviel.themoviedb.R;
import com.jchaviel.themoviedb.TheMovieDBApp;
import com.jchaviel.themoviedb.libs.base.ImageLoader;
import com.jchaviel.themoviedb.model.Character;
import com.jchaviel.themoviedb.model.TVShowInfo;
import com.jchaviel.themoviedb.tvshowlist.mvp.presenters.TVShowMapper;
import com.jchaviel.themoviedb.adapters.TVShowDetailsAdapter;
import com.jchaviel.themoviedb.tvshowdetails.di.TVShowDetailsComponent;
import com.jchaviel.themoviedb.tvshowdetails.mvp.presenters.TVShowDetailsPresenter;
import com.jchaviel.themoviedb.tvshowdetails.mvp.views.TVShowDetailsView;
import java.util.List;
import at.grabner.circleprogress.CircleProgressView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jchaviel on 6/6/2017.
 */

public class TVShowDetailsActivity extends AppCompatActivity implements TVShowDetailsView {

    public static final String TAG = TVShowDetailsActivity.class.getSimpleName();
    public static final String TVSHOWDETAIL_MODEL = TAG + ".TVSHOWDETAIL_MODEL";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bigCover)
    ImageView bigCover;
    @Bind(R.id.textName)
    TextView textName;
    @Bind(R.id.circleViewUserScore)
    CircleProgressView circleViewUserScore;
    @Bind(R.id.textPopularity)
    TextView textPopularity;
    @Bind(R.id.smallCover)
    ImageView smallCover;
    @Bind(R.id.textOverview)
    TextView textOverview;
    @Bind(R.id.recyclerViewCast)
    RecyclerView recyclerViewCast;
    @Bind(R.id.view_loading)
    LinearLayout viewLoading;
    @Bind(R.id.scrollableContents)
    NestedScrollView scrollableContents;
    @Bind(R.id.layoutContainer)
    CoordinatorLayout layoutContainer;
    @Bind(R.id.layoutCredits)
    LinearLayout layoutCredits;

    private TVShowInfo tvShowInfo;
    private ImageLoader imageLoader;
    private TVShowDetailsAdapter adapter;
    private TVShowDetailsPresenter presenter;
    private TVShowDetailsComponent component;

    public static Intent getCallingIntent(Context context, TVShowInfo tvShowInfo) {
        Intent intentToBeCalled = new Intent(context, TVShowDetailsActivity.class);
        intentToBeCalled.putExtra(TVSHOWDETAIL_MODEL, new TVShowMapper().serializeModel(tvShowInfo));

        return intentToBeCalled;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_details);
        ButterKnife.bind(this);
        setupInjection();
        setupToolbar();
        setupRecyclerView();
        setupImageLoading();

        presenter.onCreate();

        Bundle args = getIntent().getExtras();

        if (args != null) {
            tvShowInfo = new TVShowMapper().deserializeModel(args.getString(TVSHOWDETAIL_MODEL));
            presenter.getTVShowDetails(tvShowInfo.getTv_id());
        }
    }

    private void setupImageLoading() {
        imageLoader.setOnFinishedImageLoadingListener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                Snackbar.make(layoutContainer, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                hideProgress();
                showUIElements();
                return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupInjection() {
        TheMovieDBApp app = (TheMovieDBApp) getApplication();
        component = app.getTVShowDetailsComponent(this, this);
        imageLoader = getImageLoader();
        presenter = getPresenter();
        adapter = getAdapter();
    }

    public ImageLoader getImageLoader() {
        return component.getImageLoader();
    }

    private TVShowDetailsPresenter getPresenter() {
        return component.getPresenter();
    }

    private TVShowDetailsAdapter getAdapter() {
        return component.getAdapter();
    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.tvshowdetails_titletoolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        recyclerViewCast.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewCast.setAdapter(adapter);
    }

    @Override
    public void setTVShowInfo(TVShowInfo tvShowInfo) {
        imageLoader.load(smallCover, tvShowInfo.getSmallCover());
        imageLoader.load(bigCover, tvShowInfo.getBigCover());
        String yearOfRelease =  " (" + tvShowInfo.getYearOfRelease().split("-")[0] + ")";
        textName.setText(tvShowInfo.getTitle() + yearOfRelease);
        circleViewUserScore.setText(String.valueOf(tvShowInfo.getUserScore()));
        circleViewUserScore.setValue(tvShowInfo.getUserScore());
        textPopularity.setText(String.valueOf(tvShowInfo.getPopularity()));
        textOverview.setText(tvShowInfo.getOverview());
    }

    @Override
    public void setTVShowCredits(List<Character> tvShowCredits) {
        if (tvShowCredits != null) {
            layoutCredits.setVisibility(View.VISIBLE);
            adapter.setTVShowCredits(tvShowCredits);
        }
    }

    @Override
    public void showProgress() {
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        viewLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showUIElements() {
        scrollableContents.setVisibility(View.VISIBLE);
        recyclerViewCast.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        scrollableContents.setVisibility(View.INVISIBLE);
        recyclerViewCast.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGetTVShowDetailsError(String error) {
        String msgError = String.format(getString(R.string.tvshowlist_error), error);
        Snackbar.make(layoutContainer, msgError, Snackbar.LENGTH_SHORT).show();
    }

    private void navigateToListScreen() {
        Intent intent = new Intent(this, TVShowListActivity.class);
        startActivity(intent);
    }
}
