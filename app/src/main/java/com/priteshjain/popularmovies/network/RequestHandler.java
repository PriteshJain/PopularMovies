package com.priteshjain.popularmovies.network;

import com.priteshjain.popularmovies.constants.ApiEndpoint;
import com.priteshjain.popularmovies.models.PaginatedMovie;

import rx.Observable;

public class RequestHandler {
    ApiEndpoint endpointClient;

    public RequestHandler(){
        endpointClient = new HttpClient().getClient();
    }
    public Observable<PaginatedMovie> getPopularMovies(String page) {
        return endpointClient.getPopularMovies(page);
    }
    public Observable<PaginatedMovie> getTopRatedMovies(String page) {
        return endpointClient.getPopularMovies(page);
    }
}
