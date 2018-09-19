package com.coinmarket.sample.ui;

import com.coinmarket.sample.data.model.Metadata;
import com.coinmarket.sample.data.model.Ticker;
import com.coinmarket.sample.data.source.MainRepository;
import com.coinmarket.sample.data.source.remote.TickerRemoteDataSource;

import java.util.List;

import javax.inject.Inject;

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
