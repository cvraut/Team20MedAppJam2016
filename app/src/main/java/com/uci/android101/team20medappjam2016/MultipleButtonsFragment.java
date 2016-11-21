package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by codyx on 11/19/2016.
 */

public class MultipleButtonsFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    private final String SCORE_FILE = "scorefile";
    public MyViewPager mViewPager;
    Button buttonOne, buttonTwo, buttonThree, buttonFour;

    public MultipleButtonsFragment(){
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MultipleButtonsFragment newInstance(int sectionNumber, String question) {
        MultipleButtonsFragment fragment = new MultipleButtonsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.multibutton_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.title_label);
        textView.setText(getString(R.string.question_format, getArguments().getInt(ARG_SECTION_NUMBER), getArguments().getString(ARG_QUESTION)));
        mViewPager = ((MainActivity)getActivity()).getPager();
        buttonOne = (Button) rootView.findViewById(R.id.button_one);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(getItem(+1), true);
            }
        });
        buttonTwo = (Button) rootView.findViewById(R.id.button_two);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(getItem(+1), true);
            }
        });
        buttonThree = (Button) rootView.findViewById(R.id.button_three);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean rightButton = true;
                //saveAnswer(rightButton);
                mViewPager.setCurrentItem(getItem(+1), true);
            }
        });
        buttonFour = (Button) rootView.findViewById(R.id.button_four);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(getItem(+1), true);
            }
        });
        return rootView;
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void saveAnswer(boolean right) {
        if(right) {
            ((MainActivity)getActivity()).appendScore(1);
        }
    }

}
