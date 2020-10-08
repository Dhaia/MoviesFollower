package com.mvfollower.moviesfollower.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mvfollower.moviesfollower.databases.FavoritesObjectEntity;
import com.mvfollower.moviesfollower.R;
import com.mvfollower.moviesfollower.utilities.HashMapUtilities;
import com.mvfollower.moviesfollower.utilities.RatingUtilities;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.FavoritesViewHolder> {
    private Context context;
    private List<FavoritesObjectEntity> moviesList;
    final private FavoritesClickListener mOnClickListener;
    private static final String DATE_FORMAT = "dd MM yyyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public interface FavoritesClickListener {
        void onFavoritesListItemClick(int clickedItemIndex);
    }

    public FavoritesRecyclerViewAdapter(Context context, List<FavoritesObjectEntity> moviesList, FavoritesClickListener mOnClickListener) {
        this.context = context;
        this.moviesList = moviesList;
        this.mOnClickListener = mOnClickListener;
    }

    public List<FavoritesObjectEntity> getMoviesList() {
        return moviesList;
    }

    public void setData(List<FavoritesObjectEntity> moviesList){
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView poster;
        ImageView stars;
        TextView title;
        TextView releaseDate;
        TextView adult;
        TextView genres;
        TextView favorites_rating_textView;
        TextView addedOn;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            stars = itemView.findViewById(R.id.item_stars_imageView);
            poster = itemView.findViewById(R.id.favorites_poster_imageView);
            title = itemView.findViewById(R.id.favorites_title_textView);
            releaseDate = itemView.findViewById(R.id.favorites_releaseDate_textView);
            adult = itemView.findViewById(R.id.favorites_adult_textView);
            genres = itemView.findViewById(R.id.favorites_genre_textView);
            favorites_rating_textView = itemView.findViewById(R.id.item_rating_textView);
            addedOn = itemView.findViewById(R.id.favorites_updatedAt_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onFavoritesListItemClick(clickedPosition);
        }
    }

    @NonNull
    @Override
    public FavoritesRecyclerViewAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.favorites_item, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesRecyclerViewAdapter.FavoritesViewHolder holder, int position) {
        FavoritesObjectEntity favoritesObjectEntity = moviesList.get(position);

        FavoritesViewHolder favoritesViewHolder = (FavoritesViewHolder) holder;
        String title = favoritesObjectEntity.getTitle();
        String releaseDate = favoritesObjectEntity.getReleaseDate();
        ArrayList<Integer> genres = favoritesObjectEntity.getGenreIds();
        String firstRating = favoritesObjectEntity.getVoteAverage();
        Double rating = Double.parseDouble(firstRating);

        String poster = favoritesObjectEntity.getPosterPath();
        String posterUrl = "https://image.tmdb.org/t/p/w780" + poster;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(25));
        Glide.with(context).load(posterUrl)
                .apply(requestOptions)
                .into(favoritesViewHolder.poster);

        favoritesViewHolder.title.setText(title);

        String releaseDateString = "Released: ";
        for (int i = 0; i < releaseDate.length(); i++){
            if(i != 4 && i != 7){
                releaseDateString += releaseDate.charAt(i);
            }else {
                releaseDateString += " ";
            }
        }
        favoritesViewHolder.releaseDate.setText(releaseDateString);

        HashMap<Integer,String> genresHashMap = new HashMap<Integer,String>();
        HashMapUtilities.fillHashMap(genresHashMap);
        StringBuilder genreTextViewText = new StringBuilder();
        for(int i = 0; i < genres.size(); i++){
            for(Map.Entry hashMapObject : genresHashMap.entrySet()){
                if(genres.get(i) == hashMapObject.getKey()){
                    genreTextViewText.append(hashMapObject.getValue() + ", ");
                }
            }
        }
        String genresFinal = genreTextViewText.toString();
        if(genresFinal.length() > 2){
            genresFinal = genresFinal.substring(0, genresFinal.length() - 2);
        }
        favoritesViewHolder.genres.setText(genresFinal);

        Date addedDate = favoritesObjectEntity.getUpdatedAt();
        String updatedAtString = "Added on " + dateFormat.format(addedDate);
        favoritesViewHolder.addedOn.setText(updatedAtString);
        RatingUtilities.setupStars(favoritesViewHolder.stars, rating, favoritesViewHolder.favorites_rating_textView);
    }

    @Override
    public int getItemCount() {
       if (moviesList != null){
           return moviesList.size();
       }
       return 0;
    }
}
