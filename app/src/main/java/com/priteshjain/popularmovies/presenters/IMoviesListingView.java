package com.priteshjain.popularmovies.presenters;

import com.priteshjain.popularmovies.models.Movie;

import java.util.List;

public interface IMoviesListingView {
    public void listMovies(List<Movie> movies);
    public void onMovieClicked(Movie movie);
    public void loadingStarted();
    public void loadingFailed(String errorMessage);
    public void loadingComplete();

}
