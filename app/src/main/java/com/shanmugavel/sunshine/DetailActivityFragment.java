package com.shanmugavel.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private final String LOG_TAG = DetailActivityFragment.class.getName();
    private ShareActionProvider mShareActionProvider;
    private String mSharedData;
    public DetailActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        inflater.inflate(R.menu.detail_fragment, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider =  (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        mShareActionProvider.setShareIntent(createSharedIntent(mSharedData));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        String inputData = getActivity().getIntent().getStringExtra("data");
        TextView txt = (TextView) rootView.findViewById(R.id.detailText);
        txt.setText(inputData);
        mSharedData = inputData;
        return rootView;
    }

    // Call to update the share intent
    public Intent createSharedIntent(String sharedMsg) {
        Intent shareIntent = null;
        if (mShareActionProvider != null) {
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, sharedMsg);
        }
        return shareIntent;
    }
}
