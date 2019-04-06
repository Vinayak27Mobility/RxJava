package com.android.iBotta.network;

import com.android.iBotta.model.Offer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OfferAPI {
    @GET("")
    Call<List<Offer>> getOfferData();
}
