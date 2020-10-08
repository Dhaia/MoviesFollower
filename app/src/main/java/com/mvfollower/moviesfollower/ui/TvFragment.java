package com.mvfollower.moviesfollower.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvfollower.moviesfollower.adapters.AiringTodayRecyclerViewAdapter;
import com.mvfollower.moviesfollower.adapters.TvRecyclerViewAdapter;
import com.mvfollower.moviesfollower.data.MainViewModel;
import com.mvfollower.moviesfollower.data.MainViewModelFactory;
import com.mvfollower.moviesfollower.pojos.TvBody;
import com.mvfollower.moviesfollower.R;

import java.util.List;

public class TvFragment extends Fragment {

    RecyclerView airingTodayRecyclerView;
    RecyclerView trendingRecyclerView;
    RecyclerView mostPopularRecyclerView;
    RecyclerView topRatedRecyclerView;
    MainViewModel mainViewModel;

    List<TvBody.TvDataStructure> airingTodayList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);

        airingTodayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        AiringTodayRecyclerViewAdapter airingTodayRecyclerViewAdapter = new AiringTodayRecyclerViewAdapter(getContext(), airingTodayList);
        airingTodayRecyclerView.setAdapter(airingTodayRecyclerViewAdapter);
        mainViewModel.getTvAiringToday().observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
            @Override
            public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                airingTodayList = tvDataStructures;
                airingTodayRecyclerViewAdapter.updateData(tvDataStructures);
            }
        });
        airingTodayRecyclerView.setNestedScrollingEnabled(false);

        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        TvRecyclerViewAdapter trendingRecyclerViewAdapter = new TvRecyclerViewAdapter(getContext(), airingTodayList);
        trendingRecyclerView.setAdapter(trendingRecyclerViewAdapter);
        mainViewModel.getTrendingTv().observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
            @Override
            public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                trendingRecyclerViewAdapter.setTvList(tvDataStructures);
            }
        });
        trendingRecyclerView.setNestedScrollingEnabled(false);

        mostPopularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        TvRecyclerViewAdapter mostPopularRecyclerViewAdapter = new TvRecyclerViewAdapter(getContext(), airingTodayList);
        mostPopularRecyclerView.setAdapter(mostPopularRecyclerViewAdapter);
        mainViewModel.getTvMostPopular().observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
            @Override
            public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                mostPopularRecyclerViewAdapter.setTvList(tvDataStructures);
            }
        });
        mostPopularRecyclerView.setNestedScrollingEnabled(false);

        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        TvRecyclerViewAdapter topRatedRecyclerViewAdapter = new TvRecyclerViewAdapter(getContext(), airingTodayList);
        topRatedRecyclerView.setAdapter(topRatedRecyclerViewAdapter);
        mainViewModel.getTvTopRated().observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
            @Override
            public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                topRatedRecyclerViewAdapter.setTvList(tvDataStructures);
            }
        });
        topRatedRecyclerView.setNestedScrollingEnabled(false);

    }

    private void initializeViews(View view){
        MainViewModelFactory factory = new MainViewModelFactory("", "");
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        airingTodayRecyclerView = view.findViewById(R.id.airingToday_recyclerView);
        trendingRecyclerView = view.findViewById(R.id.tvTrending_recyclerView);
        mostPopularRecyclerView = view.findViewById(R.id.tvMostPopular_recyclerView);
        topRatedRecyclerView = view.findViewById(R.id.tvTopRated_recyclerView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }
}