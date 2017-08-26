package hackathon.com.pagandopave;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import hackathon.com.pagandopave.fragments.ExtractFragment;
import hackathon.com.pagandopave.fragments.PrizesFragment;
import hackathon.com.pagandopave.fragments.PromotionFragment;
import hackathon.com.pagandopave.interfaces.OnFragmentInteractionListener;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    ActionBar actionBar;
    TextView mActionBarTitle;

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

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.botton_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_promotions: {

                                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                PromotionFragment promotionFragment = PromotionFragment.newInstance();
                                fragmentTransaction.replace(R.id.fragment_container, promotionFragment);
                                fragmentTransaction.commit();

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

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PromotionFragment promotionFragment = PromotionFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, promotionFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
