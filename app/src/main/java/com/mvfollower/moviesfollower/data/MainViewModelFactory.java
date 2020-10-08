package com.mvfollower.moviesfollower.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private String genreId;
    private String query;

    public MainViewModelFactory(String genreId, String query) {
        this.genreId = genreId;
        this.query = query;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(genreId, query);
    }
}
