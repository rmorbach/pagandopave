package hackathon.com.pagandopave.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.model.Prize;

public class PrizesAdapter extends RecyclerView.Adapter<PrizesAdapter.PrizeViewHolder> {

    Prize[] prizes;

    public PrizesAdapter(Prize[] prizes) {
        this.prizes = prizes;
    }

    @Override
    public PrizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prize_item, parent, false);
        return new PrizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrizeViewHolder holder, int position) {
        //holder.prizeTitle.setText();
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class PrizeViewHolder extends RecyclerView.ViewHolder {

        ImageView prizeBadgeImage;
        TextView prizeTitle;
        TextView prizeDescription;

        public PrizeViewHolder(View itemView) {
            super(itemView);

            prizeBadgeImage = itemView.findViewById(R.id.prize_image_badge);
            prizeTitle = itemView.findViewById(R.id.prize_title);
            prizeDescription = itemView.findViewById(R.id.prize_description);
        }
    }
}
