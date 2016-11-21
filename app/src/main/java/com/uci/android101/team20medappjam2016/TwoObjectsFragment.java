package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by codyx on 11/19/2016.
 */

public class TwoObjectsFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    public String answer;
    public MyViewPager mViewPager;
    Button nextPage;

    public TwoObjectsFragment(){
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TwoObjectsFragment newInstance(int sectionNumber, String question) {
        TwoObjectsFragment fragment = new TwoObjectsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.twoobjects_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.question_label);
        textView.setText(getString(R.string.question_format, getArguments().getInt(ARG_SECTION_NUMBER), getArguments().getString(ARG_QUESTION)));
        mViewPager = ((MainActivity)getActivity()).getPager();
        final EditText textField = (EditText) rootView.findViewById(R.id.answer_box);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                boolean answered = false;
                if (id == EditorInfo.IME_ACTION_DONE) {
                    answer = textField.getText().toString();
                    answered = true;
                    ((MainActivity)getActivity()).hideKeyboard(rootView);
                }
                return answered;
            }
        });
        textField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    ((MainActivity)getActivity()).hideKeyboard(rootView);
                }
            }
        });
        nextPage = (Button) rootView.findViewById(R.id.button_next);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAnswer(answer);
                mViewPager.setCurrentItem(getItem(+1), true);
            }
        });
        return rootView;
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void saveAnswer(String s) {
        int score = 0;
        if(s == null) {
            s = "";
        }
        s = s.toLowerCase();
        if(s.contains("cup")) {
            score++;
        }
        if(s.contains("watch")) {
            score++;
        }
        ((MainActivity)getActivity()).appendScore(score);
    }
}
