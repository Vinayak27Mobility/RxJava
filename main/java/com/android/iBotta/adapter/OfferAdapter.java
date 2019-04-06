package com.android.iBotta.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.iBotta.R;
import com.android.iBotta.model.Offer;
import com.android.iBotta.view.impl.OfferActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.android.iBotta.util.Util.NO_OF_OFFER_COLUMNS;


public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    private List<Offer> offerList = new ArrayList<>();
    private OfferActivity context;
    public OnItemClickListener listener;

    public OfferAdapter(OfferActivity offerActivity, OnItemClickListener listener) {
        context = offerActivity;
        this.listener = listener;
    }

    public void setData(List<Offer> offerList) {
        this.offerList = offerList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(offerList.get(position), listener);
        Offer selectedOffer = offerList.get(position);
        holder.offerAmount.setText(selectedOffer.getCurrentValue());
        holder.offerName.setText(selectedOffer.getName());
        Picasso.get()
                .load(selectedOffer.getUrl())
                .into(holder.offerImage);
        if(selectedOffer.isFav()) {
            holder.cardView.setBackgroundColor(context.getColor(R.color.colorAccent));
        } else {
            holder.cardView.setBackgroundColor(context.getColor(R.color.gray));
        }
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView offerImage;
        TextView offerAmount;
        TextView offerName;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            offerImage = itemView.findViewById(R.id.offer_image);
            offerAmount = itemView.findViewById(R.id.offer_amount);
            offerName = itemView.findViewById(R.id.offer_name);
            cardView = itemView.findViewById(R.id.offer_image_container);

            //cardview width and height
            DisplayMetrics displayMetrics = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int initspacing = context.getResources().getDimensionPixelSize(R.dimen.list_left_margin);
            int middlespacing = context.getResources().getDimensionPixelSize(R.dimen.list_item_horizontal_margin);
            int cardWidth = (width - (initspacing << 1) - middlespacing) / NO_OF_OFFER_COLUMNS;
            cardView.setLayoutParams(new RelativeLayout.LayoutParams(
                    cardWidth, ((cardWidth * 3)/4)));
            //
        }

        public void bind(final Offer item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Offer item);
    }
}
