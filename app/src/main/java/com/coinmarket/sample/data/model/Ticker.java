package com.coinmarket.sample.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class Ticker {

    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("symbol")
    private String mSymbol;
    @SerializedName("website_slug")
    private String mWebsiteSlug;
    @SerializedName("rank")
    private int mRank;
    @SerializedName("circulating_supply")
    private float mCirulatingSupply;
    @SerializedName("total_supply")
    private float mTotalSupply;
    @SerializedName("max_supply")
    private float mMaxSupply;
    @SerializedName("quotes")
    private LinkedHashMap<String, USD> mQuotes;
    @SerializedName("last_updated")
    private long mLastUpdated;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public String getWebsiteSlug() {
        return mWebsiteSlug;
    }

    public int getRank() {
        return mRank;
    }

    public float getCirulatingSupply() {
        return mCirulatingSupply;
    }

    public float getTotalSupply() {
        return mTotalSupply;
    }

    public float getMaxSupply() {
        return mMaxSupply;
    }

    public LinkedHashMap<String, USD> getQuotes() {
        return mQuotes;
    }

    public long getLastUpdated() {
        return mLastUpdated;
    }
}
