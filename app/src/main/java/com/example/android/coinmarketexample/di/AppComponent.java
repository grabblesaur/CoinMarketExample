package com.example.android.coinmarketexample.di;

import com.example.android.coinmarketexample.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    void inject(MainActivity activity);
}
