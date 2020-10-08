package com.mvfollower.moviesfollower.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.mvfollower.moviesfollower.data.MainViewModel;
import com.mvfollower.moviesfollower.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {
    View root;
    ViewPager2 viewPager2;
    TabPagerAdapter tabPagerAdapter;
    TabLayout tabs;
    MainViewModel mainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movies_fragment_text, R.string.tv_fragment_text};
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabs = root.findViewById(R.id.tabs);
        tabPagerAdapter = new TabPagerAdapter(getActivity() );
        viewPager2 = root.findViewById(R.id.view_pager);

        viewPager2.setAdapter(tabPagerAdapter);
        new TabLayoutMediator(tabs, viewPager2,
                (tab, position) -> tab.setText(TAB_TITLES[position])
        ).attach();

        // Disable swipe
        viewPager2.setUserInputEnabled(false);
    }

}