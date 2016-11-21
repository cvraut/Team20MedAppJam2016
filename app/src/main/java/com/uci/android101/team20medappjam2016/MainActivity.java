package com.uci.android101.team20medappjam2016;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private static final int MY_PERMISSIONS_ACCESS_LOCATION = 1;
    public com.uci.android101.team20medappjam2016.SectionsPagerAdapter mSectionsPagerAdapter;
    public boolean locations;
    public int totalScore;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private MyViewPager mViewPager;

    public List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        for(int i = 0; i < 15; i++) {
            switch(i) {
                case 0:
                    fList.add(TitleFragment.newInstance());
                    break;
                case 1:
                    fList.add(TextQuestionFragment.newInstance(i, "What is the current Year? Month? Season? Date? Day of the week?"));
                    break;
                case 2:
                    if(locations) {
                        fList.add(TextQuestionFragment.newInstance(i, "What Country are we in? State? County? City? Zip Code?"));
                    }
                    else {
                        fList.add(TextQuestionFragment.newInstance(i, "This question is unavailable without location permissions. " +
                                "Please move on."));
                    }
                    break;
                case 3:
                    fList.add(ImageFragment.newInstance(i));
                    break;
                case 4:
                    fList.add(ImageAnswerFragment.newInstance(i, "What were  the three images? This question will " +
                            "repeat until all three are recalled correctly"));
                    break;
                case 5:
                    fList.add(TextQuestionFragment.newInstance(i, "Count backwards by 7's from 100 (i.e. 22 15 8). " +
                            "Please enter the numbers separated by spaces only like the example."));
                    break;
                case 6:
                    fList.add(TextQuestionFragment.newInstance(i, "Earlier we showed three items on-screen. What were those items?"));
                    break;
                case 7:
                    fList.add(TwoObjectsFragment.newInstance(i, "Please name the two objects shown here."));
                    break;
                case 8:
                    fList.add(TextQuestionFragment.newInstance(i, "Tell the examinee to repeat the phrase: " +
                            "No ifs, ands, or buts, and enter their first response."));
                    break;
                case 9:
                    fList.add(ThreeStepsFragment.newInstance(i, "Do these steps in order: Press the button, " +
                            "flip the switch, then slide the slider all the way."));
                    break;
                case 10:
                    fList.add(MultipleButtonsFragment.newInstance(i, "Press the red button."));
                    break;
                case 11:
                    fList.add(TextQuestionFragment.newInstance(i, "Ask the examinee to make up any sentence. If the sentence " +
                            "makes sense, enter 'yes', otherwise, enter 'no'."));
                    break;
                case 12:
                    fList.add(TimeDrawFragment.newInstance(i, "What is the time indicated by the clock? Please enter the" +
                            "time like this: 8:15"));
                    break;
                case 13:
                    fList.add(ResultsFragment.newInstance(i));
                    break;
                case 14:
                    fList.add(AboutFragment.newInstance(i));
                    break;
            }
        }
        return fList;
    }


    public MyViewPager getPager() {
        return mViewPager;
    }
    public boolean getPermission() {
        return locations;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locations = true;
                }
                else {
                    locations = false;
                }
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_ACCESS_LOCATION);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<Fragment> flist = getFragments();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new com.uci.android101.team20medappjam2016.SectionsPagerAdapter(getSupportFragmentManager(), flist);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (MyViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void appendScore(int i) {
        totalScore+=i;
        System.out.println(totalScore);
    }
    public int getScore() {
        return totalScore;
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */

    /**
     * A placeholder fragment containing a simple view.
     */
    /*
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        public MyViewPager mViewPager;
        Button nextPage;

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            nextPage = (Button) rootView.findViewById(R.id.button);
            nextPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).getPager().setCurrentItem(+1, true);
                }
            });
            return rootView;
        }
    }
    */
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    /*
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0){
                return TitleFragment.newInstance();
            }
            else{
                return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 14 total pages.
            return 14;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position<=14 && 0<=position) {
                    return "SECTION "+Integer.toString(position+1);
            }
            return null;
        }

    }
    */
}
