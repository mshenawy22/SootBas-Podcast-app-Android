package com.sootbas.sootbasapp.model.genre;

import com.sootbas.sootbasapp.common.PodcastsConstants;
import com.sootbas.sootbasapp.model.podcast.Podcast;

import java.util.ArrayList;


public class PodcastLists {

    private ArrayList <Podcast>  SelfDevelopment_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  Religion_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  Comedy_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  HealthandFitness_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast> LifeStyle_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  FilmReviews_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast>  SelfLearning_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast> BooksReview_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast> Women_Podcast_list = new ArrayList<Podcast>() ;
    private ArrayList <Podcast> Originals_Podcast_list = new ArrayList<Podcast>() ;


    //default constructor
    public PodcastLists()
    {


        for(Podcast p : PodcastsConstants.All_podcasts)
        {
             int x   = p.getCategoryofPodcast();
            PodcastsConstants.eCategoryofPodcast m = PodcastsConstants.eCategoryofPodcast.valueOf(x);


         switch (m)
         {
             case Pray:
                 Religion_Podcast_list.add(p);
                 break;

             case Comedy:
                 Comedy_Podcast_list.add(p);
                 break;
             case Develop:
                SelfDevelopment_Podcast_list.add(p);
                break;
             case Learn:
                 SelfLearning_Podcast_list.add (p);
                 break;
             case Beauty:
                 Women_Podcast_list.add(p);
                 break;
             case Healthy:
                 HealthandFitness_Podcast_list.add(p);
                 break;
             case Read:
                 BooksReview_Podcast_list.add(p);
                 break;
             case Life:
                 LifeStyle_Podcast_list.add(p);
                 break;
             default:

         }
     }
        Originals_Podcast_list.add(PodcastsConstants.NutritionExplained_podcast);

        Originals_Podcast_list.add(PodcastsConstants.OmarBahaa_Originals_podcast);
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

    public ArrayList<Podcast> getLifeStyle_Podcast_list() {
        return LifeStyle_Podcast_list;
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

    public ArrayList<Podcast> getOriginals_Podcast_list() {
        return Originals_Podcast_list;
    }





}
