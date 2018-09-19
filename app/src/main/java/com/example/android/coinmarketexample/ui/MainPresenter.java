package com.example.android.coinmarketexample.ui;

import com.example.android.coinmarketexample.data.model.Metadata;
import com.example.android.coinmarketexample.data.model.Ticker;
import com.example.android.coinmarketexample.data.source.MainRepository;
import com.example.android.coinmarketexample.data.source.remote.TickerRemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainRepository mRepository;
    private MainContract.View mMainView;

    @Inject
    public MainPresenter(MainRepository repository) {
        mRepository = repository;
    }

    @Override
    void attachView(MainContract.View view) {
        mMainView = view;
    }

    @Override
    void detachView() {
        mMainView = null;
    }

    @Override
    void loadData(int start) {
//        mMainView.setLoadingIndicator(true);
        mRepository.loadData(start, new TickerRemoteDataSource.LoadTickersCallback() {

            @Override
            public void onMetadataLoaded(Metadata metadata) {
                
            }

            @Override
            public void onTickersLoaded(List<Ticker> tickers) {
//                mMainView.setLoadingIndicator(false);
                mMainView.showData(tickers);
            }

            @Override
            public void onError(Throwable throwable) {
//                mMainView.setLoadingIndicator(false);
                mMainView.showError(throwable.getMessage());
            }
        });
    }
}
