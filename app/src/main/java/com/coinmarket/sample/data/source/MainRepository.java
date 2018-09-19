package com.coinmarket.sample.data.source;

import com.coinmarket.sample.data.model.Metadata;
import com.coinmarket.sample.data.model.Ticker;
import com.coinmarket.sample.data.source.remote.TickerRemoteDataSource;

import java.util.List;

import javax.inject.Inject;

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
