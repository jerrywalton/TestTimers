package com.smartcommunications.testtimers;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.smartcommunications.testtimers.databinding.ActivityMain2Binding;

import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMain2Binding binding;

    private FloatingActionButton button;

    private String activityName = "MainActivity2: ";

    private final MediaBrowserTimer timer = new MediaBrowserTimer(activityName);

    private Boolean hasInitialTimeout = false;

    private long initialTimeout = 0;

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        button = findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button clicked");
                finish();
            }
        });

        activityName = getLocalClassName() + ": ";

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        Intent i = getIntent();
        hasInitialTimeout = i.hasExtra(MediaBrowserTimer.INTENT_EXTRA_INITIAL_TIMEOUT);
        if (hasInitialTimeout) {
            initialTimeout = i.getLongExtra(MediaBrowserTimer.INTENT_EXTRA_INITIAL_TIMEOUT, 0);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(activityName  + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(activityName  + "onResume");
        // init the timer
        timer.initTimer(initialTimeout);
        myViewModel.test(activityName);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(activityName  + "onPause");
        // pause the timer
        timer.pauseTimer();
        myViewModel.test(activityName);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(activityName  + "onStop");
        //  stop timer
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(activityName  + "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(activityName  + "onDestroy");
    }

}