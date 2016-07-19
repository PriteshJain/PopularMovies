package com.priteshjain.popularmovies.models;

import android.os.Parcelable;

public abstract class BaseModel implements Parcelable{
    @Override
    public int describeContents() {
        return 0;
    }

}
