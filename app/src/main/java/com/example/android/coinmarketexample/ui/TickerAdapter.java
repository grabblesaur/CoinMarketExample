package com.example.android.coinmarketexample.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.coinmarketexample.R;
import com.example.android.coinmarketexample.data.model.Ticker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class TickerAdapter extends RecyclerView.Adapter<TickerAdapter.TickerViewHolder> {

    private List<Ticker> mTickerList;

    public TickerAdapter(List<Ticker> tickerList) {
        mTickerList = tickerList;
    }

    @NonNull
    @Override
    public TickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ticker, viewGroup, false);
        return new TickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TickerViewHolder tickerViewHolder, int i) {
        tickerViewHolder.onBind(mTickerList.get(i));
    }

    @Override
    public int getItemCount() {
        return mTickerList.size();
    }

    public void addTickers(List<Ticker> tickerList) {
        mTickerList = tickerList;
        notifyDataSetChanged();
    }

    class TickerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.it_name)
        TextView mName;
        @BindView(R.id.it_symbol)
        TextView mSymbol;

        TickerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(Ticker ticker) {
            mName.setText(ticker.getName());
            mSymbol.setText(ticker.getSymbol());
        }

    }
}