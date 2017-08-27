package hackathon.com.pagandopave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import hackathon.com.pagandopave.fragments.ExtractFragment;
import hackathon.com.pagandopave.fragments.PrizesFragment;
import hackathon.com.pagandopave.fragments.PromotionFragment;
import hackathon.com.pagandopave.interfaces.OnFragmentPromotionInteractListener;
import hackathon.com.pagandopave.model.Promotion;
import hackathon.com.pagandopave.network.NetworkUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnFragmentPromotionInteractListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    ArrayList<Promotion> promotions;
    double saldo;

    ActionBar actionBar;
    TextView mActionBarTitle;

    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarView = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbarView) {
            setSupportActionBar(toolbarView);

            actionBar = getSupportActionBar();
            if (actionBar != null) {

                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(false);

                mActionBarTitle = (TextView) findViewById(R.id.action_bar_title);
            }
        }

        mProgressBar = (ProgressBar) findViewById(R.id.progress);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.botton_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_promotions: {
                                if(promotions != null) {
                                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    PromotionFragment promotionFragment = PromotionFragment.newInstance(promotions);
                                    fragmentTransaction.replace(R.id.fragment_container, promotionFragment);
                                    fragmentTransaction.commit();

                                }

                                if(mActionBarTitle != null) {
                                    mActionBarTitle.setText("#tarolando");
                                }
                                break;
                            }
                            case R.id.action_extract: {

                                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                ExtractFragment extractFragment = new ExtractFragment();
                                fragmentTransaction.replace(R.id.fragment_container, extractFragment);
                                fragmentTransaction.commit();

                                if(mActionBarTitle != null) {
                                    mActionBarTitle.setText("#paitrocinio");
                                }
                                break;
                            }
                            case R.id.action_prizes: {
                                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                PrizesFragment prizesFragment = new PrizesFragment();
                                fragmentTransaction.replace(R.id.fragment_container, prizesFragment);
                                fragmentTransaction.commit();

                                if(mActionBarTitle != null) {
                                    mActionBarTitle.setText("Conquistas");
                                }
                            }

                        }
                        return true;
                    }
                });

        new GetPromotionsAsyncTask().execute();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onPromotionClicked(Promotion promotion) {
        Intent intent = new Intent(MainActivity.this, PromotionDetailActivity.class);
        intent.putExtra("promotion", promotion);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    private void parsePromotionsResponse(String response) {
        try {
            JSONArray promotionsJsonArray = new JSONArray(response);

            promotions = new ArrayList<>();

            for(int i = 0; i < promotionsJsonArray.length(); i++) {
                JSONObject jsonPromotion = promotionsJsonArray.getJSONObject(i);
                Promotion promotion = new Promotion(jsonPromotion);
                promotions.add(promotion);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class GetPromotionsAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = NetworkUtils.getPromotionsURL();
                return NetworkUtils.doGet(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);

            Log.d(TAG, ""+string);

            if(string != null) {
                parsePromotionsResponse(string);

                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PromotionFragment promotionFragment = PromotionFragment.newInstance(promotions);
                fragmentTransaction.add(R.id.fragment_container, promotionFragment);
                fragmentTransaction.commit();

                mProgressBar.setVisibility(View.GONE);
            }

            new GetSaldoAsyncTask().execute();
        }
    }

    private class GetSaldoAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String idCartao = preferences.getString("idCartao", "3713100019087");
                URL url = NetworkUtils.getSaldoURL();

                JSONObject getSaldoJsonObject = new JSONObject();
                getSaldoJsonObject.put("idCartao", idCartao);
                return NetworkUtils.doPost(url, getSaldoJsonObject.toString());
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

            Log.d(TAG, "response = "+ s);

            if(!TextUtils.isEmpty(s)) {
                saldo = Double.parseDouble(s);
            }
        }
    }
}
