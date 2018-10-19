package qmb2.cs262.calvin.edu.homework2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public BookLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
