package com.priteshjain.popularmovies.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.TabLayoutSupport;
import com.priteshjain.popularmovies.R;
import com.priteshjain.popularmovies.adapters.FragmentsAdapter;

public class MovieListManagerFragment extends Fragment {
    public static String TAG = "MovieListManagerFragment";

    private Context mContext;
    private RecyclerViewPager mRecyclerView;
    private FragmentsAdapter mAdapter;
    private View rootView;

    public MovieListManagerFragment()
    {
        // Required empty public constructor
    }

    public static MovieListManagerFragment getInstance()
    {
        Bundle args = new Bundle();
        MovieListManagerFragment movieListManagerFragment = new MovieListManagerFragment();
        return movieListManagerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie_list_manager, container, false);
        setToolbar();
        initViewPager();
        initTabLayout();
        return rootView;
    }

    private void initTabLayout() {
        //给TabLayout增加Tab, 并关联ViewPager
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        TabLayoutSupport.setupWithViewPager(tabLayout, mRecyclerView, mAdapter);
    }

    private void initViewPager() {
        mRecyclerView = (RecyclerViewPager) rootView.findViewById(R.id.movies_list_pager);
        LinearLayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerView.setLayoutManager(layout);
        mAdapter = new FragmentsAdapter(getChildFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
            }
        });
    }

    private void setToolbar()
    {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
    }
}
