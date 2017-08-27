package hackathon.com.pagandopave;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hackathon.com.pagandopave.model.Promotion;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PromotionDetailActivity extends AppCompatActivity {

    ActionBar actionBar;

    Promotion promotion;

    TextView partnerName;
    ImageView partnerLogo;
    ImageView banner;
    TextView title;
    TextView description;
    TextView valorOriginal;
    TextView valorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_detail);

        Toolbar toolbarView = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbarView) {
            setSupportActionBar(toolbarView);

            actionBar = getSupportActionBar();
            if (actionBar != null) {

                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        partnerLogo = (ImageView) findViewById(R.id.partner_logo);
        partnerName = (TextView) findViewById(R.id.partner_name);
        title = (TextView) findViewById(R.id.partner_subtitle);
        description = (TextView) findViewById(R.id.promotion_description);
        banner = (ImageView) findViewById(R.id.promotion_banner);
        valorOriginal = (TextView) findViewById(R.id.promotion_original_price);
        valorFinal = (TextView) findViewById(R.id.promotion_discount_price);

        if(getIntent().hasExtra("promotion")) {
            promotion = getIntent().getParcelableExtra("promotion");

            Picasso.with(this)
                    .load(promotion.getBannerUrl())
                    .fit()
                    .into(banner);

            Picasso.with(this)
                    .load(promotion.getPartnerLogoUrl())
                    .fit()
                    .into(partnerLogo);

            partnerName.setText(promotion.getPartnerName());
            title.setText(promotion.getTitle());
            description.setText(promotion.getDescription());
            valorOriginal.setText(String.format(getString(R.string.original_price), promotion.getValorOriginal()));
            valorFinal.setText(String.format(getString(R.string.discount_price), promotion.getValorFinal()));

        } else {
            finish();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
