package com.mvfollower.moviesfollower.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.MoviePageActivity;
import com.mvfollower.moviesfollower.pojos.TvBody;
import com.mvfollower.moviesfollower.R;
import com.mvfollower.moviesfollower.utilities.IntentUtitilities;
import com.mvfollower.moviesfollower.utilities.RatingUtilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class TvRecyclerViewAdapter extends RecyclerView.Adapter<TvRecyclerViewAdapter.TrendingTvViewHolder>{
    private Context context;
    private List<TvBody.TvDataStructure> tvList;

    public TvRecyclerViewAdapter(Context context, List<TvBody.TvDataStructure> tvList) {
        this.context = context;
        this.tvList = tvList;
    }
    public void setTvList(List<TvBody.TvDataStructure> tvList) {
        this.tvList = tvList;
        notifyDataSetChanged();
    }

    class TrendingTvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView;
        ImageView posterImageView;
        ImageView ratingImageView;
        TextView ratingTextView;

        public TrendingTvViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_title_textView);
            posterImageView = itemView.findViewById(R.id.item_poster_imageView);
            ratingImageView = itemView.findViewById(R.id.item_stars_imageView);
            ratingTextView = itemView.findViewById(R.id.item_rating_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TvBody.TvDataStructure tvDataStructure = tvList.get(getAdapterPosition());
            Intent intent = new Intent(context, MoviePageActivity.class);
            IntentUtitilities.setIntentExtras(intent, tvDataStructure);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public TvRecyclerViewAdapter.TrendingTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.item, parent, false);
        return new TrendingTvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvRecyclerViewAdapter.TrendingTvViewHolder holder, int position) {
        TvBody.TvDataStructure item = tvList.get(position);
        String title = " " + item.getTitle();
        double rating = item.getVote_average();
        String poster = item.getPoster_path();
        String posterUrl = "https://image.tmdb.org/t/p/w342" + poster;

        TrendingTvViewHolder trendingTvViewHolder = (TrendingTvViewHolder) holder;
        trendingTvViewHolder.titleTextView.setText(title);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(15));
        Glide.with(context).load(posterUrl)
                .apply(requestOptions)
                .into(trendingTvViewHolder.posterImageView);

        RatingUtilities.setupStars(trendingTvViewHolder.ratingImageView, rating, trendingTvViewHolder.ratingTextView);
    }

    @Override
    public int getItemCount() {
        if (tvList != null){
            return tvList.size();
        }
        return 0;
    }
}
