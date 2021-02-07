package com.example.earthquakereport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {
    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */

    private static final int EARTHQUAKE_LOADER_ID = 1;

    ListView earthquakeListView;
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        progressDialog=new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
        mEmptyStateTextView = findViewById(R.id.empty_view);





        // Start the AsyncTask to fetch the earthquake data
//        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
//        task.execute(USGS_REQUEST_URL);



        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        }
        else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }




        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.listview);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);



        earthquakeListView.setEmptyView(mEmptyStateTextView);



        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }


    //<---------------------------------------------------------------------------------------------------------------------------------------------------------------->
//        final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
////                new ArrayList<Earthquake>();
////        earthquakes.add(new Earthquake("7","San Francisco","01-12-2020"));
////        earthquakes.add(new Earthquake("2","London","01-12-2020"));
////        earthquakes.add(new Earthquake("3","Tokyo","01-12-2020"));
////        earthquakes.add(new Earthquake("4","Mexico City","01-12-2020"));
////        earthquakes.add(new Earthquake("5","Moscow","01-12-2020"));
////        earthquakes.add(new Earthquake("4","Rio de Janeiro","01-12-2020"));
////        earthquakes.add(new Earthquake("1","Paris","01-12-2020"));
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        // Find a reference to the {@link ListView} in the layout
//        ListView earthquakeListView = (ListView) findViewById(R.id.listview);
//
//        // Create a new {@link ArrayAdapter} of earthquakes
//        EarthquakeAdapter adapter = new EarthquakeAdapter(this,earthquakes);
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);
//
//        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Find the current earthquake that was clicked on
//                Earthquake currentEarthquake = adapter.getItem(position);
//
//                // Convert the String URL into a URI object (to pass into the Intent constructor)
//                Uri earthquakeUri = ur;
//                earthquakeUri=Uri.parse(currentEarthquake.getUrl());
//
//                if (ur!=earthquakeUri) {
//
//                    // Create a new intent to view the earthquake URI
//                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
//                    if (websiteIntent.resolveActivity(getPackageManager())!=null) {
//
//                        // Send the intent to launch a new activity
//                        startActivity(websiteIntent);
//                    }
//                }
//
//            }
//        });

//    <--------------------------------------------------------------------------------------------------------------------------------------------------------------------->
//    private class EarthquakeAsyncTask extends AsyncTask<String,Void, List<Earthquake>>{
//
//        @Override
//        protected List<Earthquake> doInBackground(String... urls) {
//
//
//            // Don't perform the request if there are no URLs, or the first URL is null.
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//
//            List<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);
//            return result;
//        }
//        @Override
//        protected void onPostExecute(List<Earthquake> data) {
//            // Clear the adapter of previous earthquake data
//            mAdapter.clear();
//
//            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
//            // data set. This will trigger the ListView to update.
//            if (data != null && !data.isEmpty()) {
//                mAdapter.addAll(data);
//            }
//            progressDialog.dismiss();
//
//       }

//    }

//    <-------------------------------------------------------------------------------------------------------------------------------------------------------------------->

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude=sharedPreferences
                .getString(getString(R.string.settings_min_magnitude_key),getString(R.string.settings_min_magnitude_default));
        String orderBy = sharedPreferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        Uri baseUri=Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder=baseUri.buildUpon();
        uriBuilder.appendQueryParameter("format","geojson");
        uriBuilder.appendQueryParameter("limit", "20");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);
        // Create a new loader for the given URL
        return new EarthquakeLoader(this,uriBuilder.toString());


    }





    @Override
    public void onLoadFinished(android.content.Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);


        }

//        progressDialog.dismiss();

    }
    @Override
    public void onLoaderReset(android.content.Loader<List<Earthquake>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            Intent intent=new Intent(MainActivity.this,SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
