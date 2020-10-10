package com.mvfollower.moviesfollower.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.network.ApiInterface;
import com.mvfollower.moviesfollower.pojos.TvBody;
import com.mvfollower.moviesfollower.network.RetrofitUtilities;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> trendingMoviesWeek = new MutableLiveData<>();
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> upcomingMoviesList = new MutableLiveData<>();
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> trendingMoviesDay = new MutableLiveData<>();
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> nowPlayingMoviesList = new MutableLiveData<>();
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> mostPopularMoviesList = new MutableLiveData<>();
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> topRatedMoviesList = new MutableLiveData<>();
    private MutableLiveData<List<TvBody.TvDataStructure>> tvAiringToday = new MutableLiveData<>();
    private MutableLiveData<List<TvBody.TvDataStructure>> trendingTv = new MutableLiveData<>();
    private MutableLiveData<List<TvBody.TvDataStructure>> tvMostPopular = new MutableLiveData<>();
    private MutableLiveData<List<TvBody.TvDataStructure>> tvTopRated = new MutableLiveData<>();
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> discoverMovies = new MutableLiveData<>();
    private MutableLiveData<List<TvBody.TvDataStructure>> discoverTv = new MutableLiveData<>();
    private MutableLiveData<List<MoviesBody.MovieDataStructure>> queryMovies = new MutableLiveData<>();
    private MutableLiveData<List<TvBody.TvDataStructure>> queryTv = new MutableLiveData<>();

    ApiInterface apiInterface;
    final String week = "week";
    final String day = "day";

    public MainViewModel(String genreId, String query) {
        apiInterface = RetrofitUtilities.buildRetrofit();

        RetrofitUtilities.getTrendingMovies(week, apiInterface, trendingMoviesWeek);
        RetrofitUtilities.getTrendingMovies(day, apiInterface, trendingMoviesDay);
        RetrofitUtilities.getUpcomingMovies(apiInterface, upcomingMoviesList);
        RetrofitUtilities.getNowPlayingMovies(apiInterface, nowPlayingMoviesList, "1");
        RetrofitUtilities.getMostPopularMovies(apiInterface, mostPopularMoviesList);
        RetrofitUtilities.getTopRatedMovies(apiInterface, topRatedMoviesList);

        RetrofitUtilities.getTvAiringToday(apiInterface, tvAiringToday);
        RetrofitUtilities.getTrendingTv(apiInterface, trendingTv);
        RetrofitUtilities.getPopularTv(apiInterface, tvMostPopular);
        RetrofitUtilities.getTopRatedTv(apiInterface, tvTopRated);

        RetrofitUtilities.getDiscoverMovies(apiInterface, genreId, "popularity.desc", "", discoverMovies);
        RetrofitUtilities.getDiscoverTv(apiInterface, genreId, "popularity.desc", "", discoverTv);

        RetrofitUtilities.getQueryMovies(apiInterface, query, queryMovies);
        RetrofitUtilities.getQueryTv(apiInterface, query, queryTv);
    }

    public LiveData<List<MoviesBody.MovieDataStructure>> getTrendingMoviesWeek(){ return trendingMoviesWeek; }
    public LiveData<List<MoviesBody.MovieDataStructure>> getUpcomingMoviesList(){ return upcomingMoviesList; }
    public LiveData<List<MoviesBody.MovieDataStructure>> getNowPlayingMoviesList() { return nowPlayingMoviesList; }
    public LiveData<List<MoviesBody.MovieDataStructure>> getMostPopularMoviesList() { return mostPopularMoviesList; }
    public LiveData<List<MoviesBody.MovieDataStructure>> getTopRatedMoviesList() { return topRatedMoviesList; }
    public LiveData<List<TvBody.TvDataStructure>> getTrendingTv(){ return trendingTv; }
    public LiveData<List<TvBody.TvDataStructure>> getTvAiringToday() { return tvAiringToday; }
    public LiveData<List<TvBody.TvDataStructure>> getTvMostPopular() { return tvMostPopular; }
    public LiveData<List<TvBody.TvDataStructure>> getTvTopRated() { return tvTopRated; }
    public LiveData<List<MoviesBody.MovieDataStructure>> getDiscoverMovies() { return discoverMovies; }
    public LiveData<List<TvBody.TvDataStructure>> getDiscoverTv() { return discoverTv; }
}
