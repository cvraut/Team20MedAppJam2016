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
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;


/**
 * Created by chinmay on 11/14/2016.
 */

public class ImageFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public MyViewPager mViewPager;
    public boolean onStop = false;
    Button begin;
    /*private final int interval = 1000000; // 1 Second
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run() {
            System.out.println("Help");
            mViewPager.setCurrentItem(getItem(+1), true);
        }
    };*/
    public ImageFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ImageFragment newInstance(int sectionNumber) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.image_fragment, container, false);
        mViewPager = ((MainActivity) getActivity()).getPager();
        begin = (Button)rootView.findViewById(R.id.button_next);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(getItem(+1), true);
            }
        });
        
        return rootView;
    }

    private int getItem(int i){
        return mViewPager.getCurrentItem() + i;
    }
}
