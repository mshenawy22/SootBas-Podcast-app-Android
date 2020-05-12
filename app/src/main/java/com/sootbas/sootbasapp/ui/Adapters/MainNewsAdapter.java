package com.sootbas.sootbasapp.ui.Adapters;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sootbas.sootbasapp.custom.NewsData;
import com.sootbas.sootbasapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;




public class MainNewsAdapter extends ArrayAdapter<NewsData> {

    private Picasso mPicasso;
    /**
     *
     * @param context The current context. Used to inflate the layout file.
     * @param pnewsData A List of NewsData objects to display in a list
     */
    public MainNewsAdapter(Context context, ArrayList<NewsData> pnewsData) {
        super(context, 0, pnewsData);
    }

    /**
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View newsListView = convertView;
        if (newsListView == null) {
            newsListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        // Get the NewsData object located at this position in the list
        NewsData currentNews = getItem(position);

        TextView news_title_text_view = (TextView) newsListView.findViewById(R.id.titleOfStory_text_view);
        news_title_text_view.setText(currentNews.getTitleOfStory());

        // Updates image of news story
        ImageView news_image_view = (ImageView) newsListView.findViewById(R.id.imageOfStory_image_view);


        mPicasso = Picasso.with(this.getContext());
        mPicasso.load(currentNews.getImageOfStoryResource()).into(news_image_view);

        // Updates section of news story
        TextView sectionView = (TextView) newsListView.findViewById(R.id.section);
        String mystring = "#" + currentNews.getSectionOfStory();
        sectionView.setText(mystring);

        // Updates gauthor of news story
        TextView authorView = (TextView) newsListView.findViewById(R.id.authorName);
        List<String> author = currentNews.getReporterName();
        if(!author.isEmpty()){
            StringBuilder output = new StringBuilder();
            for(int i = 0; i<author.size();i++){
                String all_author_names = author.get(i);
                output.append(all_author_names);
                output.append(" & ");
            }
            output.deleteCharAt(output.length()-2);

            authorView.setText(output);
        }
        else{
            authorView.setVisibility(View.GONE);
        }

        // Updates date of news story
        String originalDateTime = currentNews.getDateTimeOfStory();
        TextView dateView = (TextView) newsListView.findViewById(R.id.date);
        String Date = originalDateTime.substring(0,10);
        dateView.setText(Date);
        //mshenawy : we don't need to display he publishing time in the trending page 
//        TextView timeView = (TextView) newsListView.findViewById(R.id.time);
//        String Time = originalDateTime.substring(11,16);
//        timeView.setText(Time);

        return newsListView;

    }
}

