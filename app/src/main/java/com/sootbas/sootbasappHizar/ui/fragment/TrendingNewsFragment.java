package com.sootbas.sootbasappHizar.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sootbas.sootbasappHizar.common.Constants;
import com.sootbas.sootbasappHizar.common.PodcastsConstants;
import com.sootbas.sootbasappHizar.model.genre.PodcastLists;
import com.sootbas.sootbasappHizar.ui.Adapters.MainNewsAdapter;
import com.sootbas.sootbasappHizar.custom.NewsData;

import com.sootbas.sootbasappHizar.R;


import com.sootbas.sootbasappHizar.custom.NewsLoader;
import com.sootbas.sootbasappHizar.ui.activity.PodcastActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsData>> {

    /** URL for News data from the Guardian dataset */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search";
    private static final String apiKeyparameter = "api-key";
    private static final String apiKey = "78e94902-d37d-4c1e-9f5c-35b58b767f09";
    public static final String orderByParameter = "order-by";
    private static final String queryParameter = "q";
    private static String pageSize = "10";
    private static final String author = "show-tags";
    private static final String showFieldsParameter = "show-fields";
    private static final String showFieldsValue = "thumbnail";
    private static final String nameOfAuthor = "contributor";
    private static final String showMostViewed = "show-most-viewed";

    /** Adapter for the list of earthquakes */
    private MainNewsAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;

    private PodcastLists podlist ;

    LoaderManager loaderManager;
    boolean isConnected;
    private View progressBar;

    public TrendingNewsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) rootView.findViewById(R.id.list);

        /* *********** Checks if there is an internet connection ******/
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        progressBar = (View) rootView.findViewById(R.id.progress_bar);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new MainNewsAdapter(getContext(), new ArrayList<NewsData>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        // Creates an onItemClickListen for the ListView
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsData newsData = mAdapter.getItem(position);

//mshenawy
//
                podlist = new PodcastLists();
               Intent podcast_intent = new Intent(getContext(), PodcastActivity.class);
               String podcast_channel = newsData.getUrlOfStory();



                if( podcast_channel.equals("elda7ee7")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.ElDa7ee7_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getSelfLearning_Podcast_list());
                }
                else if (podcast_channel.equals("elzatoona")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.Zatoona_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getBooksReview_Podcast_list());
                }

                else if (podcast_channel.equals("AhmedHossamAbdeen")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.AhmedHossamAbdeen_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getReligion_Podcast_list());
                }

                else if (podcast_channel.equals("AhmedRoshdy")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.AhmedRoshdy_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getBooksReview_Podcast_list());
                }
                else if (podcast_channel.equals("WaleedMostafa")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.WaleedMostafa_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getComedy_Podcast_list());
                }
                else if (podcast_channel.equals("karimanMaher")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.karimanMaher_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getSelfDevelopment_Podcast_list());
                }
                else if (podcast_channel.equals("MariamSakr")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.MariamSakr_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getWomen_Podcast_list());
                }
                else if (podcast_channel.equals("MohamedHady")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.MohamedHady_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getTravel_Podcast_list());
                }
                else if (podcast_channel.equals("NourhanKandil")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.NourhanKandil_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getHealthandFitness_Podcast_list());
                }
                else if (podcast_channel.equals("OmarBahaa")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.OmarBahaa_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getSelfDevelopment_Podcast_list());
                }
                else if (podcast_channel.equals("SeifEldeeb")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.SeifEldeeb_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getReligion_Podcast_list());
                }

                else if (podcast_channel.equals("WaelelBasel")) {

                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.WaelelBasel_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getComedy_Podcast_list());
                }
                else {
                    podcast_intent.putExtra(Constants.PODCAST_ITEM, PodcastsConstants.WaelelBasel_podcast);
                    podcast_intent.putParcelableArrayListExtra(Constants.PODCAST_LIST, podlist.getComedy_Podcast_list());
                }


                podcast_intent.putExtra(Constants.DIRECTLY_OPEN_TRENDING, true);
                startActivity(podcast_intent);

            }
        });

        // Creates an onItemLongClickListen for the ListView
        newsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NewsData newsData = mAdapter.getItem(position);

                return true;
            }
        });

        return rootView;
    }

    @Override
    public Loader<List<NewsData>> onCreateLoader(int id, Bundle args) {

        // Create a new loader for the given URL
//        Uri.Builder builder = Uri.parse(GUARDIAN_REQUEST_URL).buildUpon();
        Uri.Builder builder = Uri.parse("https://api.sootbas.com/Hizartrending.json").buildUpon();
//        builder.appendQueryParameter(queryParameter, getString(R.string.trending_news))
//                .appendQueryParameter(orderByParameter, getString(R.string.newest))
//                .appendQueryParameter(showFieldsParameter, "bodyText,thumbnail")
//                .appendQueryParameter(showMostViewed, "true")
//                .appendQueryParameter("page-size", "30")
//                .appendQueryParameter(author, nameOfAuthor)
//                .appendQueryParameter(apiKeyparameter, apiKey);
        Log.w("value of url : ", builder.toString());
        return new NewsLoader(getActivity(), builder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsData>> loader, List<NewsData> data) {
        progressBar.setVisibility(View.GONE);

        // Set empty state text to display "No news found."
        mEmptyStateTextView.setText(R.string.no_news);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link NewsData}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


}