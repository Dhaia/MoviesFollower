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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AiringTodayRecyclerViewAdapter extends RecyclerView.Adapter<AiringTodayRecyclerViewAdapter.AiringViewHolder> {

    private List<TvBody.TvDataStructure> tvList;
    private Context mContext;

    public AiringTodayRecyclerViewAdapter(Context mContext,List<TvBody.TvDataStructure> tvList) {
        this.tvList = tvList;
        this.mContext = mContext;
    }

    public void updateData(List<TvBody.TvDataStructure> tvList) {
        this.tvList = tvList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AiringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.upcoming_item_layout, parent, false);
        return new AiringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AiringViewHolder holder, int position) {
        TvBody.TvDataStructure tvDataStructure = tvList.get(position);
        holder.titleTextView.setText(tvDataStructure.getTitle());
        String finalUrl = "https://image.tmdb.org/t/p/w780" + tvDataStructure.getBackdrop_path();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(15));
        Glide.with(mContext).load(finalUrl)
                .apply(requestOptions)
                .into(holder.backDropImageView);
    }

    @Override
    public int getItemCount() {
        if(tvList == null){
            return 0;
        }
        return tvList.size();
    }

    public class AiringViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView backDropImageView;
        TextView titleTextView;

        public AiringViewHolder(@NonNull View itemView) {
            super(itemView);
            backDropImageView = itemView.findViewById(R.id.upcomingBackDrop);
            titleTextView = itemView.findViewById(R.id.upcomingTitleTextView);
            itemView.setOnClickListener(this);
            itemView.isClickable();
        }

        @Override
        public void onClick(View v) {
            TvBody.TvDataStructure tvDataStructure = tvList.get(getAdapterPosition());
            Intent intent = new Intent(mContext, MoviePageActivity.class);
            IntentUtitilities.setIntentExtras(intent, tvDataStructure);
            mContext.startActivity(intent);
        }
    }
}
