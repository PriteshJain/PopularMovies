package com.priteshjain.popularmovies.network;

import com.priteshjain.popularmovies.constants.ApiEndpoint;
import com.priteshjain.popularmovies.models.PaginatedMovie;

import rx.Observable;

public class RequestHandler {
    ApiEndpoint endpointClient;

    public RequestHandler(){
        endpointClient = new HttpClient().getClient();
    }
    public Observable<PaginatedMovie> getPopularMovies() {
        return endpointClient.getPopularMovies();
    }
    public Observable<PaginatedMovie> getTopRatedMovies() {
        return endpointClient.getPopularMovies();
    }
}
