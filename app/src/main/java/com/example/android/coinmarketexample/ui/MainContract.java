package com.example.android.coinmarketexample.ui;

import com.example.android.coinmarketexample.base.BasePresenter;
import com.example.android.coinmarketexample.base.BaseView;
import com.example.android.coinmarketexample.data.model.Ticker;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {
        void showData(List<Ticker> tickerList);
        void setLoadingIndicator(boolean b);
    }

    abstract class Presenter extends BasePresenter {
        abstract void loadData(int start);
        abstract void attachView(MainContract.View view);
        abstract void detachView();
    }

}
