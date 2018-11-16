package qmb2.cs262.calvin.edu.homework2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Grabs data and returns it from an API given the player id or lack thereof
 */
public class NetworkUtils {

    private static final String Monopoly_URL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/players";
    private static final String Monopoly_ID_URL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/player/";


    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    /**
     * Grabs data from api using GET and returns it as a JSON String
     *
     * @param queryString
     * @return playerJSONString
     */
    static String getPlayerInfo(String queryString) {
        Log.d(LOG_TAG, queryString);
        if (queryString == "-1") {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String playerJSONString = null;
            try {
                URL requestURL = new URL(Monopoly_URL.toString());
                urlConnection = (HttpURLConnection) requestURL.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
   /* Since it's JSON, adding a newline isn't necessary (it won't affect
      parsing) but it does make debugging a *lot* easier if you print out the
      completed buffer for debugging. */
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                playerJSONString = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d(LOG_TAG, playerJSONString);
            return playerJSONString;
        } else {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String playerJSONString = null;
            try {
                String specURL = Monopoly_ID_URL + queryString;
                URL requestURL = new URL(specURL.toString());
                urlConnection = (HttpURLConnection) requestURL.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
   /* Since it's JSON, adding a newline isn't necessary (it won't affect
      parsing) but it does make debugging a *lot* easier if you print out the
      completed buffer for debugging. */
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                playerJSONString = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d(LOG_TAG, playerJSONString);
            return playerJSONString;
        }
    }
}


