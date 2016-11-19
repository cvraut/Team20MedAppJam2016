package com.uci.android101.team20medappjam2016;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by codyx on 11/18/2016.
 */

public class AboutFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public MyViewPager mViewPager;
    Button back;
    //private static final String ARG_ABOUT_INFO = "about";
    public AboutFragment() {}

    public static AboutFragment newInstance(int sectionNumber) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.about_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.about_info);
        textView.setText(R.string.about);
        mViewPager = ((MainActivity) getActivity()).getPager();
        back = (Button) rootView.findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0, true);
            }
        });
        return rootView;
    }
}
