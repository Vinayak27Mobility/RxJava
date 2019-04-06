package com.android.iBotta.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.iBotta.model.Offer;

public class Util {
    //App level constants
    public static final int NO_OF_OFFER_COLUMNS = 2;

    public static Offer getOffer() {
        return offer;
    }

    public static void setOffer(Offer o) {
        offer = o;
    }

    private static Offer offer;

//    public static void storeOfferIndex(Context context, int index) {
//        SharedPreferences sp = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("email",data);
//        editor.apply();
//    }
//
//    public static int retriveOfferIndex(Context context) {
//        SharedPreferences sp = context.getSharedPreferences("LoginInfos", Context.MODE_PRIVATE);
//        String  dataFromOtherAct= sp.getInt("email", "no email");
//    }
}
