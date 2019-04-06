package com.android.iBotta.di;

import com.android.iBotta.network.ServiceHelper;
import com.android.iBotta.presenter.OfferDetailPresenter;
import com.android.iBotta.presenter.OfferPresenter;
import com.android.iBotta.view.OfferContract;
import com.android.iBotta.view.OfferDetailContract;

import dagger.Module;
import dagger.Provides;

@Module
public class OfferDetailModule {
    private OfferDetailContract.View view;

    public OfferDetailModule(OfferDetailContract.View mainView) {
        this.view = mainView;
    }

    @Provides
    @ActivityScope
    OfferDetailContract.presenter providePresenter() {
        return new OfferDetailPresenter(view);
    }
}
