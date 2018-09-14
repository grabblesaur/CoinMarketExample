package com.example.android.coinmarketexample.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TickerResponse {

    @SerializedName("data")
    private List<Ticker> mData;
    @SerializedName("metadata")
    private Metadata mMetadata;

    public List<Ticker> getData() {
        return mData;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }
}
