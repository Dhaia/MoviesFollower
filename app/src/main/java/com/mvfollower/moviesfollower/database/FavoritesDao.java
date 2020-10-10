package com.mvfollower.moviesfollower.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoritesDao {

    @Query("SELECT * FROM favorites ORDER BY updated_at")
    LiveData<List<FavoritesObjectEntity>> loadAllData();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItem(FavoritesObjectEntity movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(FavoritesObjectEntity movie);

    @Delete
    void deleteMovie(FavoritesObjectEntity movie);

    @Query("SELECT * FROM favorites WHERE rowId = :rowId")
    LiveData<FavoritesObjectEntity> loadMovieById(int rowId);
}
