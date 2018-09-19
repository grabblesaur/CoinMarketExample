package com.coinmarket.sample.data.source.remote;

import com.coinmarket.sample.data.model.TickerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("ticker")
    Observable<TickerResponse> getData(@Query("structure") String structure,
                                       @Query("start") String start,
                                       @Query("limit") String limit,
                                       @Query("sort") String sortBy);

}
