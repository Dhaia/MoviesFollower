package com.mvfollower.moviesfollower.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.pojos.VideosBody;
import com.mvfollower.moviesfollower.R;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;

public class TrailersRecyclerViewAdapter extends RecyclerView.Adapter<TrailersRecyclerViewAdapter.ImagesViewHolder> {
    private Context context;
    private List<VideosBody.VideosList> backdropList;
    private static final String YOUTUBE_API_KEY = "Enter Your API key";

    public TrailersRecyclerViewAdapter(Context context, List<VideosBody.VideosList> backdropList) {
        this.context = context;
        this.backdropList = backdropList;
    }

    public void updateData(List<VideosBody.VideosList> backdropList){
        this.backdropList = backdropList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.trailers_custom_layout, parent, false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        VideosBody.VideosList trailer = backdropList.get(position);
        String finalUrl = "https://img.youtube.com/vi/" + trailer.getKey() + "/maxresdefault.jpg";

        Glide.with(context).load(finalUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(backdropList == null){
            return 0;
        }
        return backdropList.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.trailers_thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            VideosBody.VideosList videosList = backdropList.get(getAdapterPosition());
            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, YOUTUBE_API_KEY, videosList.getKey());
            context.startActivity(intent);
        }
    }
}
