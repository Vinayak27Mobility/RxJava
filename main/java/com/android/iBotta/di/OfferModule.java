package com.android.iBotta.di;

import com.android.iBotta.network.ServiceHelper;
import com.android.iBotta.presenter.OfferPresenter;
import com.android.iBotta.view.OfferContract;

import dagger.Module;
import dagger.Provides;

@Module
public class OfferModule {
    private OfferContract.View view;

    public OfferModule(OfferContract.View mainView) {
        this.view = mainView;
    }

    @Provides
    @ActivityScope
    OfferContract.presenter providePresenter(ServiceHelper serviceHelper) {
        return new OfferPresenter(view, serviceHelper);
    }
}
