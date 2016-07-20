package com.priteshjain.popularmovies.models;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

public class PaginatedMovie extends BaseModel {
    private final String page;
    private final List<Movie> results;

    public static final Creator<PaginatedMovie> CREATOR = new Creator<PaginatedMovie>() {
        @Override
        public PaginatedMovie createFromParcel(Parcel in) {
            return new PaginatedMovie(in);
        }

        @Override
        public PaginatedMovie[] newArray(int size) {
            return new PaginatedMovie[size];
        }
    };

    private PaginatedMovie(Parcel in) {
        page = in.readString();
        results = new ArrayList<>();
        in.readList(results, null);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(page);
        parcel.writeList(results);
    }

    public List<Movie> getResults() {
        return results;
    }
}
