package com.sootbas.sootbasapp.ui.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.Utils;
import com.sootbas.sootbasapp.custom.QuerySuggestionProvider;
import com.sootbas.sootbasapp.model.episode.Channel;
import com.sootbas.sootbasapp.model.episode.EpisodesDataCache;
import com.sootbas.sootbasapp.model.episode.Feed;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.model.podcast.Results;
import com.sootbas.sootbasapp.rest.ApiClient;
import com.sootbas.sootbasapp.rest.ApiInterface;
import com.sootbas.sootbasapp.rest.RssClient;
import com.sootbas.sootbasapp.rest.RssInterface;
import com.sootbas.sootbasapp.ui.fragment.GenreItemFragment;
import com.sootbas.sootbasapp.ui.fragment.ListItemFragment;
import com.sootbas.sootbasapp.model.genre.PodcastLists;
import com.sootbas.sootbasapp.ui.fragment.OriginalsFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements
        GenreItemFragment.Contract,
        OriginalsFragment.Contract,
        ListItemFragment.Contract {


    private PodcastLists podlist ;
    // implementation of interface methods
    @Override
    public void listItemClick(int position) {
        Utils.showSnackbar(mLayout, "Clicked list item " + position);
    }

    @Override
    public void genreItemClick(int genreId, String genreTitle) {
        // launch PodcastActivity which will execute download of podcasts
        // for the relevant genre and display the results
        if (Utils.isClientConnected(this)) {
            // PodcastActivity.launch(this, genreId, genreTitle);
            executeGenreQuery(genreId, genreTitle);
        } else {
            Utils.showSnackbar(mLayout, getString(R.string.no_network_connection));
        }
    }
    // END


    private SearchRecentSuggestions mRecentSuggestions;
    private MenuItem mSearchItem;
    private SearchView mSearchView;
    private CoordinatorLayout mLayout;
    private ProgressBar mProgressBar;
    private TabLayout mTabLayout;
    private int[] mTabIcons = {

//            R.drawable.ic_explore,
            R.drawable.logowhite,
            R.drawable.originalsbigger,
            R.drawable.ic_playlist
    };

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SubscribeToTopic();
        setContentView(R.layout.activity_main);
        mLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setY(112f); // center progressbar, move down due to TabLayout
        mProgressBar.setVisibility(View.GONE);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        podlist = new PodcastLists();
        // instantiate the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // instantiate the ViewPager, fragments & icons
        setupViewPager(viewPager);
        mTabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
//creating  instance of podcast list that will we displayed when opening the categories


        // ensures there is a ref to suggestions on startup/device rotation
        mRecentSuggestions = new SearchRecentSuggestions(
                MainActivity.this,
                QuerySuggestionProvider.AUTHORITY,
                QuerySuggestionProvider.MODE
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable config with SearchView widget
        SearchManager search = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) mSearchItem.getActionView();
        mSearchView.setSearchableInfo(search.getSearchableInfo(getComponentName()));
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryRefinementEnabled(true);
        mSearchView.setOnQueryTextListener(listener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                if (mRecentSuggestions != null) {
                    ClearHistoryDialog dialog = new ClearHistoryDialog();
                    dialog.show(getSupportFragmentManager(), "clear history");
                } else {
                    Utils.showSnackbar(mLayout, "History clear");
                }
                return true;
            case R.id.action_settings:
                Utils.showSnackbar(mLayout, "Clicked on settings");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            // re-create ref in case the suggestions has been cleared since startup
            mRecentSuggestions = new SearchRecentSuggestions(
                    MainActivity.this,
                    QuerySuggestionProvider.AUTHORITY,
                    QuerySuggestionProvider.MODE);
            // save queries to suggestions provider
            mRecentSuggestions.saveRecentQuery(query, null);

            // if we're connected, execute the search
            if (Utils.isClientConnected(MainActivity.this)) {
                executeSearchQuery(query);
            } else {
                Utils.showSnackbar(mLayout, getString(R.string.no_network_connection));
            }

            // hide the soft keyboard & close the search view
            Utils.hideKeyboard(MainActivity.this, mSearchView.getWindowToken());
            mSearchItem.collapseActionView();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // no-op
            return false;
        }
    };





    // search iTunes for podcasts matching the search query
    private void executeSearchQuery(final String query) {
        mProgressBar.setVisibility(View.VISIBLE);
        ApiInterface searchService = ApiClient.getClient().create(ApiInterface.class);
        Call<Results> call = searchService.getGenrePodcasts(
                query, Constants.PODCAST_ID, Constants.REST_LIMIT, Constants.REST_SORT_RECENT
        );
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                mProgressBar.setVisibility(View.GONE);
                ArrayList<Podcast> results = (ArrayList<Podcast>) response.body().getResults();
                if (results != null && results.size() > 0) {
                    // display results in PodcastActivity/Fragment
                    PodcastActivity.launch(MainActivity.this, results, query, true);
                } else {
                    Utils.showSnackbar(mLayout, "No results returned");
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Utils.showSnackbar(mLayout, "Server error, try again");
                Timber.e("%s error executing search query: %s", Constants.LOG_TAG, t.getMessage());
            }
        });
    }

    // return a list of podcasts for the supplied genre
    private void executeGenreQuery(int genreId, final String genreTitle) {
        mProgressBar.setVisibility(View.VISIBLE);
        Timber.i("%s: executing podcast download task", Constants.LOG_TAG);
        ApiInterface restService = ApiClient.getClient().create(ApiInterface.class);
        Call<Results> call = restService.getGenrePodcasts(
                Constants.REST_TERM, genreId, Constants.REST_LIMIT, Constants.REST_SORT_POPULAR
        );

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                mProgressBar.setVisibility(View.GONE);
//                ArrayList<Podcast> list = (ArrayList<Podcast>) response.body().getResults();
                ArrayList<Podcast> list;

                if (genreTitle.equals("#Pray")) {

                    list = podlist.getReligion_Podcast_list();
                }
                else if (genreTitle.equals("#Develop"))
                {
                    list =  podlist.getSelfDevelopment_Podcast_list();

                }
                else if (genreTitle.equals("#Nutrition") )
                {
                    list =  podlist.getHealthandFitness_Podcast_list();
                }
                else if (genreTitle.equals("#Laugh"))
                {
                    list =  podlist.getComedy_Podcast_list();
                }
                else if (genreTitle.equals("#Travel"))
                {
                    list =  podlist.getLifeStyle_Podcast_list();
                }
                else if (genreTitle.equals("#LifeStyle")) {
                    list = podlist.getLifeStyle_Podcast_list();
                }

                else if (genreTitle.equals("#Critic"))
                {
                    list =  podlist.getFilmReviews_Podcast_list();
                }
                else if (genreTitle.equals("#Learn"))
                {
                    list =  podlist.getSelfLearning_Podcast_list();
                }
                else if (genreTitle.equals("#Read"))
                {

                    list =  podlist.getBooksReview_Podcast_list() ;
                }
                else if (genreTitle.equals("#Beauty"))
                {
                    list =  podlist.getWomen_Podcast_list() ;
                }

                else {
                    list =  podlist.getSelfLearning_Podcast_list() ;
                }




                if (list != null && list.size() > 0) {
                    PodcastActivity.launch(MainActivity.this, list, genreTitle, false);
                } else {
                    Utils.showSnackbar(mLayout, "No results found");
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Utils.showSnackbar(mLayout, "Server error, try again");
                Timber.e("%s error executing genre query: %s", Constants.LOG_TAG, t.getMessage());
            }
        });
    }

    public void confirmHistoryCleared(boolean historyCleared) {
        if (historyCleared) {
            mRecentSuggestions.clearHistory();
            mRecentSuggestions = null;
            Utils.showSnackbar(mLayout, "History successfully cleared");
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(mTabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(mTabIcons[1]);

        TabLayout.Tab tab = mTabLayout.getTabAt(1);
        if (tab != null) tab.setCustomView(R.layout.customtab);




    }

    private void setupViewPager(ViewPager viewPager) {
        CustomViewPagerAdapter adapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(GenreItemFragment.newInstance(), "Explore");
        adapter.addFragment(OriginalsFragment.newInstance( podlist.getOriginals_Podcast_list()), "Originals");

        viewPager.setAdapter(adapter);
    }


    private class CustomViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mTitleList = new ArrayList<>();

        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null; // icon only tab
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mTitleList.add(title);
        }

    }


    public static class ClearHistoryDialog extends DialogFragment implements View.OnClickListener {

        private boolean mHistoryCleared = false;

        public ClearHistoryDialog() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dialog_clear_history, container, false);
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            (view.findViewById(R.id.dialog_positive_btn)).setOnClickListener(this);
            (view.findViewById(R.id.dialog_negative_btn)).setOnClickListener(this); // needs to be enabled to be dismissed
            return view;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.dialog_positive_btn:
                    mHistoryCleared = true;
                    break;
            }
            ((MainActivity) getActivity()).confirmHistoryCleared(mHistoryCleared);
            dismiss();
        }

    }



    public void onPlaylistClick(Podcast podcast){

        if (Utils.isClientConnected(this)) {
            // PodcastActivity.launch(this, genreId, genreTitle);
            executePlaylistQuery(podcast);
        } else {
            Utils.showSnackbar(mLayout, getString(R.string.no_network_connection));
        }
    }

    private void executePlaylistQuery(final Podcast item  ) {
        Timber.i("%s execute episode list download", Constants.LOG_TAG);


        RssInterface rssService = RssClient.getClient().create(RssInterface.class); // takes no time
        Call<Feed> call = rssService.getItems(item.getFeedUrl());
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
//                mProgressBar.setVisibility(View.GONE);
                Channel channel = response.body().getChannel();
                if (channel != null && channel.getItemList() != null && channel.getItemList().size() > 0) {
                    // save feed to an in-memory cache since it's too large to send via IPC/intent
                    EpisodesDataCache.getInstance().setPodcast(item);
                    EpisodesDataCache.getInstance().setChannel(channel);
//start with the very first episode (which should contain a trailer on sprekaer
                    EpisodesActivity.launch(MainActivity.this);


//                    EpisodesActivity.launch(PodcastActivity.this);
//                    finish();
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
//                mProgressBar.setVisibility(View.GONE);
                Timber.e("%s failure, error: %s", Constants.LOG_TAG, t.getMessage());
                Utils.showSnackbar(mLayout, getString(R.string.feed_not_available));
            }


        });
    }


    private void SubscribeToTopic()
    {
        final String TAG = "FirebaseSubscription" ;
        FirebaseMessaging.getInstance().subscribeToTopic("All")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Topic Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscription Failed";
                        }
                        Log.d(TAG,msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
