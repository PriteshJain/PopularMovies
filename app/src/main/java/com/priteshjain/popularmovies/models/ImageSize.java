package com.priteshjain.popularmovies.models;

public enum ImageSize {
    TINY("w92"),
    THUMB("w154"),
    MEDIUM("w185"),
    LARGE("w385"),
    XLARGE("w500"),
    XXLARGE("w780"),
    ORIGINAL("original");

    private final String size;

    ImageSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
