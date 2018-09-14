package com.example.android.coinmarketexample.data.source.remote;

import com.example.android.coinmarketexample.data.model.TickerResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("ticker")
    Single<TickerResponse> getData(@Query("structure") String structure,
                                   @Query("limit") String limit,
                                   @Query("sort") String sortBy);

}
