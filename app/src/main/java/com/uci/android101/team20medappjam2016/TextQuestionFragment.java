package com.uci.android101.team20medappjam2016;

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

/**
 * Created by codyx on 11/11/2016.
 */

public class TextQuestionFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public MyViewPager mViewPager;
    Button nextPage;

    public TextQuestionFragment(){
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TextQuestionFragment newInstance(int sectionNumber) {
        TextQuestionFragment fragment = new TextQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.textquestion_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        mViewPager = ((MainActivity)getActivity()).getPager();
        EditText textField = (EditText) rootView.findViewById(R.id.answer_box);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                boolean answered = false;
                if (id == EditorInfo.IME_ACTION_SEND) {
                    //saveMessageForAnswer();
                    answered = true;
                }
                return answered;
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
}
