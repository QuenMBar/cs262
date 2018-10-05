package qmb2.cs262.calvin.edu.lab05;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;


public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    /**
     * Sets the queryString that the user is searching for
     */
    public BookLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    /**
     * Makes sure it loads
     */
    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    /**
     * Searches for the query using the loader
     */
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
