package com.mvfollower.moviesfollower.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.MoviePageActivity;
import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.R;
import com.mvfollower.moviesfollower.utilities.IntentUtilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class UpcomingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MoviesBody.MovieDataStructure> itemsLits;
    private Context mContext;
    public UpcomingRecyclerViewAdapter(@NonNull Context context, List<MoviesBody.MovieDataStructure> mMoviesList) {
        this.itemsLits = mMoviesList;
        this.mContext = context;
    }

    public void updateData(List<MoviesBody.MovieDataStructure> movieList) {
        itemsLits = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.upcoming_item_layout, viewGroup, false);
        return new UpcomingViewHolder(view);

//        int layoutIdForListItem = R.layout.item;
//        LayoutInflater inflater = LayoutInflater.from(context);
//        boolean shouldAttachToParentImmediately = false;
//
//        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
//        return new TrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MoviesBody.MovieDataStructure item = itemsLits.get(position);
        String title = " " + item.getTitle();
        String releaseDate = item.getRelease_date();
        String backDropPath = item.getBackdrop_path();
        String finalUrl = "https://image.tmdb.org/t/p/w780" + backDropPath;
        String poster = item.getPoster_path();

        UpcomingViewHolder upcomingViewHolder = (UpcomingViewHolder) holder;
        upcomingViewHolder.title.setText(title);

        String releaseDateString = "";
        for (int i = 0; i < releaseDate.length(); i++){
            if(i != 4 && i != 7){
                releaseDateString += releaseDate.charAt(i);
            }else {
                releaseDateString += " ";
            }
        }
        upcomingViewHolder.releaseDate.setText(releaseDateString);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(15));
        Glide.with(mContext).load(finalUrl)
                .apply(requestOptions)
                .into(upcomingViewHolder.backDropImageView);
    }

    @Override
    public int getItemCount() {
        if(itemsLits != null){
            return itemsLits.size();
        }
        return 0;
    }


    class UpcomingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView releaseDate;
        TextView title;
        ImageView backDropImageView;

        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.upcomingDateTextView);
            title = itemView.findViewById(R.id.upcomingTitleTextView);
            backDropImageView = itemView.findViewById(R.id.upcomingBackDrop);
            Log.d("viewHolder", "Added");
            itemView.setOnClickListener(this);
            itemView.isClickable();
        }

        @Override
        public void onClick(View v) {
            MoviesBody.MovieDataStructure movieDataStructure = itemsLits.get(getAdapterPosition());
            Intent intent = new Intent(mContext, MoviePageActivity.class);
            IntentUtilities.setIntentExtras(intent, movieDataStructure);
            mContext.startActivity(intent);
        }
    }
}
