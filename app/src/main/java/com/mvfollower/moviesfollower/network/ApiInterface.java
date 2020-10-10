package com.mvfollower.moviesfollower.network;

import android.content.res.Resources;

import com.bumptech.glide.load.engine.Resource;
import com.mvfollower.moviesfollower.BuildConfig;
import com.mvfollower.moviesfollower.R;
import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.pojos.TvBody;
import com.mvfollower.moviesfollower.pojos.UpcomingBody;
import com.mvfollower.moviesfollower.pojos.VideosBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String MDB_API_KEY = "Replace with your API key";

    @GET("movie/upcoming?api_key=" + MDB_API_KEY + "&language=en-US")
    Call<UpcomingBody> getUpcomingMovies(@Query("page") String pageNumber);

    @GET("trending/movie/{week}?api_key=" + MDB_API_KEY)
    Call<MoviesBody> getTrendingMoviesWeek(@Path("week") String weekOrDay);

    @GET("movie/now_playing?api_key=" + MDB_API_KEY + "&language=en-US")
    Call<MoviesBody> getNowPlayingMovies(@Query("page") String pageNumber);

    @GET("movie/popular?api_key=" + MDB_API_KEY + "&language=en-US")
    Call<MoviesBody> getMostPopularMovies();

    @GET("movie/top_rated?api_key=" + MDB_API_KEY + "&language=en-US")
    Call<MoviesBody> getTopRatedMovies();

    @GET("tv/airing_today?api_key=" + MDB_API_KEY)
    Call<TvBody> getTvAiringToday();

    @GET("trending/tv/week?api_key=" + MDB_API_KEY)
    Call<TvBody> getTrendingTv();

    @GET("tv/popular?api_key=" + MDB_API_KEY )
    Call<TvBody> getPopularTv();

    @GET("tv/top_rated?api_key=" + MDB_API_KEY)
    Call<TvBody> getTopRatedTv();

    @GET("{tv}/{movie_id}/videos?api_key=" + MDB_API_KEY + "&language=en-US")
    Call<VideosBody> getVideos(@Path("tv") String tvORmovie,
                               @Path("movie_id") String id);

    @GET("movie/{movie_id}/similar?api_key=" + MDB_API_KEY + "&language=en-US&page=1")
    Call<MoviesBody> getSimilarMovies(@Path("movie_id") String id);

    @GET("tv/{tv_id}/similar?api_key=" + MDB_API_KEY + "&language=en-US&page=1")
    Call<TvBody> getSimilarTvs(@Path("tv_id") String id);

    @GET("discover/movie?api_key=" + MDB_API_KEY)
    Call<MoviesBody> getDiscoverMovies(@Query("with_genres") String genre_id,
                                       @Query("sort_by") String sort_by,
                                       @Query("year") String year);

    @GET("discover/tv?api_key=" + MDB_API_KEY )
    Call<TvBody> getDiscoverTv(@Query("with_genres") String genre_id,
                               @Query("sort_by") String sort_by,
                               @Query("year") String year);

    @GET("search/movie?api_key=" + MDB_API_KEY)
    Call<MoviesBody> getMoviesQuery(@Query("query") String query);

    @GET("search/tv?api_key=" + MDB_API_KEY)
    Call<TvBody> getTvQuery(@Query("query") String query);
}