package com.example.android.coinmarketexample.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
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

    @Inject
    MainPresenter mMainPresenter;

    private TickerAdapter mTickerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean loading = true;
    private int previousTotal = 0;
    private int visibleThreshold = 3;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int start = 1;

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
        mProgressBar.setVisibility(View.GONE);
        mTickerAdapter = new TickerAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mTickerAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = mRecyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    start += 500;
                    mMainPresenter.loadData(start);
                    loading = true;
                }
            }
        });

        mMainPresenter.loadData(1);
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
