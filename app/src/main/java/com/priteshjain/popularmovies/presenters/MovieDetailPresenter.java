package com.priteshjain.popularmovies.presenters;

import com.priteshjain.popularmovies.models.Movie;
import com.priteshjain.popularmovies.models.PaginatedMovie;
import com.priteshjain.popularmovies.network.RequestHandler;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MovieDetailPresenter extends BasePresenter {
    private IMoviesDetailView mMoviesView;

    public MovieDetailPresenter(IMoviesDetailView view) {
        mMoviesView = view;
    }

    public void displayDetails(Movie movie){
        mMoviesView.showDetails(movie);
    }

}
