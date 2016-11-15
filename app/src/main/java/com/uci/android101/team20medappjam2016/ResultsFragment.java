package com.uci.android101.team20medappjam2016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by codyx on 11/14/2016.
 */

public class ResultsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public MyViewPager mViewPager;
    Button goBack;

    public ResultsFragment() {}

    public static ResultsFragment newInstance(int i) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.results_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.results, getArguments().getInt(ARG_SECTION_NUMBER)));
        mViewPager = ((MainActivity)getActivity()).getPager();
        goBack = (Button)rootView.findViewById(R.id.button_results);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0, true);
            }
        });
        return rootView;
    }
}
