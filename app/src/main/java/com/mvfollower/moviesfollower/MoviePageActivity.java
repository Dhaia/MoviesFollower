package com.mvfollower.moviesfollower;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.adapters.TrailersRecyclerViewAdapter;
import com.mvfollower.moviesfollower.adapters.MoviesRecyclerViewAdapter;
import com.mvfollower.moviesfollower.adapters.TvRecyclerViewAdapter;
import com.mvfollower.moviesfollower.database.AppExecutors;
import com.mvfollower.moviesfollower.database.FavoritesAppDatabase;
import com.mvfollower.moviesfollower.database.FavoritesObjectEntity;
import com.mvfollower.moviesfollower.network.ApiInterface;
import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.pojos.TvBody;
import com.mvfollower.moviesfollower.pojos.VideosBody;
import com.mvfollower.moviesfollower.utilities.HashMapUtilities;
import com.mvfollower.moviesfollower.network.RetrofitUtilities;
import com.mvfollower.moviesfollower.utilities.RatingUtilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviePageActivity extends AppCompatActivity {
    ImageView backDropImageView;
    TextView titleTextView;
    TextView releaseDateTextView;
    ImageView posterImageView;
    TextView ratingTextView;
    TextView overviewTextView;
    TextView genreTextView;
    TextView voteCountTextView;
    RecyclerView trailersRecyclerView;
    TextView similar_textView;
    ImageView starsImageView;

    List<VideosBody.VideosList> adapterEmptyList = new ArrayList<>();
    List<TvBody.TvDataStructure> tvDataStructureArrayList = new ArrayList<>();
    List<MoviesBody.MovieDataStructure> movieDataStructureArrayList = new ArrayList<>();

    MutableLiveData<List<VideosBody.VideosList>> videosList = new MutableLiveData<>();
    TrailersRecyclerViewAdapter trailersRecyclerViewAdapter;
    RecyclerView similarRecyclerView;

    String title;
    String backDropPath;
    String posterPath;
    String voteCount;
    String voteAverage;
    String releaseDate;
    String overview;
    ArrayList<Integer> genreIds;
    ApiInterface apiInterface;
    String itemId;
    String itemType;
    FavoritesAppDatabase favoritesAppDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page_activity);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationBar));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initializeViews();

        HashMap<Integer,String> genresHashMap = new HashMap<Integer,String>();
        HashMapUtilities.fillHashMap(genresHashMap);

        Intent intent = getIntent();
        backDropPath = intent.getStringExtra("backDrop");
        String url = "https://image.tmdb.org/t/p/w780" + backDropPath;
        Glide.with(this).load(url).into(backDropImageView);

        title = " " + intent.getStringExtra("title");
        titleTextView.setText(title);

        releaseDate = intent.getStringExtra("releaseDate");
        releaseDateTextView.setText(releaseDate);

        posterPath = intent.getStringExtra("poster");
        String posterUrl = "https://image.tmdb.org/t/p/w780" + posterPath;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
        Glide.with(this).load(posterUrl)
                .apply(requestOptions)
                .into(posterImageView);

        voteAverage = intent.getStringExtra("voteAverage");
        ratingTextView.setText(String.valueOf(voteAverage));
        double ratingDouble = Double.parseDouble(voteAverage);
        RatingUtilities.setupStars(starsImageView, ratingDouble, ratingTextView);

        overview = intent.getStringExtra("overview");
        overviewTextView.setText(overview);

        StringBuilder genreTextViewText = new StringBuilder();
        genreIds = intent.getIntegerArrayListExtra("genre");

        assert genreIds != null;
        for(int i = 0; i < genreIds.size(); i++){
            for(Map.Entry hashMapObject : genresHashMap.entrySet()){
                if(genreIds.get(i) == hashMapObject.getKey()){
                    genreTextViewText.append(hashMapObject.getValue()).append(", ");
                }
            }
        }

        String genresString = genreTextViewText.toString();
        if (genresString.length() > 1)
            genresString = genresString.substring(0, genresString.length() - 2);
        genreTextView.setText(genresString);

        voteCount = intent.getStringExtra("voteCount");
        String count = voteCount + getString(R.string.itemPageVotedText);
        voteCountTextView.setText(count);

        int id = intent.getIntExtra("id", 0);
        itemId = String.valueOf(id);
        String tvORmovie;
        boolean isMovie;
        if(intent.getAction().equals("movie")){
            itemType = "movie";
            isMovie = true;
            tvORmovie = "movie";
        }else{
            itemType = "tv";
            isMovie = false;
            tvORmovie = "tv";
        }

        trailersRecyclerViewAdapter = new TrailersRecyclerViewAdapter(this, adapterEmptyList);
        trailersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trailersRecyclerView.setAdapter(trailersRecyclerViewAdapter);
        RetrofitUtilities.getVideosTvOrMovie(tvORmovie, String.valueOf(id), videosList);
        videosList.observe(this, videosLists -> trailersRecyclerViewAdapter.updateData(videosLists));
        trailersRecyclerView.setNestedScrollingEnabled(false);

        similarRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        MoviesRecyclerViewAdapter moviesRecyclerViewAdapter = new MoviesRecyclerViewAdapter(this, movieDataStructureArrayList);
        TvRecyclerViewAdapter tvRecyclerViewAdapter = new TvRecyclerViewAdapter(this, tvDataStructureArrayList);
        MutableLiveData<List<TvBody.TvDataStructure>> tvDataStructureMutableLiveData = new MutableLiveData<>();
        MutableLiveData<List<MoviesBody.MovieDataStructure>> movieDataStructureMutableLiveData = new MutableLiveData<>();
        if(isMovie){
            similarRecyclerView.setAdapter(moviesRecyclerViewAdapter);
            RetrofitUtilities.geySimilarMovies(apiInterface, String.valueOf(id), movieDataStructureMutableLiveData);
            movieDataStructureMutableLiveData.observe(this, movieDataStructures -> moviesRecyclerViewAdapter.setMovieList(movieDataStructures));
            similar_textView.setText(R.string.similar_movies);
        }else {
            similarRecyclerView.setAdapter(tvRecyclerViewAdapter);
            RetrofitUtilities.geySimilarTvs(apiInterface, String.valueOf(id), tvDataStructureMutableLiveData);
            tvDataStructureMutableLiveData.observe(this, tvDataStructures -> tvRecyclerViewAdapter.setTvList(tvDataStructures));
            similar_textView.setText(R.string.similar_tv_thows);
        }
        similarRecyclerView.setNestedScrollingEnabled(false);

        favoritesAppDatabase = FavoritesAppDatabase.getInstance(getApplicationContext());
    }

    public void initializeViews(){
        backDropImageView = findViewById(R.id.movie_backDrop_imageView);
        releaseDateTextView = findViewById(R.id.moviePage_releaseDate);
        posterImageView = findViewById(R.id.moviePage_poster);
        ratingTextView = findViewById(R.id.moviePage_rating);
        titleTextView = findViewById(R.id.moviePage_title);
        overviewTextView = findViewById(R.id.moviePage_overview);
        genreTextView = findViewById(R.id.genre_textView);
        voteCountTextView = findViewById(R.id.moviePage_voteCount);
        trailersRecyclerView = findViewById(R.id.trailers_recyclerView);
        similarRecyclerView = findViewById(R.id.similar_recyclerView);
        apiInterface = RetrofitUtilities.buildRetrofit();
        similar_textView = findViewById(R.id.similar_textView);
        starsImageView = findViewById(R.id.moviePageStarsImageView);
    }

    public void favoritesButtonClicked(View view){
        final FavoritesObjectEntity favoritesObjectEntity = new FavoritesObjectEntity(title, backDropPath,
                posterPath, voteCount, voteAverage, releaseDate, overview, genreIds, new Date(), itemId, itemType);
        AppExecutors.getInstance().diskIO().execute(() -> favoritesAppDatabase.favoritesDao().insertItem(favoritesObjectEntity));
        Toast.makeText(this, title + " was added", Toast.LENGTH_SHORT).show();
    }
}
