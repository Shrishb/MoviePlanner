package com.movieplanner.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.movieplanner.MainActivity;
import com.movieplanner.NewMainActivity;
import com.movieplanner.R;

public class DisplaySplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //use thread to display image for specified time
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    //decalre next activity to be used
                    Intent intent = new Intent(DisplaySplashScreen.this, NewMainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    // override the onPause method to move to next activity
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
