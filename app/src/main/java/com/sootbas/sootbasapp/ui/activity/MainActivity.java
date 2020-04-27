package com.sootbas.sootbasapp.ui.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.Utils;
import com.sootbas.sootbasapp.custom.QuerySuggestionProvider;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.model.podcast.Results;
import com.sootbas.sootbasapp.rest.ApiClient;
import com.sootbas.sootbasapp.rest.ApiInterface;
import com.sootbas.sootbasapp.ui.fragment.GenreItemFragment;
import com.sootbas.sootbasapp.ui.fragment.ListItemFragment;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements
        GenreItemFragment.Contract,
        ListItemFragment.Contract {


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

            R.drawable.ic_explore,
            R.drawable.ic_subscription,
            R.drawable.ic_playlist
    };

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setY(112f); // center progressbar, move down due to TabLayout
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        // instantiate the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // instantiate the ViewPager, fragments & icons
        setupViewPager(viewPager);
        mTabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

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

//
//    static  Podcast AhmedHassanwZeinab_podcast = new Podcast(
//
//            /* artistName;*/"Ahmed jj Hassan Family",
//            /* collectionName;*/ "AHmed jj Hassan Family vlogs",
//            /* feedUrl; */ "https://anchor.fm/s/19653114/podcast/rss",
//            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/ahmed%20w%20zenab%20600.jpg",
//            "https://anchor.fm/ahmed-hassan-w-zeinab"
//
//    );
//
//    static  Podcast ObamaElMasry_podcast = new Podcast(
//
//            /* artistName;*/"Obama El Masry",
//            /* collectionName;*/ "Obama El Masry comedy",
//            /* feedUrl; */ "https://anchor.fm/s/19661084/podcast/rss",
//            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/obama%20el%20masry%20600.jpg",
//            "https://anchor.fm/obama-el-masry"
//
//    );
//
    static  Podcast NourhanKandil_podcast = new Podcast(

            /* artistName;*/"Dr Nourhan Kandil",
            /* collectionName;*/ "Dr Nourhan Kandil health tips",
            /* feedUrl; */ "https://anchor.fm/s/1958091c/podcast/rss",
            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/nourhan%20kandil%20600.jpg",

            ""

    );

        static  Podcast OmarBahaa_podcast = new Podcast(

            /* artistName;*/"Omar Bahaa",
            /* collectionName;*/ "Omar Bahaa life coaching",
            /* feedUrl; */ "https://anchor.fm/s/1b6b0650/podcast/rss",
            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/omarbahaa600.jpg",

            ""

    );

    static  Podcast SeifEldeeb_podcast = new Podcast(

            /* artistName;*/"Seif Eldeeb",
            /* collectionName;*/ "سيف الديب",
            /* feedUrl; */ "https://anchor.fm/s/1b342d4c/podcast/rss",
            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/SeifElDeeb600.jpg",

            ""

    );

    static  Podcast AhmedHossamAbdeen_podcast = new Podcast(

            /* artistName;*/"Ahmed Hossam",
            /* collectionName;*/ "احمد حسام عابدين",
            /* feedUrl; */ "https://anchor.fm/s/1b6d50a4/podcast/rss",
            /* artworkUrl600;*/"https://newmido22.000webhostapp.com/arab%20podcasts%20pics/ahmedhossamabdeen600.png",

            ""

    );








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
                ArrayList <Podcast>  LifeStyle_Podcast_list = new ArrayList<Podcast>() ;
                ArrayList <Podcast>  Religion_Podcast_list = new ArrayList<Podcast>() ;
                ArrayList <Podcast>  Comedy_Podcast_list = new ArrayList<Podcast>() ;
                ArrayList <Podcast>  Health_Podcast_list = new ArrayList<Podcast>() ;
                LifeStyle_Podcast_list.add(OmarBahaa_podcast);
                LifeStyle_Podcast_list.add(SeifEldeeb_podcast);
                Religion_Podcast_list.add(AhmedHossamAbdeen_podcast);
//                Comedy_Podcast_list.add(ObamaElMasry_podcast);
//                Health_Podcast_list.add(NourhanKandil_podcast);


                if (genreTitle == "Religion and Spirituality") {
                   list = Religion_Podcast_list ;
                }
                else if (genreTitle == "Life Coaching")
                {
                   list = LifeStyle_Podcast_list;
                }
                else
                {
                  list = LifeStyle_Podcast_list;
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
//        mTabLayout.getTabAt(0).setIcon(mTabIcons[0]);
//        mTabLayout.getTabAt(1).setIcon(mTabIcons[1]);
//        mTabLayout.getTabAt(2).setIcon(mTabIcons[2]);
        //mTabLayout.getTabAt(3).setIcon(mTabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {
        CustomViewPagerAdapter adapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(GenreItemFragment.newInstance(), "Explore");
//        adapter.addFragment(ListItemFragment.newInstance(), "Subscription");
//        adapter.addFragment(ListItemFragment.newInstance(), "Playlist");
        // adapter.addFragment(SubscriptionFragment.newInstance(), "Subscription");
        // adapter.addFragment(PlaylistFragment.newInstance(), "Playlist");
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


}
