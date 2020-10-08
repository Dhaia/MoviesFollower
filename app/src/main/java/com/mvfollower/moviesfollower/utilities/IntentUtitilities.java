package com.mvfollower.moviesfollower.utilities;

import android.content.Intent;

import com.mvfollower.moviesfollower.databases.FavoritesObjectEntity;
import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.pojos.TvBody;

import java.util.ArrayList;
import java.util.List;

public class IntentUtitilities {
    public static void setIntentExtras(Intent intent, MoviesBody.MovieDataStructure clickedItem) {
        intent.putExtra("backDrop", clickedItem.getBackdrop_path());

        Double rating = clickedItem.getVote_average();
        intent.putExtra("voteAverage", String.valueOf(rating));

        intent.setAction("movie");
        intent.putExtra("id", clickedItem.getId());
        intent.putExtra("poster", clickedItem.getPoster_path());
        intent.putExtra("releaseDate", clickedItem.getRelease_date());
        intent.putExtra("title", clickedItem.getTitle());
        intent.putExtra("overview", clickedItem.getOverview());
        intent.putExtra("voteCount", clickedItem.getVote_count());

        List<Integer> firstList = clickedItem.getGenre_ids();
        ArrayList<Integer> secondArrayList = new ArrayList<>();
        secondArrayList.addAll(firstList);
        intent.putIntegerArrayListExtra("genre", secondArrayList);
    }
    public static void setIntentExtras(Intent intent, TvBody.TvDataStructure clickedItem) {
        intent.putExtra("backDrop", clickedItem.getBackdrop_path());

        Double rating = clickedItem.getVote_average();
        intent.putExtra("voteAverage", String.valueOf(rating));

        intent.setAction("tv");
        intent.putExtra("id", clickedItem.getId());
        intent.putExtra("poster", clickedItem.getPoster_path());
        intent.putExtra("releaseDate", clickedItem.getRelease_date());
        intent.putExtra("title", clickedItem.getTitle());
        intent.putExtra("overview", clickedItem.getOverview());
        intent.putExtra("voteCount", clickedItem.getVote_Count());

        List<Integer> firstList = clickedItem.getGenre_ids();
        ArrayList<Integer> secondArrayList = new ArrayList<>();
        secondArrayList.addAll(firstList);
        intent.putIntegerArrayListExtra("genre", secondArrayList);
    }
    public static void setIntentExtras(Intent intent, FavoritesObjectEntity clickedItem) {
        intent.putExtra("backDrop", clickedItem.getBackDropPath());

        intent.setAction(clickedItem.getItemType());
        intent.putExtra("id", Integer.parseInt(clickedItem.getItemId()));
        intent.putExtra("voteAverage", clickedItem.getVoteAverage());
        intent.putExtra("poster", clickedItem.getPosterPath());
        intent.putExtra("releaseDate", clickedItem.getReleaseDate());
        intent.putExtra("title", clickedItem.getTitle());
        intent.putExtra("overview", clickedItem.getOverview());
        intent.putExtra("voteCount", clickedItem.getVoteCount());

        List<Integer> firstList = clickedItem.getGenreIds();
        ArrayList<Integer> secondArrayList = new ArrayList<>();
        secondArrayList.addAll(firstList);
        intent.putIntegerArrayListExtra("genre", secondArrayList);
    }
}
