package qmb2.cs262.calvin.edu.homework2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private EditText mBookInput;
    private TextView mTitleText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mButton = (Button)findViewById(R.id.searchButton);

        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }

        String queryString = "-1";

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            mTitleText.setText(R.string.loading_text);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }

        else {
            if (queryString.length() == 0) {
                mTitleText.setText("Please enter a search term");
            } else {
                mTitleText.setText("Please check your network connection and try again.");
            }
        }
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();
        if (queryString.toString().length() == 0) {
            queryString = "-1";
        }
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            mTitleText.setText(R.string.loading_text);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }

        else {
            if (queryString.length() == 0) {
                mTitleText.setText("Please enter a search term");
            } else {
                mTitleText.setText("Please check your network connection and try again.");
            }
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, args.getString("queryString"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(data);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            // Initialize iterator and results fields.
            int i = 0;
            String title = null;

            // Look for results in the items array, exiting when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() || (title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    String id = book.getString("id");
                    String eMail = book.getString("emailAddress");
                    String name = null;
                    try {
                        name = book.getString("name");
                    } catch (Exception e) {
                        name = "No Name";
                    }
                    if (title == null) {
                        title = id + ", " + name + ", " + eMail;
                    } else {
                        title = title + "\n" + id + ", " + name + ", " + eMail;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }

            // If both are found, display the result.
            if (title != null){
                mTitleText.setText(title);
                mBookInput.setText("");
            } else {
                // If none are found, update the UI to show failed results.
                mTitleText.setText("2");
            }

        } catch(Exception e) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String title = null;
                String id = jsonObject.getString("id");
                String eMail = jsonObject.getString("emailAddress");
                String name = null;
                try {
                    name = jsonObject.getString("name");
                } catch (Exception q) {
                    name = "No Name";
                }
                title = id + ", " + name + ", " + eMail;

                if (title != null) {
                    mTitleText.setText(title);
                    mBookInput.setText("");
                } else {
                    // If none are found, update the UI to show failed results.
                    mTitleText.setText("No results found");
                }
            } catch (Exception q) {
                // If onPostExecute does not receive a proper JSON string, update the UI to show failed results.
                mTitleText.setText("Please enter nothing or a valid ID number.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {}
}
