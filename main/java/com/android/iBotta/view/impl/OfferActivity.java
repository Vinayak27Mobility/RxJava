package com.android.iBotta.view.impl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.iBotta.R;
import com.android.iBotta.adapter.OfferAdapter;
import com.android.iBotta.adapter.SpaceItemDecoration;
import com.android.iBotta.di.DaggerOfferComponent;
import com.android.iBotta.di.OfferComponent;
import com.android.iBotta.di.OfferModule;
import com.android.iBotta.iBottaApplication;
import com.android.iBotta.model.Offer;
import com.android.iBotta.util.Util;
import com.android.iBotta.view.OfferContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.android.iBotta.util.Util.NO_OF_OFFER_COLUMNS;

public class OfferActivity extends AppCompatActivity implements OfferContract.View {


    private OfferComponent daggerComponent;
    private OfferAdapter offerAdapter;

    @BindView(R.id.offer_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_loader)
    ProgressBar progressBar;

    @BindView(R.id.error_text)
    TextView errorMessage;

    @Inject
    OfferContract.presenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        ButterKnife.bind(this);
        setUpDagger();
        setUpRecyclerView();

        progressBar.setVisibility(View.VISIBLE);
        presenter.getOfferList();
    }

    private void setUpRecyclerView() {
        offerAdapter = new OfferAdapter(this, new OfferAdapter.OnItemClickListener() {
            @Override public void onItemClick(Offer item) {
                navigateToOfferDetails(item);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, NO_OF_OFFER_COLUMNS));
        int spacingInPixelsVert = getResources().getDimensionPixelSize(R.dimen.list_top_margin);
        int spacingInPixelsHorz = getResources().getDimensionPixelSize(R.dimen.list_item_horizontal_margin);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixelsVert, spacingInPixelsHorz));
        recyclerView.setAdapter(offerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        offerAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        daggerComponent = null;
        super.onDestroy();
    }

    @Override
    public void onOfferListAvailable(List<Offer> offerList) {
        errorMessage.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        offerAdapter.setData(offerList);
    }

    @Override
    public void onOfferListFetchError() {
        errorMessage.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void navigateToOfferDetails(Offer item) {
        Util.setOffer(item);
        Timber.d("Selected Offer : " + item.getName());
        Intent intent = new Intent(OfferActivity.this, OfferDetailActivity.class);
        OfferActivity.this.startActivity(intent);
    }

    private void setUpDagger() {
        daggerComponent = DaggerOfferComponent.builder()
                .offerModule(new OfferModule(this))
                .applicationComponent(iBottaApplication.getApplicationComponent())
                .build();

        daggerComponent.inject(this);
    }
}
