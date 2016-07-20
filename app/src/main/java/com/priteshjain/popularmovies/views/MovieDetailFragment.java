package com.priteshjain.popularmovies.views;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.priteshjain.popularmovies.R;
import com.priteshjain.popularmovies.constants.Constant;
import com.priteshjain.popularmovies.databinding.FragmentMovieDetailBinding;
import com.priteshjain.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import static android.R.attr.id;

public class MovieDetailFragment extends Fragment {
    public static final String TAG = "MovieDetailFragment";

    private Context mContext;
    private FragmentMovieDetailBinding fragmentMovieDetailBinding;
    private View rootView;
    private Movie mMovie;

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
        mMovie = (Movie) getArguments().get(Constant.MOVIE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMovieDetailBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_movie_detail, container, false);
        rootView = fragmentMovieDetailBinding.getRoot();
        setToolbar();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentMovieDetailBinding.setMovie(mMovie);
        fragmentMovieDetailBinding.notifyChange();
    }

    private void setToolbar()
    {
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsingToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(mContext, id));
        } else {
            //noinspection deprecation
            collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        }

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

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .fit().centerCrop()
                .placeholder( R.drawable.progress_animation )
                .into(view);
    }
}
