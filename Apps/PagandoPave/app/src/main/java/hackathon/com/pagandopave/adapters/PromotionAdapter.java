package hackathon.com.pagandopave.adapters;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.fragments.PromotionFragment;
import hackathon.com.pagandopave.model.Promotion;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder> {

    List<Promotion> promotions;

    PromotionFragment fragment;

    public PromotionAdapter(PromotionFragment fragment, List<Promotion> promotions) {
        this.promotions = promotions;
        this.fragment = fragment;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_card_item, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {
        final Promotion promotion = promotions.get(position);
        holder.promotionDescription.setText(promotion.getDescription());
        holder.promotionTitle.setText(promotion.getTitle());
        Picasso.with(fragment.getContext())
                .load(promotion.getBannerUrl())
                .fit()
                .into(holder.promotionBanner);
        Picasso.with(fragment.getContext())
                .load(promotion.getPartnerLogoUrl())
                .fit()
                .into(holder.partnerLogo);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.onButtonPressed(promotion);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(promotions == null) return 2;
        return promotions.size();
    }

     class PromotionViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        ImageView promotionBanner;
         ImageView partnerLogo;
        TextView promotionTitle;
        TextView promotionDescription;

        public PromotionViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            partnerLogo = itemView.findViewById(R.id.partner_logo);
            promotionBanner = itemView.findViewById(R.id.card_item_promotion_image);
            promotionTitle = itemView.findViewById(R.id.card_item_promotion_title);
            promotionDescription = itemView.findViewById(R.id.card_item_promotion_description);
        }
    }
}
