package com.mvfollower.moviesfollower.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.adapters.MoviesRecyclerViewAdapter;
import com.mvfollower.moviesfollower.adapters.UpcomingRecyclerViewAdapter;
import com.mvfollower.moviesfollower.data.MainViewModel;
import com.mvfollower.moviesfollower.data.MainViewModelFactory;
import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.R;

import java.util.List;

public class MoviesFragment extends Fragment {

    RecyclerView upcomingRecyclerView;
    RecyclerView trendingRecyclerView;
    RecyclerView nowRecyclerView;
    RecyclerView mostPopularRecyclerView;
    RecyclerView topRatedRecyclerView;
    View root;
    MainViewModel mainViewModel;

    List<MoviesBody.MovieDataStructure> moviesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movies, container, false);

        Log.d("Movies Fragment", "CreateView Called");
        initializeViews();

        upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        final UpcomingRecyclerViewAdapter upcomingRecyclerViewAdapter = new UpcomingRecyclerViewAdapter(getContext(), moviesList);
        upcomingRecyclerView.setAdapter(upcomingRecyclerViewAdapter);
        mainViewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<List<MoviesBody.MovieDataStructure>>() {
            @Override
            public void onChanged(List<MoviesBody.MovieDataStructure> movieDataStructures) {
                upcomingRecyclerViewAdapter.updateData(movieDataStructures);
            }
        });
        upcomingRecyclerView.setNestedScrollingEnabled(false);

        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        final MoviesRecyclerViewAdapter trendingTvRecyclerViewAdapter = new MoviesRecyclerViewAdapter(getContext(), moviesList);
        trendingRecyclerView.setAdapter(trendingTvRecyclerViewAdapter);
        mainViewModel.getTrendingMoviesWeek().observe(getViewLifecycleOwner(), new Observer<List<MoviesBody.MovieDataStructure>>() {
            @Override
            public void onChanged(List<MoviesBody.MovieDataStructure> movieDataStructures) {
                trendingTvRecyclerViewAdapter.setMovieList(movieDataStructures);
            }
        });
        trendingRecyclerView.setNestedScrollingEnabled(false);

        nowRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        final MoviesRecyclerViewAdapter nowAdapter = new MoviesRecyclerViewAdapter(getContext(), moviesList);
        nowRecyclerView.setAdapter(nowAdapter);
        mainViewModel.getNowPlayingMoviesList().observe(getViewLifecycleOwner(), new Observer<List<MoviesBody.MovieDataStructure>>() {
            @Override
            public void onChanged(List<MoviesBody.MovieDataStructure> movieDataStructures) {
                nowAdapter.setMovieList(movieDataStructures);
            }
        });
        nowRecyclerView.setNestedScrollingEnabled(false);

        mostPopularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        final MoviesRecyclerViewAdapter mostPopularAdapter = new MoviesRecyclerViewAdapter(getContext(), moviesList);
        mostPopularRecyclerView.setAdapter(mostPopularAdapter);
        mainViewModel.getMostPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<List<MoviesBody.MovieDataStructure>>() {
            @Override
            public void onChanged(List<MoviesBody.MovieDataStructure> movieDataStructures) {
                mostPopularAdapter.setMovieList(movieDataStructures);
            }
        });
        mostPopularRecyclerView.setNestedScrollingEnabled(false);

        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        final MoviesRecyclerViewAdapter topRatedAdapter = new MoviesRecyclerViewAdapter(getContext(), moviesList);
        topRatedRecyclerView.setAdapter(topRatedAdapter);
        mainViewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<List<MoviesBody.MovieDataStructure>>() {
            @Override
            public void onChanged(List<MoviesBody.MovieDataStructure> movieDataStructures) {
                topRatedAdapter.setMovieList(movieDataStructures);
            }
        });
        topRatedRecyclerView.setNestedScrollingEnabled(false);

        return root;
    }

    private void initializeViews(){
        upcomingRecyclerView = root.findViewById(R.id.upcoming_recyclerView);
        trendingRecyclerView = root.findViewById(R.id.trending_recyclerView);
        nowRecyclerView = root.findViewById(R.id.nowPlaying_recyclerView);
        mostPopularRecyclerView = root.findViewById(R.id.mostPopular_recyclerView);
        topRatedRecyclerView = root.findViewById(R.id.topRated_recyclerView);

        MainViewModelFactory factory = new MainViewModelFactory("", "");
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
    }
}
