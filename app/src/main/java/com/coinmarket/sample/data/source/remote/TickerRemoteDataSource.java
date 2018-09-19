package com.coinmarket.sample.data.source.remote;

import android.util.Log;

import com.coinmarket.sample.data.model.Metadata;
import com.coinmarket.sample.data.model.Ticker;
import com.coinmarket.sample.data.model.TickerResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TickerRemoteDataSource {

    private static final String TAG = TickerRemoteDataSource.class.getSimpleName();

    public interface LoadTickersCallback {
        void onMetadataLoaded(Metadata metadata);
        void onTickersLoaded(List<Ticker> tickers);
        void onError(Throwable throwable);
    }

    private ApiService mApiService;

    @Inject
    public TickerRemoteDataSource(ApiService apiService) {
        mApiService = apiService;
    }

    public void loadData(int start, LoadTickersCallback callback) {
        Log.i(TAG, "loadData: start = " + start);
        mApiService.getData(
                "array",
                String.valueOf(start),
                "100",
                "id")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::onError)
                .subscribe(new Observer<TickerResponse>() {
                    Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(TickerResponse tickerResponse) {
                        Log.i(TAG, "onNext: ");
                        callback.onTickersLoaded(tickerResponse.getData());
                        callback.onMetadataLoaded(tickerResponse.getMetadata());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                        if (!mDisposable.isDisposed()) {
                            mDisposable.dispose();
                        }
                    }
                });
    }

    private Observable onError(Throwable throwable) {
        return Observable.create(subscriber -> {
            if (throwable != null && throwable.getMessage() != null) {
                if (throwable instanceof UnknownHostException
                        || throwable instanceof SocketTimeoutException
                        || throwable instanceof ConnectException) {
                    subscriber.onError(new Throwable("Проблемы с интернет соединением"));
                } else if (throwable.getMessage().contains("422")) {
                    subscriber.onError(new Throwable("Неверные данные"));
                } else {
                    subscriber.onError(throwable);
                }
            }
        });
    }
}
