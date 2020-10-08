package com.mvfollower.moviesfollower.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingBody {
    @SerializedName("results")
    List<MoviesBody.MovieDataStructure> results;

    @SerializedName("page")
    int page;

    @SerializedName("total_results")
    int total_results;

    @SerializedName("total_pages")
    int total_pages;

    public List<MoviesBody.MovieDataStructure> getResults() {
        return results;
    }
}
