package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import android.util.Log;

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
        int finalResult = ((MainActivity) getActivity()).getScore();
        textView.setText(getString(R.string.results, finalResult));
        mViewPager = ((MainActivity) getActivity()).getPager();
        goBack = (Button) rootView.findViewById(R.id.button_results);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0, true);
            }
        });
        TextView resultView = (TextView) rootView.findViewById(R.id.results_label);
        if(((MainActivity)getActivity()).getPermission()) {
            if (finalResult < 18) {
                resultView.setText(getString(R.string.result_severe));
            } else if (finalResult < 24 && finalResult >= 18) {
                resultView.setText(getString(R.string.result_mild));
            } else {
                resultView.setText(getString(R.string.result_none));
            }
        }
        else {
            if (finalResult < 13) {
                resultView.setText(getString(R.string.result_severe));
            } else if (finalResult < 19 && finalResult >= 13) {
                resultView.setText(getString(R.string.result_mild));
            } else {
                resultView.setText(getString(R.string.result_none));
            }
        }
        return rootView;
    }
}
