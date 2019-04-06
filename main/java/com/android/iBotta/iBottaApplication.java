package com.android.iBotta;

import android.app.Application;

import com.android.iBotta.di.ApplicationComponent;
import com.android.iBotta.di.ApplicationModule;
import com.android.iBotta.di.DaggerApplicationComponent;
import com.android.iBotta.di.NetworkModule;

import timber.log.Timber;

public class iBottaApplication extends Application {

    private static iBottaApplication iBottaApplication;
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //Dagger
        setUpDagger();

        //Timber
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private static ApplicationComponent getDaggerComponent() {
        return applicationComponent;
    }


    public static ApplicationComponent getApplicationComponent() {
        return iBottaApplication.getDaggerComponent();
    }

    private void setUpDagger() {
        iBottaApplication = this;
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
        applicationComponent.inject(this);
    }
}
