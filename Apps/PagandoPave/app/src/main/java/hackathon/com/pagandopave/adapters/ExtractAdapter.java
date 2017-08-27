package hackathon.com.pagandopave.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.model.Extract;

public class ExtractAdapter extends RecyclerView.Adapter<ExtractAdapter.ExtractViewHolder> {

    List<Extract> extracts;

    public ExtractAdapter(List<Extract> extracts) {
        this.extracts = extracts;
    }

    @Override
    public ExtractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extract_card_item, parent, false);
        return new ExtractAdapter.ExtractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExtractViewHolder holder, int position) {
        Extract extract = extracts.get(position);
        if(extract.isCharge()) {
            holder.extractCategoryImage.setImageResource(R.drawable.extract_money_bag);
            holder.extractValueTextView.setTextColor(Color.parseColor("#219653"));
        } else {
            holder.extractCategoryImage.setImageResource(R.drawable.cutlery);
        }

        holder.extractPlaceTextView.setText(extract.getExtractPlace());
        holder.extractValueTextView.setText(String.format("R$ %.2f", extract.getExtractValue()));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat showFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
        try {
            Date date = format.parse(extract.getExtractDate());
            holder.extractDateTextView.setText(showFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(extracts == null) return 0;
        return extracts.size();
    }

    public static class ExtractViewHolder extends RecyclerView.ViewHolder {

        ImageView extractCategoryImage;
        TextView extractValueTextView;
        TextView extractCategoryTextView;
        TextView extractPlaceTextView;
        TextView extractDateTextView;

        public ExtractViewHolder(View itemView) {
            super(itemView);

            extractCategoryImage = itemView.findViewById(R.id.card_item_extract_category_icon);
            extractValueTextView = itemView.findViewById(R.id.card_item_extract_value);
            //extractCategoryTextView = itemView.findViewById(R.id.card_item_extract_category);
            extractPlaceTextView = itemView.findViewById(R.id.card_item_extract_place);
            extractDateTextView = itemView.findViewById(R.id.card_item_extract_date);
        }
    }
}
