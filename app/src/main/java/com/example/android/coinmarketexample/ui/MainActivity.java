package com.example.android.coinmarketexample.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.coinmarketexample.Application;
import com.example.android.coinmarketexample.R;
import com.example.android.coinmarketexample.base.BaseActivity;
import com.example.android.coinmarketexample.data.model.Ticker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.am_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.am_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.am_progress_bar)
    ProgressBar mProgressBar;

    private TickerAdapter mTickerAdapter;

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Application.getAppComponent().inject(this);
        mMainPresenter.attachView(this);
        setUpToolbar(mToolbar);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    private void initViews() {
        mTickerAdapter = new TickerAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mTickerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.loadData();
    }

    @Override
    public void showData(List<Ticker> tickerList) {
        if (mTickerAdapter != null) {
            mTickerAdapter.addTickers(tickerList);
        }
    }

    @Override
    public void setLoadingIndicator(boolean b) {
        mProgressBar.setVisibility(b ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(b ? View.GONE : View.VISIBLE);
    }
}
