package com.mvfollower.moviesfollower.utilities;

import android.widget.ImageView;
import android.widget.TextView;

import com.mvfollower.moviesfollower.R;

import java.text.DecimalFormat;

public class RatingUtilities {
    public static void setupStars(ImageView imageView, double rating, TextView textView){
        double ratingScaleOfFive = rating / 2;
        DecimalFormat df = new DecimalFormat("####0.0");
        textView.setText(df.format(ratingScaleOfFive));
        if (rating == 10){
            textView.setText("5");
        }else if(rating >= 9){
            imageView.setImageResource(R.drawable.five_star);
        }else if(rating >= 8 & rating < 9){
            imageView.setImageResource(R.drawable.four_and_half_star);
        }else if(rating >= 7 & rating < 8){
            imageView.setImageResource(R.drawable.four_star);
        }else if(rating >= 6 & rating < 7){
            imageView.setImageResource(R.drawable.three_and_half_star);
        }else if(rating >= 5 & rating < 6){
            imageView.setImageResource(R.drawable.three_star);
        }else if(rating >= 4 & rating < 5){
            imageView.setImageResource(R.drawable.two_and_half_star);
        }else if(rating >= 3 & rating < 4){
            imageView.setImageResource(R.drawable.two_stars);
        }else if(rating >= 2 & rating < 3){
            imageView.setImageResource(R.drawable.one_and_half_star);
        }else if(rating >= 1 & rating < 2){
            imageView.setImageResource(R.drawable.one_star);
        }else if(rating > 0 & rating < 1){
            imageView.setImageResource(R.drawable.half_star);
        }else {
            textView.setText("0");
            imageView.setImageResource(R.drawable.zero_stars);
        }
    }
}
