package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.app.widgets.DrawingView;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by codyx on 11/16/2016.
 */

public class TimeDrawFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    public MyViewPager mViewPager;
    Button nextPage, clear;

    public TimeDrawFragment() {}

    public static TimeDrawFragment newInstance(int sectionNumber, String question) {
        TimeDrawFragment fragment = new TimeDrawFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.drawtime_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.title_label);
        textView.setText(getString(R.string.question_format, getArguments().getInt(ARG_SECTION_NUMBER), getArguments().getString(ARG_QUESTION)));
        mViewPager = ((MainActivity) getActivity()).getPager();
        /*
        drawView = (DrawingView) rootView.findViewById(R.id.draw_time);
        drawHour = (DrawingView) rootView.findViewById(R.id.draw_hour);
        drawMinute = (DrawingView) rootView.findViewById(R.id.draw_minute);
        clear = (Button)rootView.findViewById(R.id.button_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.clearCanvas();
            }
        });
        */
        final EditText textField = (EditText) rootView.findViewById(R.id.answer_box);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                boolean answered = false;
                if (id == EditorInfo.IME_ACTION_DONE) {
                    String answer = textField.getText().toString();
                    saveAnswer(answer);
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
        nextPage = (Button)rootView.findViewById(R.id.button_next);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(getItem(+1), true);
            }
        });
        return rootView;
    }
    public void saveAnswer(String s) {
        int score = 0;
        if(s.contains("6:15")) {
            score++;
        }
        ((MainActivity) getActivity()).appendScore(score);
    }
    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }
}
