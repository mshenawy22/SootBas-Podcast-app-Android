package com.sootbas.sootbasapp.common;

import com.sootbas.sootbasapp.model.podcast.Podcast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        Beauty(7),
        Pray(8),
        Life(9),
        Originals(10),
        NoCategory(11);

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
//            /* feedUrl; */ " https://anchor.fm/s/19f63614/podcast/rss",
            /* feedUrl; */ "https://feeds.transistor.fm/elda7ee7",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/da7ee7.jpg",
            eCategoryofPodcast.Learn ,
            "101"

           );


    public static final Podcast OmarBahaa_podcast = new Podcast(

            /* artistName;*/"Omar Bahaa",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/omar-bahaa",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/omarbahaa.jpg",

            eCategoryofPodcast.Develop,
            "102"
    );

    public static final Podcast SeifEldeeb_podcast = new Podcast(

            /* artistName;*/"Seif Eldeeb",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/seif-el-deeb",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/SeifElDeeb.jpg",

            eCategoryofPodcast.Pray,
            "103"
    );





    public static final Podcast AhmedHossamAbdeen_podcast = new Podcast(

            /* artistName;*/"Ahmed Hossam",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/ahmed-hossam-abdeen",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/ahmedhossamabdeen600.jpg",
            eCategoryofPodcast.Pray,
            "104"

            );



    public static final Podcast WaleedMostafa_podcast = new Podcast(

            /* artistName;*/"Waleed Mostafa",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/waleed-mostafa",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/waleedmostafa.jpg",
            eCategoryofPodcast.Comedy,
            "105"

            );



    public static final Podcast WaelelBasel_podcast = new Podcast(

            /* artistName;*/"Wael ElBasel",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/wael-elbasel",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/waelelbasel.jpg",
            eCategoryofPodcast.Life,
            "106"


            );


    public static final Podcast AhmedRoshdy_podcast = new Podcast(

            /* artistName;*/"Ahmed Roshdy",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/ahmed-roshdy",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AhmedRoshdy.jpg",
            eCategoryofPodcast.Develop,
            "107"

            );

    public static final Podcast karimanMaher_podcast = new Podcast(

            /* artistName;*/"Kariman Maher",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1f979db0/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/KarimanMaher.jpg",
            eCategoryofPodcast.Develop,
            "108"

            );


    public static final Podcast AhmedElBaz_podcast = new Podcast(
            /* artistName;*/"Ahmed ElBaz ",
            /* cmaollectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/1ffbaf44/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/ahmedelbaz.jpg",
            eCategoryofPodcast.Comedy,
            "109"


    );

    public static final  Podcast Zatoona_podcast = new Podcast(

            /* artistName;*/"El Zatoona",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/el-zatoona",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/elzatoona.jpg",
            eCategoryofPodcast.Read,
            "110"

    );

    public static final Podcast NedalReads_podcast = new Podcast(
            /* artistName;*/"Nedal Reads ",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/nedal-reads",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/nedalreads.jpg",
            eCategoryofPodcast.Read,
            "111"


    );

    public static final Podcast AdhamAbdelRahman_podcast = new Podcast(
            /* artistName;*/"Adham AbdelRahman",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/adham-abdelrahman",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AdhamAbdelRahman.jpg",
            eCategoryofPodcast.Comedy,
            "112"

    );

    public static final Podcast MohamedGoabas_podcast = new Podcast(
            /* artistName;*/"Mohamed Goabas",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/mohaed-goabas",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/MohamedGoabaz.jpg",

            eCategoryofPodcast.Pray,
            "113"

    );

    public static final Podcast FekraSohib_podcast = new Podcast(

            /* artistName;*/"Fekra",
            /* collectionName;*/ "قناة فكرة",
            /* feedUrl; */ " https://anchor.fm/s/1f052214/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/sohaibfekra.jpg",


            eCategoryofPodcast.Learn,
            "114"


    );

    public static final Podcast RowaydaAdel_podcast = new Podcast(

            /* artistName;*/"Rowayda Adel",
            /* collectionName;*/ "انــتي#",
            /* feedUrl; */ "https://feeds.transistor.fm/rowayda-adel",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/rowayda600.jpg",
            eCategoryofPodcast.Beauty,
            "115"


    );

    public static final Podcast MichaelBeshay_podcast = new Podcast(

            /* artistName;*/"Michael Beshay",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/mickael-beshay",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/MichaelBeshay600.jpg",
            eCategoryofPodcast.Develop,
            "116"



    );

    public static final Podcast RihamIraky_podcast = new Podcast(

            /* artistName;*/"Riham Eliraky",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/dr-riham-eliraky",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/RihamElIraky600.jpg",
            eCategoryofPodcast.Healthy,
            "117"

    );

    public static final Podcast ExploreWithKhatib_podcast = new Podcast(

            /* artistName;*/"Explore with Khatib",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/explore-with-khateeb-sootbas",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/exlorewithkhateeb600.jpg",
            eCategoryofPodcast.Life,
            "118"
    );


    public static final Podcast AymanKashef_podcast = new Podcast(

            /* artistName;*/"Ayman Kashef",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/ayman-kashef-sootbas",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AymanKashef600.jpg",
            eCategoryofPodcast.Life,
            "119"

    );

    public static final Podcast Mix_podcast = new Podcast(

            /* artistName;*/"Mix",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/20fe3e48/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/mix.jpg",
            eCategoryofPodcast.NoCategory,
            "120"




    );

    public static final Podcast Mohammed_Sherif_podcast = new Podcast(

            /* artistName;*/"Mohammed Sherif",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/mohammed-sherif",
                /* artworkUrl600;*/"https://vloggerpics.sootbas.com/MuhammadSherif.jpg",

            eCategoryofPodcast.Learn,
            "121"

    );

    public static final Podcast Reham_Aiaad_podcast = new Podcast(

            /* artistName;*/"Reham Aiaad",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/reham-aiaad",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/RehamAiaad.jpg",
            eCategoryofPodcast.Learn,
            "122"


    );

    public static final Podcast Engy_Safei_Eldin_podcast = new Podcast(

            /* artistName;*/"Engy Safei Eldin",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/engy-safei-eldin",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/EngySafei.jpg",

            eCategoryofPodcast.Develop,
            "123"

    );

    public static final Podcast Deana_chaaban_podcast = new Podcast(

            /* artistName;*/"Deana Chaaban",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/243b9150/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/DeanaChaaban.jpg",
            eCategoryofPodcast.Healthy,
            "124"

    );




    public static final Podcast  Eman_Gamal_podcast = new Podcast(

            /* artistName;*/"Eman Gamal",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/dr-eman-gamal",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/EmanGamal.jpg",
            eCategoryofPodcast.Beauty,
            "125"

    );

    public static final Podcast  Hesham_Ahmed_podcast = new Podcast(

            /* artistName;*/"Hesham Ahmed",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/hesham-ahmed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/HeshamAhmed.jpg",
            eCategoryofPodcast.Comedy,
            "126"

    );

    public static final Podcast Nilly_Shams_podcast = new Podcast(

            /* artistName;*/"Nilly Shams",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/dr-nilly-shams",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/NillyShams.jpg",
            eCategoryofPodcast.Healthy,
            "127"

    );

    public static final Podcast  NutritionExplained_podcast = new Podcast(

            /* artistName;*/"Nutrition Explained",
            /* collectionName;*/ "Haidy Eltawous",
            /* feedUrl; */ "https://feeds.transistor.fm/nutrition-explained",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/NutritionExplained.jpg",
            eCategoryofPodcast.Originals,
            "128"

    );

    public static final Podcast    OmarBahaa_Originals_podcast = new Podcast(
    /* artistName;*/"معرفة النفس ",
            /* collectionName;*/ "Omar Bahaa",
            /* feedUrl; */ "https://feeds.transistor.fm/ff0df6a1-a2f2-4713-a934-6a55ed1b3bf1",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/omarbahaaoriginals.jpg",
    eCategoryofPodcast.Originals,
            "129"

    );

    public static final Podcast   AhmedGalal_podcast = new Podcast(
            /* artistName;*/"Ahmed Galal",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/ahmed-galal",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AhmedGalal.jpg",
            eCategoryofPodcast.Comedy,
            "130"

    );



    public static final Podcast   AbdelRahmanAlani_podcast = new Podcast(
            /* artistName;*/"Abdelrahman Alani",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/2503f550/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AbdurrahmanAlani.jpg",
            eCategoryofPodcast.NoCategory,
            "131"

    );

    public static final Podcast   ShaimaaHasona_podcast = new Podcast(
            /* artistName;*/"Shaimaa Hasona",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://feeds.transistor.fm/shaimaa-hasona",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/ShaimaaHasouna2.jpg",
            eCategoryofPodcast.Healthy,
            "132"

    );

    public static final Podcast   SaharElshennawy_podcast = new Podcast(
            /* artistName;*/"Sahar ELShennawy",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/283745d8/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/SaharShennawy.jpg",
            eCategoryofPodcast.Life,
            "133"

    );

    public static final Podcast FakrBe5telaf_podcast = new Podcast(
            /* artistName;*/" فــكر باختــلاف ",
            /* collectionName;*/ "Hebatullah Gamal",
            /* feedUrl; */ "https://feeds.transistor.fm/82fd19b2-62f0-4f57-a1cb-fd44086b1ce3",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/Fakr_be5telaf.jpg",
            eCategoryofPodcast.Originals,
            "134"

    );

    public static final Podcast   HadotetShar3_podcast = new Podcast(
            /* artistName;*/" حــدوتة شــارع ",
            /* collectionName;*/ "Alaa Ahmed",
            /* feedUrl; */ "https://www.spreaker.com/show/4471716/episodes/feed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/hadotetShar3.jpg",
            eCategoryofPodcast.Originals,
            "135"

    );

    public static final Podcast   AbakretEl3arab_podcast = new Podcast(
            /* artistName;*/" عباقرة العرب ",
            /* collectionName;*/ "Asmaa Tarek",
            /* feedUrl; */ "https://www.spreaker.com/show/4490530/episodes/feed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AbakretEl3arab.jpg",
            eCategoryofPodcast.Originals,
            "136"

    );

    public static final Podcast   ChangewithAmira_podcast = new Podcast(
            /* artistName;*/"Change with Amira",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/2a4a818c/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/changewithAmira.jpg",
            eCategoryofPodcast.Healthy,
            "137"

    );

    public static final Podcast   SherifMokadem_podcast = new Podcast(
            /* artistName;*/"Sherif Mokadem",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://www.spreaker.com/show/4509508/episodes/feed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/sherifMokadem.jpg",
            eCategoryofPodcast.Life,
            "138"

    );

    public static final Podcast   AsmaKhalifa_podcast = new Podcast(
            /* artistName;*/"Asma Khalifa",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://www.spreaker.com/show/4509500/episodes/feed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/asfakhalifa.jpg",
            eCategoryofPodcast.Beauty,
            "139"
    );

    public static final Podcast   MariamElSayem_podcast = new Podcast(
            /* artistName;*/"Mariam ElSayem ",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://anchor.fm/s/2bffa7b4/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/mariamelsayem.jpg",
            eCategoryofPodcast.Read,
            "140"
    );

    public static final Podcast   HaidyElTawous_podcast = new Podcast(
            /* artistName;*/"Haidy ElTawous ",
            /* collectionName;*/ "",
            /* feedUrl; */ "https://www.spreaker.com/show/4509553/episodes/feed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/HaidyElTawous.jpg",
            eCategoryofPodcast.Healthy,
            "141"
    );

    public static final Podcast   e3adetdabt_podcast = new Podcast(
            /* artistName;*/" اعادة ضبط ",
            /* collectionName;*/ "Asmaa ElShamy",
            /* feedUrl; */ "https://www.spreaker.com/show/4526474/episodes/feed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/23adet_dabt.jpg",
            eCategoryofPodcast.Originals,
            "142"
    );

    public static final Podcast   hadotetkelma_podcast = new Podcast(
            /* artistName;*/" حدوتة كلمة ",
            /* collectionName;*/ "Alaa Ahmed",
            /* feedUrl; */ "https://www.spreaker.com/show/4525940/episodes/feed",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/hadotet_kelma.jpg",
            eCategoryofPodcast.Originals,
            "143"
    );

    public static final Podcast   alar2yelmasal_podcast = new Podcast(
            /* artistName;*/" علي رأي المثل ",
            /* collectionName;*/ "Ahmed Hashim",
            /* feedUrl; */ "https://anchor.fm/s/2ecd83f8/podcast/rss",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/3lar2yelmasal.jpg",
            eCategoryofPodcast.Originals,
            "144"
    );

    public static final Podcast   HealthyTalks_S1_podcast = new Podcast(
            /* artistName;*/"Healthy Talks-الصيام المتقطع ",
            /* collectionName;*/ "Dr Healthfully",
            /* feedUrl; */ "https://feeds.transistor.fm/ad3000ff-f2bb-46fb-9a77-1a1c90827c7b",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/HealthyTalks_ElSyam.jpg",
            eCategoryofPodcast.Originals,
            "145"
    );

    public static final Podcast   AswaneFelSeka_podcast = new Podcast(
            /* artistName;*/"  أسواني في السكة  ",
            /* collectionName;*/ " أسماء الشامي ",
            /* feedUrl; */ "https://feeds.transistor.fm/b2ddabf6-a924-41ff-8534-05118b4191ef",
            /* artworkUrl600;*/"https://vloggerpics.sootbas.com/AswaneFelSeka.jpg",
            eCategoryofPodcast.Originals,
            "146"
    );



    public static final ArrayList <Podcast> All_podcasts = new ArrayList<>(
            Arrays.asList(
            HealthyTalks_S1_podcast,
              AswaneFelSeka_podcast,
            alar2yelmasal_podcast,
            e3adetdabt_podcast,
            hadotetkelma_podcast,
            ElDa7ee7_podcast,
            OmarBahaa_podcast,
            SeifEldeeb_podcast,
            AhmedHossamAbdeen_podcast,
            WaleedMostafa_podcast,
            AhmedRoshdy_podcast,
            karimanMaher_podcast,
            AhmedElBaz_podcast,
            NedalReads_podcast,
            MariamElSayem_podcast,
            AdhamAbdelRahman_podcast,
            MohamedGoabas_podcast,
            Eman_Gamal_podcast,
            RowaydaAdel_podcast,
            MichaelBeshay_podcast,
            RihamIraky_podcast,
            ExploreWithKhatib_podcast,
            AymanKashef_podcast,
            Mix_podcast,
            Reham_Aiaad_podcast,
            Mohammed_Sherif_podcast,
            FekraSohib_podcast,
            Engy_Safei_Eldin_podcast,
            Nilly_Shams_podcast,
            ShaimaaHasona_podcast,
            Zatoona_podcast,
            Hesham_Ahmed_podcast,
            AhmedGalal_podcast,
            OmarBahaa_Originals_podcast,
            NutritionExplained_podcast,
            HadotetShar3_podcast,
            FakrBe5telaf_podcast,
            SaharElshennawy_podcast,

            ChangewithAmira_podcast,

            SherifMokadem_podcast,
            AsmaKhalifa_podcast,
            WaelelBasel_podcast,
            MariamElSayem_podcast,
            HaidyElTawous_podcast


            ));

}


