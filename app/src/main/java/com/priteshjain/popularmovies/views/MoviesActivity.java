package com.priteshjain.popularmovies.views;

import android.os.Bundle;

import com.priteshjain.popularmovies.R;


public class MoviesActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // TODO fix this
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_listing, new MovieListFragment())
                    .commit();
        }

    }
}
