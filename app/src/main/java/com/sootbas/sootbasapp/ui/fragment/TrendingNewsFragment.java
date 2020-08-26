package com.sootbas.sootbasapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.PodcastsConstants;
import com.sootbas.sootbasapp.common.Utils;
import com.sootbas.sootbasapp.model.episode.Channel;
import com.sootbas.sootbasapp.model.episode.EpisodesDataCache;
import com.sootbas.sootbasapp.model.episode.Feed;
import com.sootbas.sootbasapp.model.genre.PodcastLists;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.rest.RssClient;
import com.sootbas.sootbasapp.rest.RssInterface;
import com.sootbas.sootbasapp.ui.Adapters.MainNewsAdapter;
import com.sootbas.sootbasapp.custom.NewsData;
import com.sootbas.sootbasapp.ui.Adapters.PodcastApiLoader;
import com.sootbas.sootbasapp.ui.activity.EpisodeActivity;

import com.sootbas.sootbasapp.R;


import com.sootbas.sootbasapp.custom.NewsLoader;
import com.sootbas.sootbasapp.ui.activity.PodcastActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

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
    private static String pageSize = "15";
    private static final String author = "show-tags";
    private static final String showFieldsParameter = "show-fields";
    private static final String showFieldsValue = "thumbnail";
    private static final String showFieldsEpisodeNumber = "EpisodeNumber";
    private static final String nameOfAuthor = "contributor";
    private static final String showMostViewed = "show-most-viewed";
    private boolean firsttime = true ; // firsttime to load the stories


    /** Adapter for the list of earthquakes */
    private MainNewsAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;



    private View rootView;
    private View appContentView;
    LoaderManager loaderManager;
    boolean isConnected;
    private View progressBar;

    public TrendingNewsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.news_list, container, false);

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
                activeNetwork.isConnected();

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        progressBar = (View) rootView.findViewById(R.id.progress_bar);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new MainNewsAdapter(getContext(), new ArrayList<NewsData>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);
//       new PodcastApiLoader(getActivity());
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

         switchToPodcast(newsData);

//mshenawy
//
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
//        Uri.Builder builder = Uri.parse(GUARDIAN_RitEQUEST_URL).buildUpon();
        Uri.Builder builder = Uri.parse("https://api.sootbas.com/trending.json").buildUpon();
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
        if( firsttime == true) {
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
            firsttime = false;
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    public void switchToPodcast( NewsData newsData )

    {


        Intent podcast_intent = new Intent(getContext(), PodcastActivity.class);
        String podcast_channel = newsData.getUrlOfStory();
        String story_image = newsData.getImageOfStoryResource();
       int episode_number = newsData.getEpisodenumber();
        Podcast selected_podcast = PodcastsConstants.ElDa7ee7_podcast ;



        if( podcast_channel.equals("elda7ee7")) {


            selected_podcast = PodcastsConstants.ElDa7ee7_podcast;
            System.out.println("Doing nothing");
        }


        else if (podcast_channel.equals("AhmedHossamAbdeen")) {

            selected_podcast = PodcastsConstants.AhmedHossamAbdeen_podcast;
            System.out.println("Doing nothing");

        }

        else if (podcast_channel.equals("AhmedRoshdy")) {


            selected_podcast = PodcastsConstants.AhmedRoshdy_podcast;
        }
        else if (podcast_channel.equals("WaleedMostafa")) {


            selected_podcast = PodcastsConstants.WaleedMostafa_podcast;
            System.out.println("Doing nothing");
        }
        else if (podcast_channel.equals("KarimanMaher")) {


            selected_podcast = PodcastsConstants.karimanMaher_podcast;
            System.out.println("Doing nothing");
        }

        else if (podcast_channel.equals("OmarBahaa")) {


            selected_podcast = PodcastsConstants.OmarBahaa_podcast;
            System.out.println("Doing nothing");
        }
        else if (podcast_channel.equals("SeifEldeeb")) {


            selected_podcast = PodcastsConstants.SeifEldeeb_podcast;
        }

        else if (podcast_channel.equals("WaelelBasel")) {

            selected_podcast = PodcastsConstants.WaelelBasel_podcast;
        }

        else if (podcast_channel.equals("Ahmedelbaz"))
        {

            selected_podcast = PodcastsConstants.AhmedElBaz_podcast;
        }

        else if (podcast_channel.equals("NedalReads"))
        {

            selected_podcast = PodcastsConstants.NedalReads_podcast;
        }

        else if (podcast_channel.equals("MohamedGoabas"))
        {

            selected_podcast = PodcastsConstants.MohamedGoabas_podcast;
        }
        else if (podcast_channel.equals("AdhamAbdelRahman"))
        {

            selected_podcast = PodcastsConstants.AdhamAbdelRahman_podcast;
        }

        else if (podcast_channel.equals("FekraSohaib"))
        {

            selected_podcast = PodcastsConstants.FekraSohib_podcast;

        }


        else if (podcast_channel.equals("AymanKashef"))
        {

            selected_podcast = PodcastsConstants.AymanKashef_podcast;

        }

        else if (podcast_channel.equals("ExploreWithKhatib"))
        {

            selected_podcast = PodcastsConstants.ExploreWithKhatib_podcast;

        }

        else if (podcast_channel.equals("RihamEliraky"))
        {

            selected_podcast = PodcastsConstants.RihamIraky_podcast;

        }

        else if (podcast_channel.equals("MichaelBeshay"))
        {

            selected_podcast = PodcastsConstants.MichaelBeshay_podcast;
        }

        else if (podcast_channel.equals("RowaydaAdel"))
        {

            selected_podcast = PodcastsConstants.RowaydaAdel_podcast;
        }



        else if (podcast_channel.equals("Mix"))
        {

            selected_podcast = PodcastsConstants.Mix_podcast;
        }
        else if (podcast_channel.equals("DeanaChaaban"))
        {

            selected_podcast = PodcastsConstants.Deana_chaaban_podcast;
        }
        else if (podcast_channel.equals("RehmAiaad"))
        {

            selected_podcast = PodcastsConstants.Reham_Aiaad_podcast;
        }
        else if (podcast_channel.equals("EngySafei"))
        {

            selected_podcast = PodcastsConstants.Engy_Safei_Eldin_podcast;
        }
        else if (podcast_channel.equals("EmanGamal"))
        {

            selected_podcast = PodcastsConstants.Eman_Gamal_podcast;
        }
        else if (podcast_channel.equals("HeshamAhmed"))
        {

            selected_podcast = PodcastsConstants.Hesham_Ahmed_podcast;
        }
        else if (podcast_channel.equals("MohammedSherif"))
        {

            selected_podcast = PodcastsConstants.Mohammed_Sherif_podcast;
        }

        else if (podcast_channel.equals("NutritionExplained"))
        {

            selected_podcast = PodcastsConstants.NutritionExplained_podcast;
        }

        else if (podcast_channel.equals("OmarBahaaOriginals"))
        {

            selected_podcast = PodcastsConstants.OmarBahaa_Originals_podcast;
        }

        else if (podcast_channel.equals("7adotetShar3"))
        {

            selected_podcast = PodcastsConstants.HadotetShar3_podcast;
        }

        else if (podcast_channel.equals("FakarBe5telaf"))
        {

            selected_podcast = PodcastsConstants.FakrBe5telaf_podcast;
        }
        else if (podcast_channel.equals("SaharElshennawy"))
        {

            selected_podcast = PodcastsConstants.SaharElshennawy_podcast;
        }
        else if (podcast_channel.equals("ShaimaaHasona_podcast"))
        {

            selected_podcast = PodcastsConstants.ShaimaaHasona_podcast;
        }



        else {
            selected_podcast = PodcastsConstants.OmarBahaa_Originals_podcast;

        }


        for(Podcast p : PodcastsConstants.All_podcasts)
        {

            if (podcast_channel.equals(p.getArtistName()))
            {

                selected_podcast = p;


            }

        }

        podcast_intent.putExtra(Constants.DIRECTLY_OPEN_TRENDING, true);
        executeEpisodeQuery(selected_podcast , episode_number);
//        startActivity(podcast_intent);


    }


    // download the podcast episode list
    private void executeEpisodeQuery(final Podcast item , int episodenumber ) {
        Timber.i("%s execute episode list download", Constants.LOG_TAG);

    if (episodenumber <1) episodenumber =1;
        else episodenumber -=1;
        final int episodeNumber = episodenumber;



        RssInterface rssService = RssClient.getClient().create(RssInterface.class); // takes no time
        Call<Feed> call = rssService.getItems(item.getFeedUrl());
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {

                Channel channel = response.body().getChannel();
                progressBar.setVisibility(View.GONE);
                if (channel != null && channel.getItemList() != null && channel.getItemList().size() > 0) {
                    // save feed to an in-memory cache since it's too large to send via IPC/intent
                    EpisodesDataCache.getInstance().setPodcast(item);
                    EpisodesDataCache.getInstance().setChannel(channel);
                    Intent player_intent = new Intent(getContext(), EpisodeActivity.class);
                    player_intent.putExtra(Constants.EPISODE_SELECTED, episodeNumber);
                    startActivity(player_intent);
                } else {
                    Utils.showSnackbar(rootView, getString(R.string.error_downloading_episode_list));
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Timber.e("%s failure, error: %s", Constants.LOG_TAG, t.getMessage());
                Utils.showSnackbar(rootView, getString(R.string.feed_not_available));
            }


        });
    }



}