package com.priteshjain.popularmovies.models;

import android.os.Parcel;

import com.priteshjain.popularmovies.constants.Constant;

public class Movie extends BaseModel {
    private String id;
    private String title;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private String backdropPath;
    private double voteAverage;

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeDouble(voteAverage);
    }

    private Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        voteAverage = in.readDouble();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getThumbPosterUrl(){
        return Constant.BASE_POSTER_URL + ImageSize.THUMB.getSize() + posterPath;
    }

    public String getMediumPosterUrl(){
        return Constant.BASE_POSTER_URL + ImageSize.MEDIUM.getSize() + posterPath;
    }

    public String getLargePosterUrl(){
        return Constant.BASE_POSTER_URL + ImageSize.LARGE.getSize() + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
    public String getBackdropUrl() {
        return Constant.BASE_POSTER_URL + ImageSize.XLARGE.getSize() + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getRating() {
        return voteAverage  +  "/10";
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
