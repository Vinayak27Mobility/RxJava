package com.android.iBotta.view.impl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.iBotta.R;
import com.android.iBotta.di.DaggerOfferDetailComponent;
import com.android.iBotta.di.OfferDetailComponent;
import com.android.iBotta.di.OfferDetailModule;
import com.android.iBotta.model.Offer;
import com.android.iBotta.util.Util;
import com.android.iBotta.view.OfferDetailContract;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class OfferDetailActivity extends AppCompatActivity implements OfferDetailContract.View {


    private OfferDetailComponent daggerComponent;
    private Offer offer;

    @Inject
    OfferDetailContract.presenter presenter;

    @BindView(R.id.offer_image)
    ImageView offerImage;

    @BindView(R.id.offer_amount)
    TextView offerAmount;

    @BindView(R.id.offer_name)
    TextView offerName;

    @BindView(R.id.offer_desc)
    TextView offerDesc;

    @BindView(R.id.offer_terms)
    TextView offerTerms;

    @BindView(R.id.fab)
    FloatingActionButton favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);
        ButterKnife.bind(this);
        setUpDagger();
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stringRes = 0;
                if(offer.isFav()) {
                    presenter.setOfferFavorite(false);
                    stringRes = R.string.offer_remove_favorites;
                } else {
                    presenter.setOfferFavorite(true);
                    stringRes = R.string.offer_add_favorites;
                }
                Snackbar.make(view, stringRes, Snackbar.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        offer = Util.getOffer();
        Timber.d("Retrieved Offer for details : " + offer.getName());
        Picasso.get()
                .load(offer.getUrl())
                .into(offerImage);
        offerAmount.setText(offer.getCurrentValue());
        offerName.setText(offer.getName());
        offerDesc.setText(offer.getDescription());
        offerTerms.setText(offer.getTerms());
        if(offer.isFav()) {
            onOfferSetFav();
        } else {
            onOfferResetFav();
        }
    }


    @Override
    protected void onDestroy() {
        daggerComponent = null;
        super.onDestroy();
    }

    @Override
    public void onOfferSetFav() {
        favBtn.setImageDrawable(getDrawable(R.drawable.set));
        offer.setFav(true);
    }

    @Override
    public void onOfferResetFav() {
        favBtn.setImageDrawable(getDrawable(R.drawable.reset));
        offer.setFav(false);
    }

    private void setUpDagger() {
        daggerComponent = DaggerOfferDetailComponent.builder()
                .offerDetailModule(new OfferDetailModule(this))
                .build();

        daggerComponent.inject(this);
    }
}
