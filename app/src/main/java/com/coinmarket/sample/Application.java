package com.coinmarket.sample;

import com.coinmarket.sample.di.AppComponent;
import com.coinmarket.sample.di.AppModule;
import com.coinmarket.sample.di.DaggerAppComponent;

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
