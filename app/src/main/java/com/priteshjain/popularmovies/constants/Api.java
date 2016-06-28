package com.priteshjain.popularmovies.constants;

public class Api {
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String BASE_MOVIES = BASE_URL + "/movies";
    private static final String POPULAR_MOVIES;
    private static final String TOP_RATED_MOVIES;

    static {
        POPULAR_MOVIES = BASE_MOVIES + "/popular";
        TOP_RATED_MOVIES = BASE_MOVIES + "/top_rated";
    }
}
