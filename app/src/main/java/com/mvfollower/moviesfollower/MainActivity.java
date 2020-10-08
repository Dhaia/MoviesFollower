package com.mvfollower.moviesfollower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;

import com.mvfollower.moviesfollower.ui.FavoritesFragment;
import com.mvfollower.moviesfollower.ui.HomeFragment;
import com.mvfollower.moviesfollower.ui.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    FavoritesFragment favoritesFragment = new FavoritesFragment();
    SearchFragment searchFragment = new SearchFragment();
    Bundle savedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar, null));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.main_navBar, null));

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_header_container);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setOnNavigationItemReselectedListener(reselectedListener);

        // Show the Home Fragment by default when the app launches at first
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,  homeFragment).commit();
        }else {
            savedState = savedInstanceState;
            favoritesFragment = (FavoritesFragment) getSupportFragmentManager().getFragment(savedInstanceState, "Favorites Fragment");
            homeFragment = (HomeFragment) getSupportFragmentManager().getFragment(savedInstanceState, "Home Fragment");
            searchFragment = (SearchFragment) getSupportFragmentManager().getFragment(savedInstanceState, "Search Fragment");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getSupportFragmentManager().putFragment(outState, "Home Fragment", homeFragment);
        getSupportFragmentManager().putFragment(outState, "Favorites Fragment", favoritesFragment);
        getSupportFragmentManager().putFragment(outState, "Search Fragment", searchFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home_menu:
                    if(homeFragment == null){
                        selectedFragment = HomeFragment.newInstance();
                    }else {
                        selectedFragment = homeFragment;
                    }
                    break;
                case R.id.favorites_menu:
                    if(favoritesFragment == null){
                        selectedFragment = FavoritesFragment.newInstance();
                    }else {
                        selectedFragment = favoritesFragment;
                    }
                    break;
                case R.id.discover_menu:
                    if(searchFragment == null){
                        selectedFragment = SearchFragment.newInstance();
                    }else {
                        selectedFragment = searchFragment;
                    }
                    break;
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, selectedFragment).commit();
            return true;
        }
    };
    private BottomNavigationView.OnNavigationItemReselectedListener reselectedListener = item -> {
        switch (item.getItemId()){
            case R.id.home_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, new HomeFragment()).commit();
                break;
            case R.id.favorites_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, new FavoritesFragment()).commit();
                break;
            case R.id.discover_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag, new SearchFragment()).commit();
                break;
        }
    };

    public void actionClicked(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setAction("action");
        startActivity(intent);
    }
    public void animationClicked(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setAction("animation");
        startActivity(intent);
    }
    public void horrorClicked(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setAction("horror");
        startActivity(intent);
    }
    public void comedyClicked(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setAction("comedy");
        startActivity(intent);
    }public void searchClicked(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setAction("search");
        startActivity(intent);
    }
    public void filterToolClicked(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setAction("filter");
        startActivity(intent);
    }
}
