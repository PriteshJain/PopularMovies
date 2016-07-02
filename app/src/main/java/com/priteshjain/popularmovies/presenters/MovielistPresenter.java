package com.priteshjain.popularmovies.presenters;

import android.content.Context;

import com.priteshjain.popularmovies.models.Movie;
import com.priteshjain.popularmovies.models.PaginatedMovie;
import com.priteshjain.popularmovies.network.RequestHandler;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by priteshjain on 28/06/16.
 */
public class MovieListPresenter extends BasePresenter {
    private IMoviesListingView mMoviesView;
    private Context mContext;
    private Subscription mSubscription;

    public MovieListPresenter(IMoviesListingView view, Context context) {
        mMoviesView = view;
        this.mContext = context;
    }

    public void displayMovies(){
        fetchMovies();
    }

    public Subscription fetchMovies() {
        RequestHandler requestHandler = new RequestHandler();

        mSubscription = requestHandler.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mMoviesView.loadingStarted();
                    }
                })
                .subscribe(new Subscriber<PaginatedMovie>() {
                    @Override
                    public void onCompleted() {
                        mMoviesView.loadingComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMoviesView.loadingFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(PaginatedMovie paginatedMovie) {
                        mMoviesView.listMovies(paginatedMovie.getResults());
                    }
                });
        return mSubscription;
    }

}
