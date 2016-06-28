package com.priteshjain.popularmovies.constants;

import com.priteshjain.popularmovies.models.Movie;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiEndpoint {
    @GET("/movies/popular")
    Observable<List<Movie>> getPopularMovies();

    @GET("/movies/top_rated")
    Observable<List<Movie>> getTopRatedMovies();
}
