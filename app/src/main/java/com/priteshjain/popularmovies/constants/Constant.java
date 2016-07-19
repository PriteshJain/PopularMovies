package com.priteshjain.popularmovies.constants;

public class Constant {
    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/";
    public static final String MOVIE = "MovieItem";
    public static final String TABITEM = "TabItem";

    public enum MovieListType {
        POPULAR("Popular"),
        TOP_RATED("Top Rated");

        private final String type;

        MovieListType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public static MovieListType getByName(String name) {
            MovieListType movieListType = null;
            for (int i = 0; i < MovieListType.values().length ; i++) {
                if (MovieListType.values()[i].name().equals(name)){
                    movieListType = MovieListType.values()[i];
                }
            }
            return movieListType;
        }
    }
}
