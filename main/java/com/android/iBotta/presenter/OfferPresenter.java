package com.android.iBotta.presenter;

import com.android.iBotta.model.Offer;
import com.android.iBotta.network.ServiceHelper;
import com.android.iBotta.view.OfferContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class OfferPresenter implements OfferContract.presenter {

    private OfferContract.View view;
    private ServiceHelper serviceHelper;

    public OfferPresenter(OfferContract.View view, ServiceHelper serviceHelper) {
        this.view = view;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public void getOfferList() {
//        Call<List<Offer>> offerCall = serviceHelper.getOfferList();
//
//        offerCall.enqueue(new Callback<List<Offer>>() {
//            @Override
//            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
//                view.onOfferListAvailable(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Offer>> call, Throwable t) {
//                Timber.e(TAG, "doInBackground: Something went wrong fetching data", t);
//                view.onOfferListFetchError();
//            }
//        });

        serviceHelper.getOfferListObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Offer>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Offer> offers) {
                        view.onOfferListAvailable(offers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
