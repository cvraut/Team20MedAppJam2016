package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.app.widgets.DrawingView;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by codyx on 11/16/2016.
 */

public class TimeDrawFragment extends Fragment{
    private ArrayList<Path> pointsToDraw = new ArrayList<Path>();
    private Paint mPaint;
    Path path;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    public MyViewPager mViewPager;
    public DrawingView drawView1;
    Button nextPage;

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
        final View rootView = inflater.inflate(R.layout.textquestion_fragment, container, false);
        drawView1 = (DrawingView) rootView.findViewById(R.id.draw_time);
        nextPage = (Button)rootView.findViewById(R.id.step_one);
        nextPage.setOnClickListener(new View.OnClickListener() {
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
