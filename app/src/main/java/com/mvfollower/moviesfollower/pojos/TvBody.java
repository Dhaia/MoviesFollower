package com.mvfollower.moviesfollower.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvBody {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<TvDataStructure> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public List<TvDataStructure> getResults() {
        return results;
    }

    public static class TvDataStructure {
        @SerializedName("original_name")
        @Expose
        private String originalName;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("vote_count")
        @Expose
        private String voteCount;
        @SerializedName("vote_average")
        @Expose
        private Double voteAverage;
        @SerializedName("first_air_date")
        @Expose
        private String firstAirDate;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("genre_ids")
        @Expose
        private List<Integer> genreIds = null;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("origin_country")
        @Expose
        private List<String> originCountry = null;
        @SerializedName("popularity")
        @Expose
        private Double popularity;
        @SerializedName("media_type")
        @Expose
        private String mediaType;

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVote_Count() {
            return voteCount;
        }


        public Double getVote_average() {
            return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getRelease_date() {
            return firstAirDate;
        }

        public void setFirstAirDate(String firstAirDate) {
            this.firstAirDate = firstAirDate;
        }

        public String getPoster_path() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public List<Integer> getGenre_ids() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getBackdrop_path() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public List<String> getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(List<String> originCountry) {
            this.originCountry = originCountry;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }
    }
}
