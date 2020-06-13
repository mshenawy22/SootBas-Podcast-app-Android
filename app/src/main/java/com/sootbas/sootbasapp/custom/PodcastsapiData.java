package com.sootbas.sootbasapp.custom;

import android.os.Parcel;
import android.os.Parcelable;

import com.sootbas.sootbasapp.common.PodcastsConstants;

import java.util.ArrayList;
import java.util.List;

//import com.example.android.newsfeedapp.Loader.NewsLoader;

public class PodcastsapiData implements Parcelable {
    private String titleOfStory; //Stores the title of the news
    private String imageOfStoryResource; // Stores the image of the news
    private String urlOfStory; // Stores the url of the news
    private String dateTimeOfStory; // Stores the date and time of the news
    private String sectionOfStory; // Stores the section of the news
    private List<String> reporterName; // Stores the author of the news
    private String bodyOfStory; // Stores the body of the news
    private int EpisodeNumber; // Stores the body of the news


    private String artistName ;
    private String collectionName ;
    private String feedUrl ;
    private String artworkUrl600 ;

    private  PodcastsConstants.eCategoryofPodcast categoryofPodcast ;

    /**
     * Creates a constructor of the Tour Places Data Class
     *
     * @param inputTitleOfStory is the title of the news
     * @param inputImageOfStoryResourceID is the image of the news
     * @param InputUrlOfStory is the url of the news
     * @param InputDateTimeOfStory is the date and time of the news
     * @param InputSectionOfStory is the section of the news
     */
    public PodcastsapiData(String artistName, String collectionName, String feedUrl, String artworkUrl600, PodcastsConstants.eCategoryofPodcast categoryofPodcast) {
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.feedUrl = feedUrl;
        this.artworkUrl600 = artworkUrl600;
        this.categoryofPodcast = categoryofPodcast;
    }

    /**
     * Passes the data contained to its destination
     *
     * @param source is the container of the passed data
     */
    public PodcastsapiData(Parcel source) {
        this.artistName = source.readString();
        this.collectionName = source.readString();
        this.feedUrl = source.readString();
        this.artworkUrl600 = source.readString();
        this.categoryofPodcast.getValue();

//        this.categoryofPodcast.value  = source.readInt();



    }

    /**
     * Gets the tile of the news story
     *
     * @return the title of the news story
     */
    public String getTitleOfStory() {
        return titleOfStory;
    }

    /**
     * Gets the image resource ID of the place
     *
     * @return the image resource ID of the place
     */
    public String getImageOfStoryResource() {
        return imageOfStoryResource;
    }

    public int getEpisodenumber() {
        return EpisodeNumber;
    }

    /**
     * Gets the name of the news story
     *
     * @return the url of the news story
     */
    public String getUrlOfStory() {
        return urlOfStory;
    }

    /**
     * Gets the unix time of the news story
     *
     * @return the unix time of the news story
     */
    public String getDateTimeOfStory() {
        return dateTimeOfStory;
    }

    /**
     * Gets the section of the news story
     *
     * @return the section of the news story
     */
    public String getSectionOfStory() {
        return sectionOfStory;
    }

    /**
     * Gets the reporter's name
     *
     * @return the reporter's name
     */
    public List<String> getReporterName() {
        return reporterName;
    }

    /**
     * Gets the body of the news story
     *
     * @return the body of the news story
     */
    public String getBodyOfStory() {
        return bodyOfStory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleOfStory);
        dest.writeString(imageOfStoryResource);
        dest.writeString(sectionOfStory);
        dest.writeString(dateTimeOfStory);
        dest.writeList(reporterName);
        dest.writeString(bodyOfStory);
    }

    public static final Creator<PodcastsapiData> CREATOR = new Creator<PodcastsapiData>() {
        @Override
        public PodcastsapiData createFromParcel(Parcel source) {
            return new PodcastsapiData(source);
        }

        @Override
        public PodcastsapiData[] newArray(int size) {
            return new PodcastsapiData[size];
        }
    };
}
