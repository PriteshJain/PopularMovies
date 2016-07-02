package com.priteshjain.popularmovies.models;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priteshjain on 02/07/16.
 */
public class PaginatedMovie extends BaseModel {
    private String page;
    private List<Movie> results;

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

    public PaginatedMovie(Parcel in) {
        page = in.readString();
        results = new ArrayList<Movie>();
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
