package hackathon.com.pagandopave.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String REGISTER_TOKEN_NOTIFICATION = "http://172.13.0.86:8080/gateway/rest/gateway/register";

    public static URL getRegisterTokenURL() throws MalformedURLException {
        return new URL(REGISTER_TOKEN_NOTIFICATION);
    }

    public static String doGet(URL url) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String responseStringJson;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            responseStringJson = buffer.toString();

            return responseStringJson;
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.e(TAG, "ProtocolException while doing get", e);
        } catch (IOException e) {
            Log.e(TAG, "IOException while doing get", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }

        return null;
    }


    public static String doPost(URL url, String postJsonString) {
        HttpURLConnection urlConnection;
        BufferedReader reader;
        DataOutputStream printout;

        String responseString;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type","application/json");

            urlConnection.connect();

            // Envia requisicao POST
            printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.writeBytes(postJsonString);
            printout.flush();
            printout.close();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            responseString = buffer.toString();

            Log.d(TAG, "Response = "+responseString);

            return responseString;

        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException while trying to connect with server", e);
        } catch (IOException e) {
            Log.e(TAG, "IOException while trying to connect with server", e);
        }

        return null;
    }


}
