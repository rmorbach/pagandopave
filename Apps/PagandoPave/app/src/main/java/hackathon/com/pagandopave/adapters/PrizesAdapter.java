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

        prizes = new Prize[5];

        Prize prize1 = new Prize();
        prize1.setBadgeResourceId(R.drawable.badge);
        prize1.setTitle("#IMDb");
        prize1.setDescription("Já é quase um crítico de cinema! Tem algum filme que ainda não viu?");

        Prize prize2 = new Prize();
        prize2.setBadgeResourceId(R.drawable.badge2);
        prize2.setTitle("#maodevaca");
        prize2.setDescription("Estamos começando uma fortuna aqui. Tá planejando o que? ");

        Prize prize3 = new Prize();
        prize3.setBadgeResourceId(R.drawable.badge3);
        prize3.setTitle("#booklover");
        prize3.setDescription("Uau, você não sai de uma livraria.\n" +
                "Tô vendo futuro! ");

        Prize prize4 = new Prize();
        prize4.setBadgeResourceId(R.drawable.badge4);
        prize4.setTitle("#TeenCardGo!");
        prize4.setDescription("Aeee! Sua primeira compra com o #TeenCard!");

        Prize prize5 = new Prize();
        prize5.setBadgeResourceId(R.drawable.badge5);
        prize5.setTitle("#masterchef");
        prize5.setDescription("4 restaurantes diferentes em um mês? Vai virar chef!");

        prizes[0] = prize1;
        prizes[1] = prize2;
        prizes[2] = prize3;
        prizes[3] = prize4;
        prizes[4] = prize5;

        this.prizes = prizes;
    }

    @Override
    public PrizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prize_item, parent, false);
        return new PrizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrizeViewHolder holder, int position) {
        Prize prize = prizes[position];
        holder.prizeTitle.setText(prize.getTitle());
        holder.prizeBadgeImage.setImageResource(prize.getBadgeResourceId());
        holder.prizeDescription.setText(prize.getDescription());
    }

    @Override
    public int getItemCount() {
        if(prizes == null) return 0;
        return prizes.length;
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
