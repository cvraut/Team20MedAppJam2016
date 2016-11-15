package com.uci.android101.team20medappjam2016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by codyx on 11/13/2016.
 */

public class ThreeStepsFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    public MyViewPager mViewPager;
    Button step1;
    Switch step2;
    SeekBar step3;
    private int steps;
    private List<Integer> order;

    public ThreeStepsFragment() {
        order = new ArrayList<Integer>();
    }

    public static ThreeStepsFragment newInstance(int sectionNumber, String steps) {
        ThreeStepsFragment fragment = new ThreeStepsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_QUESTION, steps);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.threestep_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.question_format, getArguments().getInt(ARG_SECTION_NUMBER), getArguments().getString(ARG_QUESTION)));
        mViewPager = ((MainActivity)getActivity()).getPager();
        step1 = (Button)rootView.findViewById(R.id.step_one);
        step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                steps++;
                order.add(1);
                if(steps == 3) {
                    mViewPager.setCurrentItem(getItem(+1), true);
                }
            }
        });
        step2 = (Switch)rootView.findViewById(R.id.step_two);
        step2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean switched) {
                if(switched) {
                    steps++;
                    order.add(2);
                    if(steps == 3) {
                        mViewPager.setCurrentItem(getItem(+1), true);
                    }
                }
            }
        });
        step3 = (SeekBar)rootView.findViewById(R.id.step_three);
        step3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 100) {
                    steps++;
                    order.add(3);
                    if(steps == 3) {
                        mViewPager.setCurrentItem(getItem(+1), true);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        return rootView;
    }
    private int getItem(int i){
        return mViewPager.getCurrentItem() + i;
    }
    // Backend method to check if user followed instructions correctly
    // Will implement later when i figure out to find out how lists are equivalent
    private boolean checkCorrect() {
        return true;
    }
}
