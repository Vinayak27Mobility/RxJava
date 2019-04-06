package com.android.iBotta.di;

import android.content.Context;

import com.android.iBotta.iBottaApplication;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class})
public class ApplicationModule {

    private final iBottaApplication application;

    public ApplicationModule(iBottaApplication application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }
}
