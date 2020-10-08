package com.mvfollower.moviesfollower.databases;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mvfollower.moviesfollower.data.MainViewModel;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<FavoritesObjectEntity>> favoritesList;

    public DatabaseViewModel (Application application) {
        super(application);
        FavoritesAppDatabase database = FavoritesAppDatabase.getInstance(application);
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        favoritesList = database.favoritesDao().loadAllData();
    }

    public LiveData<List<FavoritesObjectEntity>> getFavoritesList() {
        return favoritesList;
    }
}
