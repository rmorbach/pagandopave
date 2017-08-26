package hackathon.com.pagandopave.notification;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.UUID;

import hackathon.com.pagandopave.network.NetworkUtils;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                String token = strings[0];

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFirebaseInstanceIDService.this);
                String deviceId = preferences.getString("deviceId", null);

                if(deviceId == null) {
                    deviceId = UUID.randomUUID().toString();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("deviceId", deviceId);
                    editor.apply();
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("deviceId", deviceId);
                    jsonObject.put("token", token);

                    String response = NetworkUtils.doPost(NetworkUtils.getRegisterTokenURL(), jsonObject.toString());
                    Log.d(TAG, "Register response = "+response);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException while trying to register token", e);
                } catch (MalformedURLException e) {
                    Log.e(TAG, "MalformedURLException while trying to register token", e);
                }
                return null;
            }
        }.execute(refreshedToken);
    }
}
