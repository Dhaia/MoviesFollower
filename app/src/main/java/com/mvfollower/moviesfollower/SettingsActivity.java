package com.mvfollower.moviesfollower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mvfollower.moviesfollower.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Settings);
        setTitle("Filter Tool");
        setContentView(R.layout.activity_settings);
    }
}