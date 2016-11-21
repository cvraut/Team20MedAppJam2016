package com.uci.android101.team20medappjam2016;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by codyx on 11/11/2016.
 */

public class TextQuestionFragment extends Fragment implements LocationListener{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_QUESTION = "section_question";
    private final String SCORE_FILE = "scorefile";
    public MyViewPager mViewPager;
    public String answer, country, city, county, state, zipcode;
    Button nextPage;

    public TextQuestionFragment(){
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TextQuestionFragment newInstance(int sectionNumber, String question) {
        TextQuestionFragment fragment = new TextQuestionFragment();
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
        final EditText textField = (EditText) rootView.findViewById(R.id.answer_box);
        textField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                boolean answered = false;
                if (id == EditorInfo.IME_ACTION_DONE) {
                    answer = textField.getText().toString();
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
                    saveAnswer(answer);
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
                GregorianCalendar calendar = new GregorianCalendar();
                Date time = new Date();
                calendar.setTime(time);
                String [] datetime = s.split("\\s+");
                String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
                String currentDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                String currentMonth, currentSeason, currentDay;
                currentMonth = currentSeason = currentDay = "";
                switch (calendar.get(Calendar.MONTH)) {
                    case Calendar.JANUARY:
                        currentMonth = "JANUARY";
                        currentSeason = "WINTER";
                        break;
                    case Calendar.FEBRUARY:
                        currentMonth = "FEBRUARY";
                        currentSeason = "WINTER";
                        break;
                    case Calendar.MARCH:
                        currentMonth = "MARCH";
                        currentSeason = "SPRING";
                        break;
                    case Calendar.APRIL:
                        currentMonth = "APRIL";
                        currentSeason = "SPRING";
                        break;
                    case Calendar.MAY:
                        currentMonth = "MAY";
                        currentSeason = "SPRING";
                        break;
                    case Calendar.JUNE:
                        currentMonth = "JUNE";
                        currentSeason = "SUMMER";
                        break;
                    case Calendar.JULY:
                        currentMonth = "JULY";
                        currentSeason = "SUMMER";
                        break;
                    case Calendar.AUGUST:
                        currentMonth = "AUGUST";
                        currentSeason = "SUMMER";
                        break;
                    case Calendar.SEPTEMBER:
                        currentMonth = "SEPTEMBER";
                        currentSeason = "FALL";
                        break;
                    case Calendar.OCTOBER:
                        currentMonth = "OCTOBER";
                        currentSeason = "FALL";
                        break;
                    case Calendar.NOVEMBER:
                        currentMonth = "NOVEMBER";
                        currentSeason = "FALL";
                        break;
                    case Calendar.DECEMBER:
                        currentMonth = "DECEMBER";
                        currentSeason = "WINTER";
                        break;
                }
                switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                    case Calendar.MONDAY:
                        currentDay = "MONDAY";
                        break;
                    case Calendar.TUESDAY:
                        currentDay = "TUESDAY";
                        break;
                    case Calendar.WEDNESDAY:
                        currentDay = "WEDNESDAY";
                        break;
                    case Calendar.THURSDAY:
                        currentDay = "THURSDAY";
                        break;
                    case Calendar.FRIDAY:
                        currentDay = "FRIDAY";
                        break;
                    case Calendar.SATURDAY:
                        currentDay = "SATURDAY";
                        break;
                    case Calendar.SUNDAY:
                        currentDay = "SUNDAY";
                        break;

                }
                if(s.contains(currentYear)) {
                    score++;
                }
                if(s.contains(currentDate)) {
                    score++;
                }
                if(s.toUpperCase().contains(currentDay)) {
                    score++;
                }
                if(s.toUpperCase().contains(currentMonth)) {
                    score++;
                }
                if(s.toUpperCase().contains(currentSeason)) {
                    score++;
                }
                break;
            case 2:
                LocationManager mLocationManager = (LocationManager) ((MainActivity)getActivity()).getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = mLocationManager.getBestProvider(criteria, false);
                Location location = null;
                try {
                    location = mLocationManager.getLastKnownLocation(provider);
                }
                catch (SecurityException e) {
                    e.printStackTrace();
                }
                if(location != null) {
                    onLocationChanged(location);
                    if(country != null && s.contains(country)) {
                        score++;
                    }
                    if(state != null && s.contains(state)) {
                        score++;
                    }
                    if(county != null && s.contains(county)) {
                        score++;
                    }
                    if(city != null && s.contains(city)) {
                        score++;
                    }
                    if(zipcode != null && s.contains(zipcode)) {
                        score++;
                    }
                }
                break;
            case 5:
                String [] ints = s.split("\\s+");
                int[] results = new int[ints.length+1];
                results[0] = 100;
                for (int i = 0; i < ints.length; i++) {
                    try {
                        results[i+1] = Integer.parseInt(ints[i]);
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                    };
                }
                for(int i = 1; i < results.length; i++) {
                    if(results[i-1] - results[i] == 7) {
                        score++;
                    }
                }
                break;
            case 6:
                if(s.toLowerCase().contains("dog")) {
                    score++;
                }
                if(s.toLowerCase().contains("pen")) {
                    score++;
                }
                if(s.toLowerCase().contains("ball")) {
                    score++;
                }
                break;
            case 8:
                if(s.toLowerCase().contains("no") && s.toLowerCase().contains("if's") && s.toLowerCase().contains("ands") &&
                    s.toLowerCase().contains("or") && s.toLowerCase().contains("buts")) {
                    score++;
                }
                break;
            case 11:
                if(s.toLowerCase().equals("yes")) {
                    score++;
                }
                break;
        }
        ((MainActivity) getActivity()).appendScore(score);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Geocoder geoCoder = new Geocoder((MainActivity)getActivity(), Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
            country = address.get(0).getCountryName();
            county = address.get(0).getSubAdminArea();
            city = address.get(0).getLocality();
            state = address.get(0).getAdminArea();
            zipcode = address.get(0).getPostalCode();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}
    @Override
    public void onProviderEnabled(String s) {}
    @Override
    public void onProviderDisabled(String s) {}
}
