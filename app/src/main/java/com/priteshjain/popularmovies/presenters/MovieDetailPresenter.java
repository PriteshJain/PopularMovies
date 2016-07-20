package com.priteshjain.popularmovies.presenters;

import com.priteshjain.popularmovies.models.Movie;

public class MovieDetailPresenter extends BasePresenter {
    private final IMoviesDetailView mMoviesView;

    public MovieDetailPresenter(IMoviesDetailView view) {
        mMoviesView = view;
    }

    public void displayDetails(Movie movie){
        mMoviesView.showDetails(movie);
    }

}
