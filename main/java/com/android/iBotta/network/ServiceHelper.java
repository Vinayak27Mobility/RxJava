package com.android.iBotta.network;

import android.content.Context;

import com.android.iBotta.model.Offer;
import com.android.iBotta.rx.Func0;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;

public class ServiceHelper {

    private OfferAPI offerAPI;
    //Passed for asset access
    private Context context;

    public ServiceHelper(OfferAPI offerAPI, Context context) {
        this.offerAPI = offerAPI;
        this.context = context;
    }

//    public Call<List<Offer>> getOfferList() {
//        return offerAPI.getOfferData();
//    }

    private List<Offer> getJsonList() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("Offers.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        //
        Gson gson = new Gson();
        Type type = new TypeToken<List<Offer>>(){}.getType();
        List<Offer> offerList = gson.fromJson(json, type);
        //
        return offerList;
    }

    public Observable<List<Offer>> getOfferListObservable() {
    //return offerAPI.getOfferData();
        return Observable.defer(new Func0<Observable<List<Offer>>>() {

            @Override
            public Object apply(Object o) throws Exception {
                return null;
            }

            @Override
            public Observable<List<Offer>> call() {
                return Observable.just(getJsonList());
            }
        });
    }

//        String json = null;
//        try {
//            InputStream is = context.getAssets().open("Offers.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//
//        //
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<Offer>>(){}.getType();
//        List<Offer> offerList = gson.fromJson(json, type);
//        //
//        return offerList;

}
