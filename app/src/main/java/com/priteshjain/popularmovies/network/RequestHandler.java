package com.priteshjain.popularmovies.network;

import com.priteshjain.popularmovies.constants.ApiEndpoint;
import com.priteshjain.popularmovies.models.Movie;

import java.util.List;

import rx.Observable;

public class RequestHandler {
    ApiEndpoint endpointClient;

    public RequestHandler(){
        endpointClient = new HttpClient().getClient();
    }
    public Observable<List<Movie>> getPopularMovies() {
        return endpointClient.getPopularMovies();
    }
    public Observable<List<Movie>> getTopRatedMovies() {
        return endpointClient.getPopularMovies();
    }
}
