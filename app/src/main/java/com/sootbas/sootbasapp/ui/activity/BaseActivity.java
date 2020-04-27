package com.sootbas.sootbasapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.sootbas.sootbasapp.R;


public class BaseActivity extends BlankActivity{

    protected void initFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    protected void initToolbar() {
        // instantiate the toolbar with up nav arrow
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//            getSupportActionBar().setHomeButtonEnabled(false);
//            getSupportActionBar().setCustomView(R.id.toolbar_logo_icon);
//            getSupportActionBar().setDisplayUseLogoEnabled(true);
//            getSupportActionBar().setIcon(R.mipmap.sootbas_logo_round);
//            getSupportActionBar().setLogo(R.mipmap.sootbas_logo_round);
//            ImageView myImageView = (ImageView)findViewById(R.id.toolbar_logo_icon);
//            getSupportActionBar().setCustomView(myImageView);
////            getSupportActionBar().setIcon(R.mipmap.sootbas_logo_round);


            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
