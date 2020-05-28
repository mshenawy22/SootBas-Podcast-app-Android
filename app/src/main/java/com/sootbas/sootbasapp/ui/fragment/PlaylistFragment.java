package com.sootbas.sootbasapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sootbas.sootbasapp.PodcastPlayerApplication;
import com.sootbas.sootbasapp.R;
import com.sootbas.sootbasapp.common.Constants;
import com.sootbas.sootbasapp.common.Utils;
import com.sootbas.sootbasapp.custom.AutofitRecyclerView;
import com.sootbas.sootbasapp.custom.ItemSpacerDecoration;
import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.player.manager.PlaylistManager;
import com.sootbas.sootbasapp.player.service.AudioService;

import java.util.ArrayList;
import java.util.List;


public class PlaylistFragment extends ContractFragment<PlaylistFragment.Contract>{



    public interface Contract {
        void onPlaylistClick(Podcast podcast);
    }
    private AutofitRecyclerView mRecyclerView;

    public PlaylistFragment() {}

    public static PlaylistFragment newInstance(ArrayList<Podcast> list) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.PODCAST_LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_playlists, container, false);
        setupView(view);
        List<Podcast> podcastList = getArguments().getParcelableArrayList(Constants.PODCAST_LIST);
        mRecyclerView.setAdapter(new PlaylistFragment.PodcastListAdapter(podcastList));
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

    class PodcastListAdapter extends RecyclerView.Adapter<PlaylistFragment.PodcastListAdapter.ViewHolder>{

        private List<Podcast> mList;
        private Context mContext;

        PodcastListAdapter(List<Podcast> list) {
            mList = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            mContext = parent.getContext();
            View view = LayoutInflater.from(mContext).inflate(R.layout.playlist_item, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(PlaylistFragment.PodcastListAdapter.ViewHolder holder, int position) {
            holder.bindModelItem(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return (mList != null) ? mList.size() : 0;
        }


        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            Podcast podcast;
            String mFeedUrl;
            ImageView mThumbnail;
            TextView mArtistName;
            TextView mCollectionName;
            TextView mNumberOfEpisodes;
            TextView mLatestPublicationDate;


            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                mThumbnail = (ImageView) itemView.findViewById(R.id.playlist_thumbnail);
                mArtistName = (TextView) itemView.findViewById(R.id.podcast_artist_name);
                mCollectionName = (TextView) itemView.findViewById(R.id.podcast_collection_name);
                mNumberOfEpisodes = (TextView) itemView.findViewById(R.id.podcast_episode_number);
                mLatestPublicationDate = (TextView) itemView.findViewById(R.id.podcast_latest_pubdate);
            }

            public void bindModelItem(Podcast item) {
                podcast = item;
                mFeedUrl = item.getFeedUrl();
                mArtistName.setText(item.getArtistName());
//                mCollectionName.setText(item.getCollectionName());
//                mNumberOfEpisodes.setText(String.format(Locale.ENGLISH, "Available episodes: %d", item.getTrackCount()));

                PlaylistManager k = PodcastPlayerApplication.getsPlaylistManager();


                // format the dateTime string
                if (item.getReleaseDate() != null) {
                    String date = Utils.dateConverter(item.getReleaseDate());
                    if (date != null) {
                        String day = date.substring(8, 10);
                        String month = date.substring(4, 7);
                        String year = date.substring(date.length() - 4, date.length());
//                        mLatestPublicationDate.setText(String.format(Locale.ENGLISH, "Last published: %s %s %s", day, month, year));
                    } else {
//                        mLatestPublicationDate.setText(R.string.publication_date_unknown);
                    }
                } else {
//                    mLatestPublicationDate.setText(R.string.publication_date_unknown);
                }
                // use glide to download and display image
                if (item.getArtworkUrl600() != null && !item.getArtworkUrl600().isEmpty()) {
                    Utils.loadPreviewWithGlide(mContext, item.getArtworkUrl600(), mThumbnail);
                } else {
                    Utils.loadPreviewWithGlide(mContext, R.drawable.no_image_600x600, mThumbnail);
                }
            }

            @Override
            public void onClick(View view) {
                // forward the podcast rss feed to the hosting activity
                getContract().onPlaylistClick(podcast);
            }
        }

    }


}
