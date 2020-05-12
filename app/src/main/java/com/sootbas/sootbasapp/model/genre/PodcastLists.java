package com.sootbas.sootbasapp.model.genre;

import com.sootbas.sootbasapp.model.podcast.Podcast;
import com.sootbas.sootbasapp.common.PodcastsConstants;

import java.util.ArrayList;

public class PodcastLists {

    private ArrayList <Podcast>  SelfDevelopment_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  Religion_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  Comedy_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  HealthandFitness_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  Travel_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  FilmReviews_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  SelfLearning_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast> BooksReview_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast> Women_Podcast_list = new ArrayList<Podcast>() ;

    //default constructor
    public PodcastLists()
    {
        SelfDevelopment_Podcast_list.add(PodcastsConstants.OmarBahaa_podcast);
        SelfDevelopment_Podcast_list.add(PodcastsConstants.AhmedRoshdy_podcast);
        SelfDevelopment_Podcast_list.add(PodcastsConstants.karimanMaher_podcast);
        Religion_Podcast_list.add(PodcastsConstants.SeifEldeeb_podcast);
        Religion_Podcast_list.add(PodcastsConstants.AhmedHossamAbdeen_podcast);
        HealthandFitness_Podcast_list.add(PodcastsConstants.NourhanKandil_podcast);
        Comedy_Podcast_list.add(PodcastsConstants.WaleedMostafa_podcast);
        Comedy_Podcast_list.add(PodcastsConstants.WaelelBasel_podcast);
        Comedy_Podcast_list.add(PodcastsConstants.AymanMostafa_podcast);

        Travel_Podcast_list.add(PodcastsConstants.MohamedHady_podcast);
        FilmReviews_Podcast_list.add(PodcastsConstants.FilmGamed_podcast);
        SelfLearning_Podcast_list.add(PodcastsConstants.ElDa7ee7_podcast);
        BooksReview_Podcast_list.add(PodcastsConstants.Zatoona_podcast);
        BooksReview_Podcast_list.add(PodcastsConstants.AhmedRoshdy_podcast);
        Women_Podcast_list.add(PodcastsConstants.MariamSakr_podcast);
    }


    public ArrayList<Podcast> getSelfDevelopment_Podcast_list() {
        return SelfDevelopment_Podcast_list;
    }

    public ArrayList<Podcast> getReligion_Podcast_list() {
        return Religion_Podcast_list;
    }

    public ArrayList<Podcast> getComedy_Podcast_list() {
        return Comedy_Podcast_list;
    }

    public ArrayList<Podcast> getHealthandFitness_Podcast_list() {
        return HealthandFitness_Podcast_list;
    }

    public ArrayList<Podcast> getTravel_Podcast_list() {
        return Travel_Podcast_list;
    }

    public ArrayList<Podcast> getFilmReviews_Podcast_list() {
        return FilmReviews_Podcast_list;
    }

    public ArrayList<Podcast> getSelfLearning_Podcast_list() {
        return SelfLearning_Podcast_list;
    }

    public ArrayList<Podcast> getBooksReview_Podcast_list() {
        return BooksReview_Podcast_list;
    }

    public ArrayList<Podcast> getWomen_Podcast_list() {
        return Women_Podcast_list;
    }



}
