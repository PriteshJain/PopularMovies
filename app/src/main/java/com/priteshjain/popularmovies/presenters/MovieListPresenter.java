package com.priteshjain.popularmovies.presenters;

import com.priteshjain.popularmovies.constants.Constant;
import com.priteshjain.popularmovies.models.PaginatedMovie;
import com.priteshjain.popularmovies.network.RequestHandler;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MovieListPresenter extends BasePresenter {
    private final IMoviesListingView mMoviesView;
    private Subscription mSubscription;
    private String mCurrentPage;
    private final Constant.MovieListType mMovieListType;

    public MovieListPresenter(IMoviesListingView view, Constant.MovieListType movieListType) {
        mMoviesView = view;
        mMovieListType = movieListType;
    }

    public void displayMovies(String currentPage){
        this.mCurrentPage = currentPage;
        fetchMovies();
    }

    private void fetchMovies() {
        RequestHandler requestHandler = new RequestHandler();

        mSubscription = requestHandler.getMovies(mMovieListType, mCurrentPage)
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
                        mMoviesView.listMovies(paginatedMovie.getResults(), mCurrentPage);
                    }
                });
    }

    public void stopFetchingMovies(){
        mSubscription.unsubscribe();
    }
}