package com.example.android.coinmarketexample.ui;

import com.example.android.coinmarketexample.data.source.TickerRepository;

public class MainPresenter extends MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private TickerRepository mTickerRepository;
    private MainContract.View mMainView;

    public MainPresenter(TickerRepository tickerRepository,
                         MainContract.View mainView) {
        mTickerRepository = tickerRepository;
        mMainView = mainView;
    }

    @Override
    void loadData() {

    }
}
