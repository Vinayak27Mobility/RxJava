package com.android.iBotta.di;


import com.android.iBotta.view.impl.OfferActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = OfferModule.class)
public interface OfferComponent {
    void inject(OfferActivity offerActivity);
}
