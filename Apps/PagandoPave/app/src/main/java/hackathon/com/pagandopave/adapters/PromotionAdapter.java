package hackathon.com.pagandopave.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.model.Promotion;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder> {

    Promotion[] promotions;

    public PromotionAdapter(Promotion[] promotions) {
        this.promotions = promotions;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_card_item, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {
        //holder.promotionTitle.setText();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class PromotionViewHolder extends RecyclerView.ViewHolder {

        ImageView promotionBanner;
        TextView promotionTitle;
        TextView promotionDescription;

        public PromotionViewHolder(View itemView) {
            super(itemView);

            promotionBanner = itemView.findViewById(R.id.card_item_promotion_image);
            promotionTitle = itemView.findViewById(R.id.card_item_promotion_title);
            promotionDescription = itemView.findViewById(R.id.card_item_promotion_description);
        }
    }
}
