package hackathon.com.pagandopave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hackathon.com.pagandopave.model.Promotion;

public class PromotionDetailActivity extends AppCompatActivity {

    Promotion promotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_detail);

        if(getIntent().hasExtra("promotion")) {
            promotion = getIntent().getParcelableExtra("promotion");
        } else {
            finish();
        }
    }


}
