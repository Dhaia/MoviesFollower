package com.mvfollower.moviesfollower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.mvfollower.moviesfollower.ui.SearchMoviesFragment;
import com.mvfollower.moviesfollower.ui.SearchTabAdapter;
import com.mvfollower.moviesfollower.ui.SearchTvFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String ACTION_ACTION = "action";
    private String ACTION_HORROR = "horror";
    private String ACTION_ANIMATION = "animation";
    private String ACTION_COMEDY = "comedy";
    private String ACTION_FILTER = "filter";

    private String ACTION_SEARCH = "search";

    ImageView search_filter_icon;
    EditText editText;
    TextView title;
    Toolbar toolbar;
    ViewPager2 viewPager2;
    SearchTabAdapter searchTabAdapter;
    TabLayout tabLayout;
    private static final int[] TAB_TITLES = new int[]{R.string.movies_fragment_text, R.string.tv_fragment_text};
    List<Fragment> fragmentList = new ArrayList<>();
    boolean detectQuery = false;
    String sortBy = "";
    String genreIds = "";
    String year = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.main_navBar));

        search_filter_icon = findViewById(R.id.search_filter_icon);
        editText = findViewById(R.id.search_result_editText);
        title = findViewById(R.id.search_result_title);
        toolbar = findViewById(R.id.search_toolbar);

        SearchMoviesFragment searchMoviesFragment = new SearchMoviesFragment();
        SearchTvFragment searchTvFragment = new SearchTvFragment();

        Intent intent = getIntent();
        if(intent.getAction().equals(ACTION_ACTION)){
            searchMoviesFragment.setAction(ACTION_ACTION);
            fragmentList.add(searchMoviesFragment);
            searchTvFragment.setAction(ACTION_ACTION);
            fragmentList.add(searchTvFragment);
            title.setText("Action");
            editText.setVisibility(View.GONE);
            search_filter_icon.setVisibility(View.GONE);
        }else if(intent.getAction().equals(ACTION_ANIMATION)){
            searchMoviesFragment.setAction(ACTION_ANIMATION);
            fragmentList.add(searchMoviesFragment);
            searchTvFragment.setAction(ACTION_ANIMATION);
            fragmentList.add(searchTvFragment);
            title.setText("Animation");
            editText.setVisibility(View.GONE);
            search_filter_icon.setVisibility(View.GONE);
        }else if(intent.getAction().equals(ACTION_HORROR)){
            searchMoviesFragment.setAction(ACTION_HORROR);
            fragmentList.add(searchMoviesFragment);
            searchTvFragment.setAction(ACTION_HORROR);
            fragmentList.add(searchTvFragment);
            title.setText("Horror");
            editText.setVisibility(View.GONE);
            search_filter_icon.setVisibility(View.GONE);
        }else  if(intent.getAction().equals(ACTION_COMEDY)){
            searchMoviesFragment.setAction(ACTION_COMEDY);
            fragmentList.add(searchMoviesFragment);
            searchTvFragment.setAction(ACTION_COMEDY);
            fragmentList.add(searchTvFragment);
            title.setText("Comedy");
            editText.setVisibility(View.GONE);
            search_filter_icon.setVisibility(View.GONE);
        }else if(intent.getAction().equals(ACTION_SEARCH)){
            searchMoviesFragment.setAction(ACTION_SEARCH);
            searchTvFragment.setAction(ACTION_SEARCH);
            fragmentList.add(searchMoviesFragment);
            fragmentList.add(searchTvFragment);
            title.setVisibility(View.GONE);
            search_filter_icon.setVisibility(View.GONE);
            detectQuery = true;
        }else if(intent.getAction().equals(ACTION_FILTER)){
            searchMoviesFragment.setAction(ACTION_FILTER);
            searchTvFragment.setAction(ACTION_FILTER);
            searchMoviesFragment.setQueries("", sortBy, genreIds, year);
            fragmentList.add(searchMoviesFragment);
            fragmentList.add(searchTvFragment);
            editText.setVisibility(View.GONE);
            title.setText("Discover");
        }

        if(detectQuery){
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    List<Fragment> fragmentList2 = new ArrayList<>();

                    String query = editText.getText().toString();
                    SearchMoviesFragment searchMoviesFragment1 = new SearchMoviesFragment();
                    SearchTvFragment searchTvFragment1 = new SearchTvFragment();
                    searchMoviesFragment1.setQueries(query, "", "", "");
                    searchTvFragment1.setQueries(query, "", "", "");
                    searchMoviesFragment1.setAction(ACTION_SEARCH);
                    searchTvFragment1.setAction(ACTION_SEARCH);

                    fragmentList2.add(searchMoviesFragment1);
                    fragmentList2.add(searchTvFragment1);
                    searchTabAdapter = new SearchTabAdapter(SearchActivity.this, fragmentList2);
                    viewPager2.setAdapter(searchTabAdapter);
                    new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(TAB_TITLES[position]))
                            .attach();
                }

            @Override
            public void afterTextChanged(Editable s) { }
        });}

        tabLayout = findViewById(R.id.search_tabs);
        viewPager2 = findViewById(R.id.search_viewPager);
        searchTabAdapter = new SearchTabAdapter(this, fragmentList);
        viewPager2.setAdapter(searchTabAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(TAB_TITLES[position]))
                .attach();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setupSharedPreferences(sharedPreferences, intent);

        Toolbar toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            @SuppressLint("PrivateResource") final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
            assert upArrow != null;
            upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void filterClicked(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void setupSharedPreferences(SharedPreferences sharedPreferences, Intent intent){
        sortBy = sharedPreferences.getString(getString(R.string.sortByListKey), null);

        Set<String> stringSet = sharedPreferences.getStringSet(getString(R.string.genresKey), null);
        StringBuilder genre_ids = new StringBuilder();
        if (stringSet != null){
            for (String string : stringSet){
                genre_ids.append(string).append(",");
            }
            genreIds = genre_ids.toString();
            if(genreIds.length() >= 1){
                genreIds = genreIds.substring(0, genreIds.length() - 1);
            }
        }
        year = sharedPreferences.getString(getString(R.string.yearSharedPrefKey), null);

        if (intent.getAction().equals(ACTION_FILTER)){
            List<Fragment> fragmentList2 = new ArrayList<>();
            SearchMoviesFragment searchMoviesFragment1 = new SearchMoviesFragment();
            SearchTvFragment searchTvFragment1 = new SearchTvFragment();
            searchMoviesFragment1.setQueries("", sortBy, genreIds, year);
            searchTvFragment1.setQueries("", sortBy, genreIds, year);
            searchMoviesFragment1.setAction(ACTION_FILTER);
            searchTvFragment1.setAction(ACTION_FILTER);

            fragmentList2.add(searchMoviesFragment1);
            fragmentList2.add(searchTvFragment1);
            searchTabAdapter = new SearchTabAdapter(SearchActivity.this, fragmentList2);
            viewPager2.setAdapter(searchTabAdapter);
            new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(TAB_TITLES[position]))
                    .attach();
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        StringBuilder genre_ids = new StringBuilder();

        if(key.equals(getString(R.string.sortByListKey))){
            sortBy = sharedPreferences.getString(getString(R.string.sortByListKey), null);
        }else if(key.equals(getString(R.string.genresKey))){
            Set<String> stringSet = sharedPreferences.getStringSet(getString(R.string.genresKey), null);
            assert stringSet != null;
            for (String string : stringSet){
                genre_ids.append(string).append(",");
            }
            genreIds = genre_ids.toString();
            if(genreIds.length() >= 1){
                genreIds = genreIds.substring(0, genreIds.length() - 1);
            }
        }else {
            year = sharedPreferences.getString(getString(R.string.yearSharedPrefKey), null);
        }

        List<Fragment> fragmentList2 = new ArrayList<>();
        SearchMoviesFragment searchMoviesFragment1 = new SearchMoviesFragment();
        SearchTvFragment searchTvFragment1 = new SearchTvFragment();
        searchMoviesFragment1.setQueries("", sortBy, genreIds, year);
        searchTvFragment1.setQueries("", sortBy, genreIds, year);
        searchMoviesFragment1.setAction(ACTION_FILTER);
        searchTvFragment1.setAction(ACTION_FILTER);

        fragmentList2.add(searchMoviesFragment1);
        fragmentList2.add(searchTvFragment1);
        searchTabAdapter = new SearchTabAdapter(SearchActivity.this, fragmentList2);
        viewPager2.setAdapter(searchTabAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(TAB_TITLES[position]))
                .attach();
    }

    /**
     * Unregister the shared Preference Change Listener to avoid any memory leaks
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}