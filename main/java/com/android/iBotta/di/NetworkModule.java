package com.android.iBotta.di;

import android.content.Context;

import com.android.iBotta.iBottaApplication;
import com.android.iBotta.network.OfferAPI;
import com.android.iBotta.network.ServiceHelper;
import com.android.iBotta.view.impl.OfferActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AndroidSupportInjectionModule.class)
public class
NetworkModule {

    @Provides
    @Singleton
    ServiceHelper provideServiceHelper(OfferAPI offerAPI, Context context) {
        return new ServiceHelper(offerAPI, context);
    }

    @Provides
    @Singleton
    OfferAPI provideOfferApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.google.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OfferAPI.class);
    }
}
