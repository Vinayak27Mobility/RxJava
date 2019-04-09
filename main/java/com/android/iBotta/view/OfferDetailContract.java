package com.android.iBotta.view;

public interface OfferDetailContract {
    interface View {
        void onOfferSetFav();

        void onOfferResetFav();
    }

    interface presenter {
        void setOfferFavorite(boolean setFav);
    }
}
