package com.mvfollower.moviesfollower.databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "favorites")
public class FavoritesObjectEntity {

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @PrimaryKey(autoGenerate = true)
    private int rowId;

    public int getRowId() {
        return rowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }

    private String title;

    private String backDropPath;

    private String posterPath;

    private String voteCount;

    private String voteAverage;

    private String releaseDate;

    private String overview;

    private ArrayList<Integer> genreIds;

    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }

    private String itemType;

    @Ignore
    public FavoritesObjectEntity(String title, String backDropPath, String posterPath,
                                 String voteCount, String voteAverage, String releaseDate, String overview,
                                 ArrayList<Integer> genreIds, Date updatedAt, String itemId, String itemType) {
        this.title = title;
        this.backDropPath = backDropPath;
        this.posterPath = posterPath;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.genreIds = genreIds;
        this.updatedAt = updatedAt;
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public FavoritesObjectEntity(int rowId, String title, String backDropPath, String posterPath,
                                 String voteCount, String voteAverage, String releaseDate, String overview,
                                 ArrayList<Integer> genreIds, Date updatedAt, String itemId, String itemType) {
        this.updatedAt = updatedAt;
        this.rowId = rowId;
        this.title = title;
        this.backDropPath = backDropPath;
        this.posterPath = posterPath;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.genreIds = genreIds;
        this.itemId = itemId;
        this.itemType = itemType;
    }
}
