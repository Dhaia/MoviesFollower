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
import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.R;
import com.mvfollower.moviesfollower.utilities.IntentUtilities;
import com.mvfollower.moviesfollower.utilities.RatingUtilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.TrendingViewHolder>{
    private List<MoviesBody.MovieDataStructure> mMoviesList;
    private Context mContext;

    public MoviesRecyclerViewAdapter(Context mContext, List<MoviesBody.MovieDataStructure> mMoviesList) {
        this.mMoviesList = mMoviesList;
        this.mContext = mContext;
    }

    public void setMovieList(List<MoviesBody.MovieDataStructure> movieList) {
        mMoviesList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.item, parent, false);
        return new TrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder holder, int position) {
        MoviesBody.MovieDataStructure item = mMoviesList.get(position);
        String title = " " + item.getTitle();
        double rating = item.getVote_average();
        String poster = item.getPoster_path();
        String posterUrl = "https://image.tmdb.org/t/p/w342" + poster;

        holder.titleTextView.setText(title);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(15));
        Glide.with(mContext).load(posterUrl)
                .apply(requestOptions)
                .into(holder.posterImageView);

        RatingUtilities.setupStars(holder.ratingImageView, rating, holder.ratingTextView);
    }

    @Override
    public int getItemCount() {
        if(mMoviesList != null){
            return mMoviesList.size();
        }
        return 0;
    }

    class TrendingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView;
        ImageView posterImageView;
        ImageView ratingImageView;
        TextView ratingTextView;

        public TrendingViewHolder(@NonNull View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_title_textView);
            posterImageView = itemView.findViewById(R.id.item_poster_imageView);
            ratingImageView = itemView.findViewById(R.id.item_stars_imageView);
            ratingTextView = itemView.findViewById(R.id.item_rating_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MoviesBody.MovieDataStructure movieDataStructure = mMoviesList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, MoviePageActivity.class);
            IntentUtilities.setIntentExtras(intent, movieDataStructure);
            mContext.startActivity(intent);
        }
    }
}
