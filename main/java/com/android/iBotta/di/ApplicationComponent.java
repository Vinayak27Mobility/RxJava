package com.android.iBotta.di;

import com.android.iBotta.iBottaApplication;
import com.android.iBotta.network.ServiceHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(iBottaApplication iBottaApplication);

    ServiceHelper getServiceHelper();
}
