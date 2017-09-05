package com.jchaviel.themoviedb.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import com.jchaviel.themoviedb.R;
import com.jchaviel.themoviedb.TheMovieDBApp;
import com.jchaviel.themoviedb.adapters.OnItemClickListener;
import com.jchaviel.themoviedb.adapters.TVShowListAdapter;
import com.jchaviel.themoviedb.tvshowlist.di.TVShowListComponent;
import com.jchaviel.themoviedb.model.TVShowInfo;
import com.jchaviel.themoviedb.tvshowlist.mvp.presenters.TVShowListPresenter;
import com.jchaviel.themoviedb.tvshowlist.mvp.views.TVShowListView;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jchaviel on 6/9/2017.
 */

public class TVShowListActivity extends AppCompatActivity implements TVShowListView, OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.activity_tvshow_list)
    CoordinatorLayout activityTvshowList;
    @Bind(R.id.view_loading)
    LinearLayout viewLoading;

    private TVShowListAdapter adapter;
    private TVShowListPresenter presenter;
    private TVShowListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_list);
        ButterKnife.bind(this);
        setupInjection();
        setupToolbar();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getTVShowList();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.toolbar)
    public void onToolbarClick() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupInjection() {
        TheMovieDBApp app = (TheMovieDBApp) getApplication();
        component = app.getTVShowListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    public TVShowListAdapter getAdapter() {
        return component.getAdapter();
    }

    public TVShowListPresenter getPresenter() {
        return component.getPresenter();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(TVShowInfo tvShowInfo) {
        startActivity(TVShowDetailsActivity.getCallingIntent(this, tvShowInfo));
    }

    @Override
    public void setTVShowList(List<TVShowInfo> data) {
        adapter.setTVShowList(data);
    }

    @Override
    public void showProgress() {
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        viewLoading.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onGetTVShowError(String error) {
        String msgError = String.format(getString(R.string.tvshowlist_error), error);
        Snackbar.make(activityTvshowList, msgError, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPopularTVShowsType() {
        toolbar.setTitle(String.format(getString(R.string.tv_popular_type)));
    }
}
