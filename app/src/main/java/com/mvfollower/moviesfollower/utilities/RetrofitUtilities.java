package com.mvfollower.moviesfollower.utilities;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.mvfollower.moviesfollower.network.ApiInterface;
import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.pojos.TvBody;
import com.mvfollower.moviesfollower.pojos.UpcomingBody;
import com.mvfollower.moviesfollower.pojos.VideosBody;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtilities {
    public final static String BASE_URL = "https://api.themoviedb.org/3/";

    public static ApiInterface buildRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        return apiInterface;
    }

    public static void getQueryMovies(ApiInterface apiInterface, String genresQuery, MutableLiveData<List<MoviesBody.MovieDataStructure>> mutableLiveData){
        Call<MoviesBody> call = apiInterface.getMoviesQuery(genresQuery);
        call.enqueue(new Callback<MoviesBody>() {
            @Override
            public void onResponse(Call<MoviesBody> call, Response<MoviesBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                MoviesBody moviesBody = response.body();

                assert moviesBody != null;
                mutableLiveData.setValue(moviesBody.getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviesBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public static void getQueryTv(ApiInterface apiInterface, String query, MutableLiveData<List<TvBody.TvDataStructure>> mutableLiveData){
        Call<TvBody> call = apiInterface.getTvQuery(query);
        call.enqueue(new Callback<TvBody>() {
            @Override
            public void onResponse(Call<TvBody> call, Response<TvBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                TvBody tvBody = response.body();

                assert tvBody != null;
                mutableLiveData.setValue(tvBody.getResults());
            }

            @Override
            public void onFailure(Call<TvBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public static void getDiscoverTv(ApiInterface apiInterface, String genre_id, String sort_by, String year,  MutableLiveData<List<TvBody.TvDataStructure>> mutableLiveData){
        Call<TvBody> call = apiInterface.getDiscoverTv(genre_id, sort_by, year);
        call.enqueue(new Callback<TvBody>() {
            @Override
            public void onResponse(Call<TvBody> call, Response<TvBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                TvBody tvBody = response.body();

                assert tvBody != null;
                mutableLiveData.setValue(tvBody.getResults());
            }

            @Override
            public void onFailure(Call<TvBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public static void getDiscoverMovies(ApiInterface apiInterface, String genre_id, String sort_by, String year, MutableLiveData<List<MoviesBody.MovieDataStructure>> mutableLiveData){
        Call<MoviesBody> call = apiInterface.getDiscoverMovies(genre_id, sort_by, year);
        call.enqueue(new Callback<MoviesBody>() {
            @Override
            public void onResponse(Call<MoviesBody> call, Response<MoviesBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                MoviesBody moviesBody = response.body();

                assert moviesBody != null;
                mutableLiveData.setValue(moviesBody.getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviesBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public static void geySimilarTvs(ApiInterface apiInterface, String id, MutableLiveData<List<TvBody.TvDataStructure>> mutableLiveData){
        Call<TvBody> call = apiInterface.getSimilarTvs(id);
        call.enqueue(new Callback<TvBody>() {
            @Override
            public void onResponse(Call<TvBody> call, Response<TvBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                TvBody tvBody = response.body();

                assert tvBody != null;
                mutableLiveData.setValue(tvBody.getResults());
            }

            @Override
            public void onFailure(Call<TvBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public static void geySimilarMovies(ApiInterface apiInterface, String id, MutableLiveData<List<MoviesBody.MovieDataStructure>> mutableLiveData){
        Call<MoviesBody> call = apiInterface.getSimilarMovies(id);
        call.enqueue(new Callback<MoviesBody>() {
            @Override
            public void onResponse(Call<MoviesBody> call, Response<MoviesBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                MoviesBody moviesBody = response.body();

                assert moviesBody != null;
                mutableLiveData.setValue(moviesBody.getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviesBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public static void getTrendingTv(ApiInterface apiInterface,
                                     final MutableLiveData<List<TvBody.TvDataStructure>> tvsList){
        Call<TvBody> call = apiInterface.getTrendingTv();
        call.enqueue(new Callback<TvBody>() {
            @Override
            public void onResponse(Call<TvBody> call, Response<TvBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }
                TvBody trendingTvBody = response.body();
                assert trendingTvBody != null;
                tvsList.setValue(trendingTvBody.getResults());
            }

            @Override
            public void onFailure(Call<TvBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public static void getPopularTv(ApiInterface apiInterface, final MutableLiveData<List<TvBody.TvDataStructure>> tvsList){
        Call<TvBody> call = apiInterface.getPopularTv();
        call.enqueue(new Callback<TvBody>() {
            @Override
            public void onResponse(Call<TvBody> call, Response<TvBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }
                TvBody tvBody = response.body();

                assert tvBody != null;
                tvsList.setValue(tvBody.getResults());
            }

            @Override
            public void onFailure(Call<TvBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public static void getTopRatedTv(ApiInterface apiInterface, final MutableLiveData<List<TvBody.TvDataStructure>> tvsList){
        Call<TvBody> call = apiInterface.getTopRatedTv();
        call.enqueue(new Callback<TvBody>() {
            @Override
            public void onResponse(Call<TvBody> call, Response<TvBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }
                TvBody tvBody = response.body();

                assert tvBody != null;
                tvsList.setValue(tvBody.getResults());
            }

            @Override
            public void onFailure(Call<TvBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public static void getTvAiringToday(ApiInterface apiInterface, final MutableLiveData<List<TvBody.TvDataStructure>> tvsList){
        Call<TvBody> call = apiInterface.getTvAiringToday();
        call.enqueue(new Callback<TvBody>() {
            @Override
            public void onResponse(Call<TvBody> call, Response<TvBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }
                TvBody tvBody = response.body();

                assert tvBody != null;
                tvsList.setValue(tvBody.getResults());
            }

            @Override
            public void onFailure(Call<TvBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    /**
     *  M O V I E S
     * */
    public static void getTopRatedMovies(ApiInterface apiInterface,
                                            final MutableLiveData<List<MoviesBody.MovieDataStructure>> moviesList){
        Call<MoviesBody> call = apiInterface.getTopRatedMovies();
        call.enqueue(new Callback<MoviesBody>() {
            @Override
            public void onResponse(Call<MoviesBody> call, Response<MoviesBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                MoviesBody moviesBody = response.body();
                assert moviesBody != null;
                moviesList.setValue(moviesBody.getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviesBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public static void getMostPopularMovies(ApiInterface apiInterface,
                                           final MutableLiveData<List<MoviesBody.MovieDataStructure>> moviesList){
        Call<MoviesBody> call = apiInterface.getMostPopularMovies();
        call.enqueue(new Callback<MoviesBody>() {
            @Override
            public void onResponse(Call<MoviesBody> call, Response<MoviesBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                MoviesBody moviesBody = response.body();
                assert moviesBody != null;
                moviesList.setValue(moviesBody.getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviesBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public static void getNowPlayingMovies(ApiInterface apiInterface,
                                           final MutableLiveData<List<MoviesBody.MovieDataStructure>> moviesList,
                                           String pageNumber){
        Call<MoviesBody> call = apiInterface.getNowPlayingMovies(pageNumber);
        call.enqueue(new Callback<MoviesBody>() {
            @Override
            public void onResponse(Call<MoviesBody> call, Response<MoviesBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }

                MoviesBody moviesBody = response.body();
                assert moviesBody != null;
                moviesList.setValue(moviesBody.getMoviesList());
            }

            @Override
            public void onFailure(Call<MoviesBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public static void getUpcomingMovies(ApiInterface apiInterface,
                                         final MutableLiveData<List<MoviesBody.MovieDataStructure>> upcomingMoviesList)
    {
        Call<UpcomingBody> call = apiInterface.getUpcomingMovies("1");
        call.enqueue(new Callback<UpcomingBody>() {
            @Override
            public void onResponse(Call<UpcomingBody> call, Response<UpcomingBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }
                UpcomingBody body = new UpcomingBody();
                body = response.body();
                assert body != null;

                upcomingMoviesList.setValue(body.getResults());
            }
            @Override
            public void onFailure(Call<UpcomingBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public static void getTrendingMovies(String weekORday,
                                         ApiInterface apiInterface,
                                         final MutableLiveData<List<MoviesBody.MovieDataStructure>> moviesList){
        Call<MoviesBody> call = apiInterface.getTrendingMoviesWeek(weekORday);
        call.enqueue(new Callback<MoviesBody>() {
            @Override
            public void onResponse(Call<MoviesBody> call, Response<MoviesBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }
                MoviesBody trendingMoviesBody = new MoviesBody();
                trendingMoviesBody = response.body();

                assert trendingMoviesBody != null;
                moviesList.setValue(trendingMoviesBody.getMoviesList());
            }
            @Override
            public void onFailure(Call<MoviesBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public static void getVideosTvOrMovie(String tvORmovie, String id,
                                 final MutableLiveData<List<VideosBody.VideosList>> videosList){
        Call<VideosBody> call = buildRetrofit().getVideos(tvORmovie, id);
        call.enqueue(new Callback<VideosBody>() {
            @Override
            public void onResponse(Call<VideosBody> call, Response<VideosBody> response) {
                if (!response.isSuccessful()){
                    Log.e("Retrofit Utilities", String.valueOf(response.code()));
                    return;
                }
                VideosBody videosBody = response.body();
                videosList.setValue(videosBody.getVideosList());
            }

            @Override
            public void onFailure(Call<VideosBody> call, Throwable t) {
                Log.e("Retrofit Utilities", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
