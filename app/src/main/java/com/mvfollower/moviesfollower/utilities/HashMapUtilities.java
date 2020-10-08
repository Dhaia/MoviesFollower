package com.mvfollower.moviesfollower.utilities;

import java.util.HashMap;

public class HashMapUtilities {
    final static int moviesAction = 28;
    final static int moviesAdventure = 12;
    final static int moviesAnimation = 16;
    final static int moviesComedy = 35;
    final static int moviesCrime = 80;
    final static int moviesDocumentary = 99;
    final static int moviesDrama = 18;
    final static int moviesFamily = 10751;
    final static int moviesFantasy = 14;
    final static int moviesHistory = 36;
    final static int moviesHorror = 27;
    final static int moviesMusic = 10402;
    final static int moviesMystery = 9648;
    final static int moviesRomance = 10749;
    final static int moviesScienceFiction = 878;
    final static int moviesTVMovie = 10770;
    final static int moviesThriller = 53;
    final static int moviesWar = 10752;
    final static int moviesWestern = 37;
    final static int ActionAdventure = 10759;
    final static int Kids = 10762;
    final static int News = 10763;
    final static int Reality = 10764;
    final static int SciFiFantasy = 10765;
    final static int Soap = 10766;
    final static int Talk = 10767;
    final static int WarPolitics = 10768;

    public static void fillHashMap(HashMap<Integer,String> genresHashMap){
        genresHashMap.put(ActionAdventure, "Action Adventure");
        genresHashMap.put(Kids, "Kids");
        genresHashMap.put(News, "News");
        genresHashMap.put(Reality, "Reality");
        genresHashMap.put(SciFiFantasy, "Sci-Fi & Fantasy");
        genresHashMap.put(Soap, "Soap");
        genresHashMap.put(Talk, "Talk");
        genresHashMap.put(WarPolitics, "War & Politics");
        genresHashMap.put(moviesAction, "Action");
        genresHashMap.put(moviesAdventure, "Adventure");
        genresHashMap.put(moviesAnimation, "Animation");
        genresHashMap.put(moviesComedy, "Comedy");
        genresHashMap.put(moviesCrime, "Crime");
        genresHashMap.put(moviesDocumentary, "Documentary");
        genresHashMap.put(moviesDrama, "Drama");
        genresHashMap.put(moviesFamily, "Family");
        genresHashMap.put(moviesHistory, "History");
        genresHashMap.put(moviesHorror, "Horror");
        genresHashMap.put(moviesMusic, "Music");
        genresHashMap.put(moviesMystery, "Mystery");
        genresHashMap.put(moviesRomance, "Romance");
        genresHashMap.put(moviesScienceFiction, "Science Fiction");
        genresHashMap.put(moviesTVMovie, "TV Movie");
        genresHashMap.put(moviesThriller, "Thriller");
        genresHashMap.put(moviesWar, "War");
        genresHashMap.put(moviesWestern, "Western");
        genresHashMap.put(moviesFantasy, "Fantasy");
    }
}
