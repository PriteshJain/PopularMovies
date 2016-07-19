package com.priteshjain.popularmovies.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.priteshjain.popularmovies.R;
import com.priteshjain.popularmovies.adapters.MovieListAdapter;
import com.priteshjain.popularmovies.constants.Constant;
import com.priteshjain.popularmovies.models.Movie;
import com.priteshjain.popularmovies.presenters.IMoviesListingView;
import com.priteshjain.popularmovies.presenters.MovieListPresenter;
import com.priteshjain.popularmovies.util.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class MovieListFragment extends Fragment implements IMoviesListingView {
    public static String TAG = "MovieListFragment";

    private Context mContext;
    private MovieListAdapter mMovieListAdapter;
    private List<Movie> mMovies = new ArrayList<>(20);
    private Constant.MovieListType mMovieListType;
    private MovieListPresenter mMovieListPresenter;
    private ProgressBar mProgressbar;
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

    public static MovieListFragment getInstance(@NonNull Constant.MovieListType tabName)
    {
        Bundle args = new Bundle();
        args.putString(Constant.TABITEM, tabName.name());
        MovieListFragment movieListFragment = new MovieListFragment();
        movieListFragment.setArguments(args);
        return movieListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mMovieListAdapter = new MovieListAdapter(mMovies, mContext, this);
        String tabName = getArguments().getString(Constant.TABITEM);
        mMovieListType = Constant.MovieListType.getByName(tabName);
        mMovieListPresenter = new MovieListPresenter(this, mMovieListType);
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
        mMovieListPresenter.displayMovies("1");
    }

    @Override
    public void listMovies(List<Movie> movies) {
        mMovies.addAll(movies);
        mMovieListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.getInstance(movie);
        ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_listing, movieDetailFragment)
                .addToBackStack(MovieDetailFragment.TAG)
                .commit();

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
        mProgressbar.setVisibility(View.INVISIBLE);
    }

    public void initLayout(){
        RecyclerView moviesListing = (RecyclerView) rootView.findViewById(R.id.movies_list);
        mProgressbar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        moviesListing.setHasFixedSize(true);
        int columns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columns);
        moviesListing.setLayoutManager(gridLayoutManager);
        moviesListing.setAdapter(mMovieListAdapter);
        moviesListing.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                Log.i(TAG, "onLoadMore: " + current_page);
                mMovieListPresenter.displayMovies(current_page + "");
            }
        });
    }

    @Override
    public void onPause() {
        mMovieListPresenter.stopFetchingMovies();
        super.onPause();
    }
}
