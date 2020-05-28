package com.sootbas.sootbasapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.custom.AutofitRecyclerView;
import com.sootbas.sootbasapp.custom.ItemSpacerDecoration;
import com.sootbas.sootbasapp.model.podcast.Podcast;

import java.util.List;


public class SubscriptionFragment extends BaseFragment{
    private AutofitRecyclerView mRecyclerView;
    public SubscriptionFragment() {}

    public static SubscriptionFragment newInstance() {
        return new SubscriptionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mContent.setText("Subscription");
//        setupView(view);
//        List<Podcast> podcastList = getArguments().getParcelableArrayList(Constants.PODCAST_LIST);
//        mRecyclerView.setAdapter(new PodcastFragment.PodcastListAdapter(podcastList));
        return view;
    }



    private void setupView(View view) {
        mRecyclerView = (AutofitRecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new ItemSpacerDecoration(
                getResources().getDimensionPixelOffset(R.dimen.list_item_vertical_margin),
                getResources().getDimensionPixelOffset(R.dimen.list_item_horizontal_margin)
        ));
    }


}
