package com.android.iBotta.view;

import com.android.iBotta.model.Offer;

import java.util.List;

public interface OfferContract {
    interface View {
        void onOfferListAvailable(List<Offer> offerList);

        void onOfferListFetchError();

        void navigateToOfferDetails(Offer item);
    }

    interface presenter {
        void getOfferList();
    }
}
