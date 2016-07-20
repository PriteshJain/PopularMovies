package com.priteshjain.popularmovies.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lsjwzh.widget.recyclerviewpager.FragmentStatePagerAdapter;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;
import com.priteshjain.popularmovies.views.MovieListFragment;

import java.util.LinkedHashMap;

import static com.priteshjain.popularmovies.constants.Constant.MovieListType;

public class FragmentsAdapter extends FragmentStatePagerAdapter implements TabLayoutSupport.ViewPagerTabLayoutAdapter {
    private final LinkedHashMap<Integer, Fragment> mFragmentCache = new LinkedHashMap<>();


    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position, Fragment.SavedState savedState) {
        Fragment f = mFragmentCache.containsKey(position) ? mFragmentCache.get(position)
                : MovieListFragment.getInstance(MovieListType.values()[position]);
        if (savedState != null && !mFragmentCache.containsKey(position)) {
            f.setInitialSavedState(savedState);
        }
        mFragmentCache.put(position, f);
        return f;
    }

    @Override
    public void onDestroyItem(int position, Fragment fragment) {
        // onDestroyItem
        mFragmentCache.remove(position);
    }

    @Override
    public String getPageTitle(int position) {
        return MovieListType.values()[position].getType();
    }

    @Override
    public int getItemCount() {
        return MovieListType.values().length;
    }
}