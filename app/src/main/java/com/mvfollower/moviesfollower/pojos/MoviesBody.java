package com.mvfollower.moviesfollower.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesBody {
    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("results")
    @Expose
    private List<MovieDataStructure> resultsArray = null;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public List<MovieDataStructure> getMoviesList() {
        return resultsArray;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public static class MovieDataStructure {
        @SerializedName("id")
        private int id;

        public MovieDataStructure(String title){
            this.title = title;
        }

        @SerializedName("backdrop_path")
        private String backdrop_path;

        @SerializedName("poster_path")
        private String poster_path;

        @SerializedName("title")
        private String title;

        @SerializedName("vote_average")
        private double vote_average;

        @SerializedName("vote_count")
        private String vote_count;

        @SerializedName("release_date")
        private String release_date;

        @SerializedName("original_title")
        private String original_title;

        @SerializedName("overview")
        private String overview;

        @SerializedName("media_type")
        private String media_type;

        @SerializedName("genre_ids")
        @Expose
        private List<Integer> genre_ids = null;

        public int getId() {
            return id;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public String getTitle() {
            return title;
        }

        public double getVote_average() {
            return vote_average;
        }

        public String getVote_count() {
            return vote_count;
        }

        public String getRelease_date() {
            return release_date;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public String getOverview() {
            return overview;
        }

        public String getMedia_type() {
            return media_type;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }
    }
}

