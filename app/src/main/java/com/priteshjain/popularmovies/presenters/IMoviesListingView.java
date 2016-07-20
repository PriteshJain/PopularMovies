package com.priteshjain.popularmovies.presenters;

import com.priteshjain.popularmovies.models.Movie;

import java.util.List;

public interface IMoviesListingView {
    void listMovies(List<Movie> movies, String currentPage);
    void onMovieClicked(Movie movie);
    void loadingStarted();
    void loadingFailed(String errorMessage);
    void loadingComplete();

}
