package com.example.android.coinmarketexample.ui;

import com.example.android.coinmarketexample.base.BasePresenter;
import com.example.android.coinmarketexample.base.BaseView;
import com.example.android.coinmarketexample.data.model.Ticker;

import java.util.List;

public class MainContract {

    interface View extends BaseView {
        void showData(List<Ticker> tickerList);
    }

    abstract class Presenter extends BasePresenter {
        abstract void loadData();
    }

}
