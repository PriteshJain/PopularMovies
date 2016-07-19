package com.priteshjain.popularmovies.network;

import com.priteshjain.popularmovies.constants.ApiEndpoint;
import com.priteshjain.popularmovies.constants.Constant.MovieListType;
import com.priteshjain.popularmovies.models.PaginatedMovie;

import rx.Observable;

public class RequestHandler {
    ApiEndpoint endpointClient;

    public RequestHandler(){
        endpointClient = new HttpClient().getClient();
    }

    public Observable<PaginatedMovie> getMovies(MovieListType movieListType, String page) {
        Observable<PaginatedMovie> paginatedMovieObservable = null;
        switch (movieListType){
            case POPULAR:
                paginatedMovieObservable = endpointClient.getPopularMovies(page);
                break;
            case TOP_RATED:
                paginatedMovieObservable = endpointClient.getTopRatedMovies(page);
                break;
        }
        return paginatedMovieObservable;
    }
}
