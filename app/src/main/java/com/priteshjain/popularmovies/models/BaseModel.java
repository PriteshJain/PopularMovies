package com.priteshjain.popularmovies.models;

import android.os.Parcelable;

/**
 * Created by priteshjain on 28/06/16.
 */
public abstract class BaseModel implements Parcelable{
    @Override
    public int describeContents() {
        return 0;
    }

}
