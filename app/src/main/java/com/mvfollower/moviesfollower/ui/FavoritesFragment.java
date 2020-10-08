package com.mvfollower.moviesfollower.ui;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.adapters.FavoritesRecyclerViewAdapter;
import com.mvfollower.moviesfollower.databases.AppExecutors;
import com.mvfollower.moviesfollower.databases.DatabaseViewModel;
import com.mvfollower.moviesfollower.databases.FavoritesAppDatabase;
import com.mvfollower.moviesfollower.databases.FavoritesObjectEntity;
import com.mvfollower.moviesfollower.MoviePageActivity;
import com.mvfollower.moviesfollower.R;
import com.mvfollower.moviesfollower.utilities.IntentUtitilities;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FavoritesFragment extends Fragment implements FavoritesRecyclerViewAdapter.FavoritesClickListener{

    RecyclerView recyclerView;
    FavoritesRecyclerViewAdapter favoritesRecyclerViewAdapter;
    List<FavoritesObjectEntity> itemsList;
    ImageView noFavImageView;
    TextView noFavTextView;
    TextView noFavTextView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    FavoritesAppDatabase favoritesAppDatabase;
    ConstraintLayout constraintLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        constraintLayout = view.findViewById(R.id.favorites_constraint_layout);

        recyclerView = view.findViewById(R.id.favorites_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        favoritesRecyclerViewAdapter = new FavoritesRecyclerViewAdapter(getContext(), itemsList, this);
        recyclerView.setAdapter(favoritesRecyclerViewAdapter);

        noFavImageView = view.findViewById(R.id.noFavImageView);
        noFavTextView2 = view.findViewById(R.id.noFaveTextView2);
        noFavTextView = view.findViewById(R.id.noFavTextView);

        favoritesAppDatabase = FavoritesAppDatabase.getInstance(getActivity());
        DatabaseViewModel databaseViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
//        LiveData<List<FavoritesObjectEntity>>  list = favoritesAppDatabase.favoritesDao().loadAllData();
        databaseViewModel.getFavoritesList().observe(getViewLifecycleOwner(), new Observer<List<FavoritesObjectEntity>>() {
            @Override
            public void onChanged(List<FavoritesObjectEntity> favoritesObjectEntities) {
                itemsList = favoritesObjectEntities;
                favoritesRecyclerViewAdapter.setData(favoritesObjectEntities);

                if(favoritesObjectEntities.size() == 0){
                    noFavImageView.setVisibility(View.VISIBLE);
                    noFavTextView.setVisibility(View.VISIBLE);
                    noFavTextView2.setVisibility(View.VISIBLE);
                    Log.d("getValue ", "it is null");
                }else {
                    noFavImageView.setVisibility(View.INVISIBLE);
                    noFavTextView.setVisibility(View.INVISIBLE);
                    noFavTextView2.setVisibility(View.INVISIBLE);
                    Log.d("getValue ", "Not null");
                }
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT){
            @Override
            public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            // Called when item_ripple user swipes left or right on item_ripple ViewHolder
            @Override
            public void onSwiped(@NotNull final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final int position = viewHolder.getAdapterPosition();
                final List<FavoritesObjectEntity> list = favoritesRecyclerViewAdapter.getMoviesList();
                final FavoritesObjectEntity deletedItem = list.get(position);
                list.remove(position);
                favoritesRecyclerViewAdapter.notifyDataSetChanged();

                showSnackBar(position, list, deletedItem);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(getContext(), R.color.deleteBackground))
                        .addActionIcon(R.drawable.delete_vector)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void showSnackBar(final int position,
                             final List<FavoritesObjectEntity> list,
                             final FavoritesObjectEntity deletedItem){

        Snackbar.make(constraintLayout, "1 item deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //undo delete
                        list.add(position, deletedItem);
                        favoritesRecyclerViewAdapter.notifyDataSetChanged();
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.white))
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .addCallback(new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                        if (event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_SWIPE
                                || event == DISMISS_EVENT_CONSECUTIVE || event == DISMISS_EVENT_MANUAL) {

                            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    favoritesAppDatabase.favoritesDao().deleteMovie(deletedItem);
                                }
                            });
                        }
                    }
                }).show();
    }

    @Override
    public void onFavoritesListItemClick(int clickedItemIndex) {
        FavoritesObjectEntity clickedItem = itemsList.get(clickedItemIndex);
        Intent intent = new Intent(getActivity(), MoviePageActivity.class);
        IntentUtitilities.setIntentExtras(intent, clickedItem);
        startActivity(intent);
    }
}
