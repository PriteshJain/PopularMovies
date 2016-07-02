package com.priteshjain.popularmovies.constants;

import com.priteshjain.popularmovies.models.PaginatedMovie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiEndpoint {
    @GET("/3/movie/popular")
    Observable<PaginatedMovie> getPopularMovies(@Query("page") String page);

    @GET("/3/movie/top_rated")
    Observable<PaginatedMovie> getTopRatedMovies(@Query("page") String page);
}
