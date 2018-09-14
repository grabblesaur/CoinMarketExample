package com.example.android.coinmarketexample.data.model;

import com.google.gson.annotations.SerializedName;

public class USD {
    @SerializedName("price")
    private double mPrice;
    @SerializedName("volume_24h")
    private double mVolume24Hours;
    @SerializedName("market_cap")
    private float mMarketCap;
    @SerializedName("percent_change_1h")
    private float mPercentChange1Hour;
    @SerializedName("percent_change_24h")
    private float mPercentChange24Hours;
    @SerializedName("percent_change_7d")
    private float mPercentChange7Days;

    public double getPrice() {
        return mPrice;
    }

    public double getVolume24Hours() {
        return mVolume24Hours;
    }

    public float getMarketCap() {
        return mMarketCap;
    }

    public float getPercentChange1Hour() {
        return mPercentChange1Hour;
    }

    public float getPercentChange24Hours() {
        return mPercentChange24Hours;
    }

    public float getPercentChange7Days() {
        return mPercentChange7Days;
    }
}
