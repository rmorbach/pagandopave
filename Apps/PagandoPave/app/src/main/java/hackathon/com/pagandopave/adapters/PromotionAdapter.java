package hackathon.com.pagandopave.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.fragments.PromotionFragment;
import hackathon.com.pagandopave.model.Promotion;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder> {

    Promotion[] promotions;

    PromotionFragment fragment;

    public PromotionAdapter(PromotionFragment fragment, Promotion[] promotions) {
        this.promotions = promotions;
        this.fragment = fragment;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_card_item, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, final int position) {
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.onButtonPressed(promotions[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(promotions == null) return 2;
        return promotions.length;
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        ImageView promotionBanner;
        TextView promotionTitle;
        TextView promotionDescription;

        public PromotionViewHolder(View itemView) {
            super(itemView);

            rootView = itemView;
            promotionBanner = itemView.findViewById(R.id.card_item_promotion_image);
            promotionTitle = itemView.findViewById(R.id.card_item_promotion_title);
            promotionDescription = itemView.findViewById(R.id.card_item_promotion_description);
        }
    }
}
