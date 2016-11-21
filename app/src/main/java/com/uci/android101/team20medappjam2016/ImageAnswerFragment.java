package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.io.OutputStreamWriter;
import android.util.Log;


/**
 * Created by codyx on 11/11/2016.
 */

public class ImageAnswerFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    private final String SCORE_FILE = "scorefile";
    public MyViewPager mViewPager;
    private int score = 5;
    private final int interval = 6000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run() {
            System.out.println("Help");
            mViewPager.setCurrentItem(4, true);
        }
    };

    public ImageAnswerFragment(){
    }

    public static ImageAnswerFragment newInstance(int sectionNumber, String question) {
        ImageAnswerFragment fragment = new ImageAnswerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.textquestion_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.question_label);
        textView.setText(getString(R.string.question_format, getArguments().getInt(ARG_SECTION_NUMBER), getArguments().getString(ARG_QUESTION)));
        mViewPager = ((MainActivity)getActivity()).getPager();

        System.out.println("Handler begins");
        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);
        System.out.println("Handler Finished");
        final EditText textField = (EditText) rootView.findViewById(R.id.answer_box);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                boolean answered = false;
                if (id == EditorInfo.IME_ACTION_DONE) {
                    String answer = textField.getText().toString();
                    answered = checkAnswer(answer);
                }
                if (answered){
                    ((MainActivity)getActivity()).appendScore(score);
                    mViewPager.setCurrentItem(getItem(+1), true);
                }
                else {
                    textView.setText("Try Again");
                    mViewPager.setCurrentItem(3, true);
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
        return rootView;
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    public boolean checkAnswer(String s) {
        s = s.toLowerCase();
        if (s.contains("dog") && s.contains("ball") && s.contains("pen")) {
            return true;
        } else {
            score = Math.max(0, score - 1);
            return false;
        }
    }
}
