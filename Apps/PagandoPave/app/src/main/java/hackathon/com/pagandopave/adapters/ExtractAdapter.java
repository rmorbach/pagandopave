package hackathon.com.pagandopave.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hackathon.com.pagandopave.R;
import hackathon.com.pagandopave.model.Extract;

public class ExtractAdapter extends RecyclerView.Adapter<ExtractAdapter.ExtractViewHolder> {

    Extract[] extracts;

    public ExtractAdapter(Extract[] extracts) {
        this.extracts = extracts;
    }

    @Override
    public ExtractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.extract_card_item, parent, false);
        return new ExtractAdapter.ExtractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExtractViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
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
            extractCategoryTextView = itemView.findViewById(R.id.card_item_extract_category);
            extractPlaceTextView = itemView.findViewById(R.id.card_item_extract_place);
            extractDateTextView = itemView.findViewById(R.id.card_item_extract_date);
        }
    }
}
