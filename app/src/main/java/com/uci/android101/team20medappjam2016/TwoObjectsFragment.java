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
    private final String SCORE_FILE = "scorefile";
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
        final View rootView = inflater.inflate(R.layout.textquestion_fragment, container, false);
        final EditText textField = (EditText) rootView.findViewById(R.id.answer_box);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                boolean answered = false;
                if (id == EditorInfo.IME_ACTION_DONE) {
                    String answer = textField.getText().toString();
                    //saveAnswer(answer);
                    answered = true;
                    mViewPager.setCurrentItem(getItem(+1), true);
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
        mViewPager = ((MainActivity) getActivity()).getPager();
        return rootView;
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void saveAnswer(String s) {
        String [] objects  = s.split("\\s+");
        int score = 0;
        FileOutputStream fos;
        DataOutputStream dos;
        for(String object: objects) {
            if(object.toLowerCase().equals("cup") || object.toLowerCase().equals("watch")){
                score++;
            }
        }
        try {
            fos = ((MainActivity) getActivity()).openFileOutput(SCORE_FILE, Context.MODE_PRIVATE);
            dos = new DataOutputStream(fos);
            dos.write(score);
            dos.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
