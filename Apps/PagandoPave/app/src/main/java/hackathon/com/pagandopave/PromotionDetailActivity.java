package hackathon.com.pagandopave;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import hackathon.com.pagandopave.model.Promotion;
import hackathon.com.pagandopave.network.NetworkUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PromotionDetailActivity extends AppCompatActivity {

    private static final String TAG = PromotionDetailActivity.class.getSimpleName();

    ActionBar actionBar;

    Promotion promotion;

    TextView partnerName;
    ImageView partnerLogo;
    ImageView banner;
    TextView title;
    TextView description;
    TextView valorOriginal;
    TextView valorFinal;

    Button askMoney;

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
        askMoney = (Button) findViewById(R.id.ask_for_money_button);
        askMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PedirDinheiroAsyncTask().execute();
            }
        });

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

    private class PedirDinheiroAsyncTask extends AsyncTask<Void, Void, String> {

        AlertDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            AlertDialog.Builder builder = new AlertDialog.Builder(PromotionDetailActivity.this);

            builder.setMessage("Pedido de grana enviado! \\o/\\o/")
                                .setTitle("#sucesso");

            dialog = builder.create();
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = NetworkUtils.getPedirDinheiro();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PromotionDetailActivity.this);
                String idCartao = preferences.getString("idCartao", "3713100019087");
                String nomeEvento = promotion.getTitle();

                JSONObject pedirDinheiroJsonObject = new JSONObject();
                pedirDinheiroJsonObject.put("idCartao", idCartao);
                pedirDinheiroJsonObject.put("nomeEvento", nomeEvento);
                pedirDinheiroJsonObject.put("valor", promotion.getValorFinal());

                return NetworkUtils.doPost(url, pedirDinheiroJsonObject.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            dialog.dismiss();

            Log.d(TAG, ""+s);
        }
    }
}
