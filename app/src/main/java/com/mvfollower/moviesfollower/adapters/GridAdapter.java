package com.mvfollower.moviesfollower.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvfollower.moviesfollower.pojos.MoviesBody;
import com.mvfollower.moviesfollower.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    Context context;
    List<MoviesBody.MovieDataStructure> list;
    Activity activity;
    public GridAdapter(Context context, List<MoviesBody.MovieDataStructure> list, Activity activity){
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    public void setList(List<MoviesBody.MovieDataStructure> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(list == null){
            return 0; }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        MoviesBody.MovieDataStructure movieDataStructure = list.get(position);

        View row;
        LayoutInflater inflater = activity.getLayoutInflater();
        row = inflater.inflate(R.layout.grid_item, parent, false);

        if (convertView == null) {
            row = inflater.inflate(R.layout.grid_item, parent, false);
        }
        TextView textView = null;
        ImageView imageView = null;
        if(row != null){
            imageView = row.findViewById(R.id.gridItemPoster);
            textView = row.findViewById(R.id.gridItemTitle);
        }

        String finalUrl = "https://image.tmdb.org/t/p/w780" + movieDataStructure.getBackdrop_path();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(15));
        assert imageView != null;
        Glide.with(context).load(finalUrl)
                .apply(requestOptions)
                .into(imageView);

        textView.setText(movieDataStructure.getTitle());
        return row;
    }
}
