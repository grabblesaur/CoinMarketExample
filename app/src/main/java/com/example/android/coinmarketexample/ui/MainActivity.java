package com.example.android.coinmarketexample.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.coinmarketexample.Application;
import com.example.android.coinmarketexample.R;
import com.example.android.coinmarketexample.base.BaseActivity;
import com.example.android.coinmarketexample.data.model.Ticker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.am_recyclerview)
    RecyclerView mRecyclerView;

    private TickerAdapter mTickerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Application.getAppComponent().inject(this);
        initViews();
    }

    private void initViews() {
        mTickerAdapter = new TickerAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mTickerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showData(List<Ticker> tickerList) {
        if (mTickerAdapter != null) {
            mTickerAdapter.addTickers(tickerList);
        }
    }
}
