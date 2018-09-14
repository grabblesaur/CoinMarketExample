package com.example.android.coinmarketexample;

import com.example.android.coinmarketexample.di.AppComponent;
import com.example.android.coinmarketexample.di.AppModule;
import com.example.android.coinmarketexample.di.DaggerAppComponent;

public class Application extends android.app.Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = createComponent();
    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
