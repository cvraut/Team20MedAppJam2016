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

/**
 * Created by codyx on 11/11/2016.
 */

public class ImageAnswerFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    private final String SCORE_FILE = "scorefile";
    public MyViewPager mViewPager;
    Button nextPage;
    private final int interval = 6000; // 1 Second
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        public void run() {
            System.out.println("Help");
            mViewPager.setCurrentItem(getItem(+1), true);
        }
    };

    public ImageAnswerFragment(){
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
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
        nextPage = (Button) rootView.findViewById(R.id.button);
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

    public void saveAnswer(String s) {
        int questionNum = getArguments().getInt(ARG_SECTION_NUMBER);
        FileOutputStream fos;
        DataOutputStream dos;
        int score = 0;
        switch (questionNum){
            case 1:
                // Default to Pacific Time Zone
                GregorianCalendar calender = new GregorianCalendar();
                Date time = new Date();
                calender.setTime(time);
                String [] datetime = s.split("\\s+");
                break;
            case 2:
                break;
            case 3:
                String [] items = s.split("\\s+");
                for(String item: items) {
                    if(item.equals("dog") || item.equals("ball") || item.equals("pen") ) {
                        score++;
                    }
                }
                break;
            case 4:
                String [] ints = s.split("\\s+");
                int[] results = new int[ints.length];
                for (int i = 0; i < ints.length; i++) {
                    try {
                        results[i] = Integer.parseInt(ints[i]);
                    } catch (NumberFormatException nfe) {
                        continue;
                    };
                }
                for(int i = 1; i < results.length; i++) {
                    if(results[i] - results[i-1] == 7) {
                        score++;
                    }
                }
                break;
            case 5:
                String [] items2 = s.split("\\s+");
                for(String item: items2) {
                    if(item.equals("dog") || item.equals("ball") || item.equals("pen") ) {
                        score++;
                    }
                }
                break;
            case 7:
                List<String> input = Arrays.asList(s.split("\\s+"));
                List<String> phrase = Arrays.asList("No", "if's", "ands", "or", "buts");
                if(input.equals(phrase)) {
                    score++;
                }
                break;
            case 10:
                if(s.equals("yes") || s.equals("YES")) {
                    score++;
                }
                break;
        }
        try {
            fos = ((MainActivity) getActivity()).openFileOutput(SCORE_FILE, Context.MODE_PRIVATE);
            dos = new DataOutputStream(fos);
            dos.write(score);
            dos.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
