package com.priteshjain.popularmovies.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.priteshjain.popularmovies.R;
import com.priteshjain.popularmovies.constants.Constant;
import com.priteshjain.popularmovies.models.Movie;
import com.priteshjain.popularmovies.presenters.IMoviesDetailView;
import com.priteshjain.popularmovies.presenters.MovieDetailPresenter;
import com.squareup.picasso.Picasso;

public class MovieDetailFragment extends Fragment implements IMoviesDetailView {
    public static String TAG = "MovieDetailFragment";

    private Context mContext;
    private MovieDetailPresenter mMovieDetailPresenter;
    private ImageView mMovieCover;
    private ImageView mMoviePoster;
    private TextView mMovieTitle;
    private TextView mMovieReleaseDate;
    private TextView mMovieRatingmRating;
    private TextView mMovieOverview;
    View rootView;
    Movie mMovie;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    public static MovieDetailFragment getInstance(@NonNull Movie movie)
    {
        Bundle args = new Bundle();
        args.putParcelable(Constant.MOVIE, movie);
        MovieDetailFragment movieDetailsFragment = new MovieDetailFragment();
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    public MovieDetailFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mMovieDetailPresenter = new MovieDetailPresenter(this);
        mMovie = (Movie) getArguments().get(Constant.MOVIE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        setToolbar();
        initLayout();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mMovie != null)
        {
            mMovieDetailPresenter.displayDetails(mMovie);
        }
    }


    public void initLayout(){
        mMovieCover = (ImageView) rootView.findViewById(R.id.cover_poster);
        mMoviePoster = (ImageView) rootView.findViewById(R.id.movie_poster);
        mMovieTitle = (TextView) rootView.findViewById(R.id.movie_title);
        mMovieReleaseDate = (TextView) rootView.findViewById(R.id.movie_release_date);
        mMovieRatingmRating = (TextView) rootView.findViewById(R.id.movie_rating);
        mMovieOverview = (TextView) rootView.findViewById(R.id.movie_description);
    }

    @Override
    public void showDetails(Movie movie) {
        Picasso.with(mContext)
                .load(mMovie.getBackdropUrl())
                .fit().centerCrop()
                .placeholder( R.drawable.progress_animation )
                .into(mMovieCover);
        Picasso.with(mContext)
                .load(mMovie.getMediumPosterUrl())
                .fit().centerCrop()
                .placeholder( R.drawable.progress_animation )
                .into(mMoviePoster);
        mMovieTitle.setText(movie.getTitle());
        mMovieReleaseDate.setText(movie.getReleaseDate());
        mMovieRatingmRating.setText(String.format(getString(R.string.rating), String.valueOf(movie.getVoteAverage())));
        mMovieOverview.setText(movie.getOverview());
    }

    private void setToolbar()
    {
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setTitle(mMovie.getTitle());
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbarLayout.setTitleEnabled(true);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                setHasOptionsMenu(true);
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);

            }
        }
    }
}
