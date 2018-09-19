package com.coinmarket.sample.ui;

import com.coinmarket.sample.base.BasePresenter;
import com.coinmarket.sample.base.BaseView;
import com.coinmarket.sample.data.model.Ticker;

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
