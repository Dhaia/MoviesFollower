package com.mvfollower.moviesfollower.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabPagerAdapter extends FragmentStateAdapter {
    List<Fragment> fragmentList = new ArrayList<>(Arrays.asList(new MoviesFragment(), new TvFragment()));

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
