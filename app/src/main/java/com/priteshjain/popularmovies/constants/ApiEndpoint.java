package com.priteshjain.popularmovies.constants;

import com.priteshjain.popularmovies.models.PaginatedMovie;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiEndpoint {
    @GET("/3/movie/popular")
    Observable<PaginatedMovie> getPopularMovies();

    @GET("/3/movie/top_rated")
    Observable<PaginatedMovie> getTopRatedMovies();
}
