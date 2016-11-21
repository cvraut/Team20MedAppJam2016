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
        textView.setText(getString(R.string.results, getScore()));
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

    public int getScore(){
        int score = 0;
        /*
        FileInputStream fis;
        try {
            fis = ((MainActivity) getActivity()).openFileInput("scorefile");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String ret = "";
            String line = null;
            while ((line = reader.readLine()) != null) {
                ret = line;
            }
            reader.close();

            String[] scores = ret.split("\\s+");
            System.out.println("This is what is in the file...\n"+ret);
            /*for(String num: scores){
                score+=Integer.parseInt(num);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/

        String ret = "";

        try {
            InputStream inputStream = ((MainActivity) getActivity()).openFileInput("scorefile");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                System.out.println(ret);

                String[] scores = ret.split("\\s+");
                for(String num: scores){
                    score+=Integer.parseInt(num);
                }
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return score;
    }
}
