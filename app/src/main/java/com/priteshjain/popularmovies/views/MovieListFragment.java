package com.priteshjain.popularmovies.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.priteshjain.popularmovies.R;
import com.priteshjain.popularmovies.adapters.MovieListAdapter;
import com.priteshjain.popularmovies.models.Movie;
import com.priteshjain.popularmovies.presenters.IMoviesListingView;
import com.priteshjain.popularmovies.presenters.MovieListPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class MovieListFragment extends Fragment implements IMoviesListingView {
    public static String TAG = "MovieListFragment";

    private Context mContext;
    private MovieListAdapter mMovieListAdapter;
    private List<Movie> mMovies = new ArrayList<>(20);
    private MovieListPresenter mMovieListPresenter;
    private Subscription mMoviesSubscription;
    View rootView;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    public MovieListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mMovieListAdapter = new MovieListAdapter(mMovies, mContext, this);
        mMovieListPresenter = new MovieListPresenter(this, mContext);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        initLayout();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieListPresenter.displayMovies();
    }

    @Override
    public void listMovies(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        mMovieListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onMovieClicked(Movie movie) {
        Log.i(TAG, "onMovieClicked: " + movie.getId());
    }

    @Override
    public void loadingStarted() {
        Log.i(TAG, "loadingStarted: ");
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Log.e(TAG, "loadingFailed: " + errorMessage);
    }

    @Override
    public void loadingComplete() {
        Log.i(TAG, "loadingComplete: ");
    }

    public void initLayout(){
        RecyclerView moviesListing = (RecyclerView) rootView.findViewById(R.id.movies_list);
        moviesListing.setHasFixedSize(true);
        int columns = 2;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);
        moviesListing.setLayoutManager(layoutManager);
        moviesListing.setAdapter(mMovieListAdapter);
    }
}
