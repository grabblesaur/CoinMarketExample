package com.example.android.coinmarketexample.data.model;

import com.google.gson.annotations.SerializedName;

public class Metadata {
    @SerializedName("timestamp")
    private long mTimestamp;
    @SerializedName("num_cryptocurrencies")
    private int mCount;
    @SerializedName("error")
    private String mError;

    public long getTimestamp() {
        return mTimestamp;
    }

    public int getCount() {
        return mCount;
    }

    public String getError() {
        return mError;
    }
}
