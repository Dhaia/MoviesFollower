package com.mvfollower.moviesfollower.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.adapters.TvRecyclerViewAdapter;
import com.mvfollower.moviesfollower.data.MainViewModel;
import com.mvfollower.moviesfollower.data.MainViewModelFactory;
import com.mvfollower.moviesfollower.pojos.TvBody;
import com.mvfollower.moviesfollower.R;
import com.mvfollower.moviesfollower.utilities.RetrofitUtilities;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchTvFragment extends Fragment {

    private String ACTION_FILTER = "filter";
    private String ACTION_ACTION = "action";
    private String ACTION_SEARCH = "search";
    private String ACTION_HORROR = "horror";
    private String ACTION_ANIMATION = "animation";
    private String ACTION_COMEDY = "comedy";
    private String genre_id = "";
    private String action;
    private String year = "";
    private String genre_id_discover = "";
    private String sortBy = "";
    private String searchQuery = "";
    private List<TvBody.TvDataStructure> tvDataStructures = new ArrayList<>();
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.search_results_fragment, container, false));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.search_result_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        TvRecyclerViewAdapter tvRecyclerViewAdapter = new TvRecyclerViewAdapter(getContext(), tvDataStructures);
        recyclerView.setAdapter(tvRecyclerViewAdapter);
        int orientation = getResources().getConfiguration().orientation;
        if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        }

        if (savedInstanceState == null){
            if (action.equals(ACTION_ACTION)){
                genre_id = "28";
            }else if(action.equals(ACTION_HORROR)){
                genre_id = "27";
            }else if(action.equals(ACTION_COMEDY)){
                genre_id = "35";
            }else if(action.equals(ACTION_ANIMATION)){
                genre_id = "16";}
        }else{
            if (Objects.equals(savedInstanceState.getString("action"), ACTION_ACTION)){
                genre_id = "28";
            }else if(Objects.equals(savedInstanceState.getString("action"), ACTION_HORROR)){
                genre_id = "27";
            }else if(Objects.equals(savedInstanceState.getString("action"), ACTION_COMEDY)){
                genre_id = "35";
            }else if(Objects.equals(savedInstanceState.getString("action"), ACTION_ANIMATION)){
                genre_id = "16";}
        }

        MainViewModelFactory factory;
        if(savedInstanceState == null){
            factory = new MainViewModelFactory(genre_id, searchQuery);
            MainViewModel mainViewModel = new ViewModelProvider(requireActivity(), factory).get(MainViewModel.class);
            MutableLiveData<List<TvBody.TvDataStructure>> mutableLiveData = new MutableLiveData<>();

            if (!action.equals(ACTION_SEARCH) && !action.equals(ACTION_FILTER)) {
                mainViewModel.getDiscoverTv().observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
                    @Override
                    public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                        tvRecyclerViewAdapter.setTvList(tvDataStructures);
                    }
                }); } else if(action.equals(ACTION_SEARCH)){
                RetrofitUtilities.getQueryTv(RetrofitUtilities.buildRetrofit(), searchQuery, mutableLiveData);
                mutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
                    @Override
                    public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                        tvRecyclerViewAdapter.setTvList(tvDataStructures);
                    }
                });
            }else if(action.equals(ACTION_FILTER)){
                RetrofitUtilities.getDiscoverTv(RetrofitUtilities.buildRetrofit(), genre_id_discover, sortBy, year, mutableLiveData);
                mutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
                    @Override
                    public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                        tvRecyclerViewAdapter.setTvList(tvDataStructures);
                    }
                });
            }
        }else {
            factory = new MainViewModelFactory(genre_id, savedInstanceState.getString("query"));
            MainViewModel mainViewModel = new ViewModelProvider(requireActivity(), factory).get(MainViewModel.class);
            MutableLiveData<List<TvBody.TvDataStructure>> mutableLiveData = new MutableLiveData<>();

            if (!Objects.equals(savedInstanceState.getString("action"), ACTION_SEARCH) && !Objects.equals(savedInstanceState.getString("action"), ACTION_FILTER)) {
                mainViewModel.getDiscoverTv().observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
                    @Override
                    public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                        tvRecyclerViewAdapter.setTvList(tvDataStructures);
                    }
                }); } else if(Objects.equals(savedInstanceState.getString("action"), ACTION_SEARCH)){

                RetrofitUtilities.getQueryTv(RetrofitUtilities.buildRetrofit(), searchQuery, mutableLiveData);
                mutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
                    @Override
                    public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                        tvRecyclerViewAdapter.setTvList(tvDataStructures);
                    }
                });
            }else if(Objects.equals(savedInstanceState.getString("action"), ACTION_FILTER)){

                RetrofitUtilities.getDiscoverTv(RetrofitUtilities.buildRetrofit(), genre_id_discover, sortBy, year, mutableLiveData);
                mutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<TvBody.TvDataStructure>>() {
                    @Override
                    public void onChanged(List<TvBody.TvDataStructure> tvDataStructures) {
                        tvRecyclerViewAdapter.setTvList(tvDataStructures);
                    }
                });
            }
        }
    }

    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("action", action);
        outState.putString("query", searchQuery);
        outState.putString("genre_id_discover", genre_id_discover);
        outState.putString("sortBy", sortBy);
    }

    public void setAction(String action){ this.action = action; }
    public void setQueries(String searchQuery, String sortBy, String genre_id_discover, String year){
        this.searchQuery = searchQuery;
        this.genre_id_discover = genre_id_discover;
        this.sortBy = sortBy;
        this.year = year;
    }
}
