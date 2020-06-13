package com.sootbas.sootbasapp.common;

import com.sootbas.sootbasapp.model.podcast.Podcast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PodcastsConstants {

    public enum eCategoryofPodcast
    {
        Comedy(1),
        Learn(2),
        Healthy(3),
        Develop(4),
        Travel(5),
        Read(6),
        She(7),
        Pray(8),
        Life(9),
        NoCategory(10);

            private  int value;

        private static Map<Integer, eCategoryofPodcast> map = new HashMap<Integer, eCategoryofPodcast>();
        static {
            for (eCategoryofPodcast legEnum : eCategoryofPodcast.values()) {
                map.put(legEnum.getValue(), legEnum);
            }
        }

        public static eCategoryofPodcast valueOf(int legNo) {
            return map.get(legNo);
        }

        eCategoryofPodcast(int value){
                this.value = value;
            }




            public int getValue() {
                return value;
            }
        }


    public static final Podcast ElDa7ee7_podcast = new Podcast(

            /* artistName;*/"El Da7ee7",
            /* collectionName;*/ "الدحيح",
            /* feedUrl; */ " https://anchor.fm/s/19f63614/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/da7ee7.jpg",
            eCategoryofPodcast.Learn

           );





    public static final Podcast OmarBahaa_podcast = new Podcast(

            /* artistName;*/"Omar Bahaa",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1b6b0650/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/omarbahaa.jpg",

            eCategoryofPodcast.Develop
    );

    public static final Podcast SeifEldeeb_podcast = new Podcast(

            /* artistName;*/"Seif Eldeeb",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1b342d4c/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/SeifElDeeb.jpg",

            eCategoryofPodcast.Pray
    );





    public static final Podcast AhmedHossamAbdeen_podcast = new Podcast(

            /* artistName;*/"Ahmed Hossam",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1b6d50a4/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/ahmedhossamabdeen600.jpg",
            eCategoryofPodcast.Pray

            );



    public static final Podcast WaleedMostafa_podcast = new Podcast(

            /* artistName;*/"Waleed Mostafa",
            /* collectionName;*/ "",
            /* feedUrl; */ " https://anchor.fm/s/1e71c1f4/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/waleedmostafa.jpg",
            eCategoryofPodcast.Comedy

            );



    public static final Podcast WaelelBasel_podcast = new Podcast(

            /* artistName;*/"Wael ElBasel",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1f820144/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/waelelbasel.jpg",
            eCategoryofPodcast.Life


            );


    public static final Podcast AhmedRoshdy_podcast = new Podcast(

            /* artistName;*/"Ahmed Roshdy",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1fa83a6c/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AhmedRoshdy.jpg",
            eCategoryofPodcast.Develop

            );

    public static final Podcast karimanMaher_podcast = new Podcast(

            /* artistName;*/"Kariman Maher",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1f979db0/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/KarimanMaher.jpg",
            eCategoryofPodcast.Develop

            );


    public static final Podcast AhmedElBaz_podcast = new Podcast(
            /* artistName;*/"Ahmed ElBaz ",
            /* cmaollectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1ffbaf44/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/ahmedelbaz.jpg",
            eCategoryofPodcast.Comedy


    );

    public static final Podcast NedalReads_podcast = new Podcast(
            /* artistName;*/"Nedal Reads ",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/202970f0/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/nedalreads.jpg",
            eCategoryofPodcast.Read


    );

    public static final Podcast AdhamAbdelRahman_podcast = new Podcast(
            /* artistName;*/"Adham AbdelRahman",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/205a42fc/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AdhamAbdelRahman.jpg",
            eCategoryofPodcast.Travel

    );

    public static final Podcast MohamedGoabas_podcast = new Podcast(
            /* artistName;*/"Mohamed Goabas",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/214d10cc/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/MohamedGoabaz.jpg",

            eCategoryofPodcast.Pray

    );

    public static final Podcast FekraSohib_podcast = new Podcast(

            /* artistName;*/"Fekra",
            /* collectionName;*/ "قناة فكرة",
            /* feedUrl; */ " https://anchor.fm/s/1f052214/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/sohaibfekra.jpg",


            eCategoryofPodcast.Learn


    );

    public static final Podcast RowaydaAdel_podcast = new Podcast(

            /* artistName;*/"Rowayda Adel",
            /* collectionName;*/ "انــتي#",
            /* feedUrl; */ "https://anchor.fm/s/22fb39e4/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/rowayda600.jpg",
            eCategoryofPodcast.She


    );

    public static final Podcast MichaelBeshay_podcast = new Podcast(

            /* artistName;*/"Michael Beshay",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/22780344/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/MichaelBeshay600.jpg",
            eCategoryofPodcast.Develop



    );

    public static final Podcast RihamIraky_podcast = new Podcast(

            /* artistName;*/"Riham Eliraky",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/2275d704/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/RihamElIraky600.jpg",
            eCategoryofPodcast.Healthy

    );

    public static final Podcast ExploreWithKhatib_podcast = new Podcast(

            /* artistName;*/"Explore with Khatib",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/22730718/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/exlorewithkhateeb600.jpg",
            eCategoryofPodcast.Travel
    );


    public static final Podcast AymanKashef_podcast = new Podcast(

            /* artistName;*/"Ayman Kashef",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1fc62180/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AymanKashef600.jpg",
            eCategoryofPodcast.Life

    );

    public static final Podcast Mix_podcast = new Podcast(

            /* artistName;*/"Mix",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/20fe3e48/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/mix.jpg",
            eCategoryofPodcast.NoCategory




    );

    public static final Podcast Mohammed_Sherif_podcast = new Podcast(

            /* artistName;*/"Mohammed Sherif",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/2429e5e0/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/MuhammadSherif.jpg",

            eCategoryofPodcast.Learn

    );

    public static final Podcast Reham_Aiaad_podcast = new Podcast(

            /* artistName;*/"Reham Aiaad",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/24354660/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/RehamAiaad.jpg",
            eCategoryofPodcast.Learn


    );

    public static final Podcast Engy_Safei_Eldin_podcast = new Podcast(

            /* artistName;*/"Engy Safei Eldin",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/243a9d2c/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/EngySafei.jpg",

            eCategoryofPodcast.Develop

    );

    public static final Podcast Deana_chaaban_podcast = new Podcast(

            /* artistName;*/"Deana chaaban",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/243b9150/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/DeanaChaaban.jpg",
            eCategoryofPodcast.Healthy



    );


    public static final Podcast    Eman_Gamal_podcast = new Podcast(

            /* artistName;*/"Eman Gamal",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/245f2d54/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/EmanGamal.jpg",
            eCategoryofPodcast.Healthy



    );

    public static final Podcast    Hesham_Ahmed_podcast = new Podcast(

            /* artistName;*/"Hesham Ahmed",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/2056519c/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/HeshamAhmed.jpg",
            eCategoryofPodcast.Comedy

    );

    public static final Podcast    Nilly_Shams_podcast = new Podcast(

            /* artistName;*/"Nilly Shams",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/252e0ea8/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/HeshamAhmed.jpg",
            eCategoryofPodcast.Healthy

    );


    public static final ArrayList <Podcast> All_podcasts = new ArrayList<>(
            Arrays.asList(

            ElDa7ee7_podcast,

            OmarBahaa_podcast,
            SeifEldeeb_podcast,
            AhmedHossamAbdeen_podcast,

            WaleedMostafa_podcast,

            WaelelBasel_podcast,

            AhmedRoshdy_podcast,
            karimanMaher_podcast,

            AhmedElBaz_podcast,
            NedalReads_podcast,
            AdhamAbdelRahman_podcast,
            MohamedGoabas_podcast,
            FekraSohib_podcast,
            RowaydaAdel_podcast,
            MichaelBeshay_podcast,
            RihamIraky_podcast,
            ExploreWithKhatib_podcast,
            AymanKashef_podcast,
            Mix_podcast,
            Mohammed_Sherif_podcast,
            Reham_Aiaad_podcast,
            Engy_Safei_Eldin_podcast,
            Deana_chaaban_podcast,
            Eman_Gamal_podcast,
            Hesham_Ahmed_podcast,
            Nilly_Shams_podcast
            ));

}


