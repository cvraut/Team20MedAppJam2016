package com.uci.android101.team20medappjam2016;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ViewSwitcher.ViewFactory;

import android.view.animation.AnimationUtils;

import android.widget.ImageView;
import android.widget.ImageSwitcher;

import android.os.Handler;

public class ImageSwitcherExampleActivity extends Activity {

    private Integer images[] = {R.drawable.ball, R.drawable.doge, R.drawable.pen,R.drawable.harambe};
    private int currImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_fragment);

        initializeImageSwitcher();
        setInitialImage();
        setImageRotateListener();
    }

    private void initializeImageSwitcher() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(ImageSwitcherExampleActivity.this);
                return imageView;
            }
        });

        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
    }


    public void switchImage(){
        currImage++;
        if (currImage == images.length) {
            currImage = 0;
        }
        setCurrentImage();
    }

    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            try{
                switchImage();
            }
            catch (Exception e) {
                currImage = 4;
            }
            finally{
                //also call the same runnable to call it at regular interval
                handler.postDelayed(this, 1000);
            }
        }
    };


    private void setImageRotateListener() {
        final Button rotatebutton = (Button) findViewById(R.id.btnRotateImage);
        rotatebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currImage++;
                if (currImage == 3) {
                    currImage = 0;
                }
                setCurrentImage();
            }
        });
    }

    private void setInitialImage() {
        setCurrentImage();
    }

    private void setCurrentImage() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setImageResource(images[currImage]);
    }

}

