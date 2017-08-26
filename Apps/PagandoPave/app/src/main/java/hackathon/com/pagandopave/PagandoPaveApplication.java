package hackathon.com.pagandopave;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class PagandoPaveApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/GloriaHallelujah.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
