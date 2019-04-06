package com.android.iBotta.presenter;

import com.android.iBotta.view.OfferDetailContract;

public class OfferDetailPresenter implements OfferDetailContract.presenter {

    private static final String TAG = OfferDetailPresenter.class.getSimpleName();

    private OfferDetailContract.View view;

    public OfferDetailPresenter(OfferDetailContract.View view) {
        this.view = view;
    }


    @Override
    public void setOfferFavoraite(boolean setFav) {
        if(setFav) {
            view.onOfferSetFav();
        } else {
            view.onOfferResetFav();
        }
    }
}
