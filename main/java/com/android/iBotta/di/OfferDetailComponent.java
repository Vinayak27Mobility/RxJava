package com.android.iBotta.di;


import com.android.iBotta.view.impl.OfferActivity;
import com.android.iBotta.view.impl.OfferDetailActivity;

import dagger.Component;

@ActivityScope
@Component(
        modules = OfferDetailModule.class)
public interface OfferDetailComponent {
    void inject(OfferDetailActivity offerDetailActivity);
}
