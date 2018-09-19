package com.example.android.coinmarketexample.data.source;

import com.example.android.coinmarketexample.data.model.Metadata;
import com.example.android.coinmarketexample.data.model.Ticker;
import com.example.android.coinmarketexample.data.source.remote.TickerRemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class MainRepository {

    private TickerRemoteDataSource mRemoteDataSource;

    @Inject
    public MainRepository(TickerRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public void loadData(int start, TickerRemoteDataSource.LoadTickersCallback callback) {
        mRemoteDataSource.loadData(start, new TickerRemoteDataSource.LoadTickersCallback() {

            @Override
            public void onMetadataLoaded(Metadata metadata) {
                callback.onMetadataLoaded(metadata);
            }

            @Override
            public void onTickersLoaded(List<Ticker> tickers) {
                callback.onTickersLoaded(tickers);
            }

            @Override
            public void onError(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
