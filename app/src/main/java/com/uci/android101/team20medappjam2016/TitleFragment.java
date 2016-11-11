package com.uci.android101.team20medappjam2016;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

/**
 * Created by chinmay on 11/10/2016.
 */

public class TitleFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TitleFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TitleFragment newInstance() {
        TitleFragment fragment = new TitleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 0);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.title_label);
        textView.setText(R.string.title_format);
        return rootView;
    }
}