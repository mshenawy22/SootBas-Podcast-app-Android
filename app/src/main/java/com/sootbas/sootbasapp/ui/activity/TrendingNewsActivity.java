package com.sootbas.sootbasapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
//import android.support.design.widget.BottomNavigationView;
import androidx.fragment.app.FragmentActivity;

import android.view.View;
import android.widget.TextView;

//import com.daimajia.androidanimations.library.Techniques;
//import com.daimajia.androidanimations.library.YoYo;
//import com.example.android.newsfeedapp.Adapters.SimpleFragmentPagerAdapter;
import com.sootbas.sootbasapp.ui.fragment.TrendingNewsFragment;

//import com.example.android.newsfeedapp.Helpers.BottomNavigationViewHelper;
import com.sootbas.sootbasapp.R;

import java.util.Calendar;




public class TrendingNewsActivity extends FragmentActivity {
//    @BindView(R.id.bottomNavigationView) BottomNavigationView bottomNavMenu; //Bottom Navigation View Menu

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, TrendingNewsActivity.class);
        activity.startActivity(intent);
    }


    public  void LaunchCategoriesScreen (View view)
    {
//                 start the categories screen
        MainActivity.launch(TrendingNewsActivity.this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_content);

        TextView topTitle = (TextView) findViewById(R.id.title_label);


        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);

        if(day == 6 )
        {
            topTitle.setText("Top Friday");
        }
        else {
            topTitle.setText("What's New");
        }
        //Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            TrendingNewsFragment firstFragment = new TrendingNewsFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }



//        ButterKnife.bind(this);
//
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView); //Disables the default transaction in the bottom navigation view

        //Sets onClick listeners on the buttons on the bottom navigation view
//        bottomNavMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.home:
//                        Intent homeIntent = new Intent(TrendingNewsActivity.this, MainActivity.class);
//                        startActivity(homeIntent);
//                        break;
//
//                    case R.id.headline:
//                        Intent headlineIntent = new Intent(TrendingNewsActivity.this, HeadlinesActivity.class);
//                        startActivity(headlineIntent);
//                        break;
//
//                    case R.id.favourites:
//                        Intent favouritesIntent = new Intent(TrendingNewsActivity.this, FavouritesActivity.class);
//                        startActivity(favouritesIntent);
//                        break;
//
//                    case R.id.trending:
//                        YoYo.with(Techniques.Tada)
//                                .duration(700)
//                                .playOn(findViewById(R.id.trending));
//                        break;
//                }
//
//                return false;
//            }
//        });
    }
}
