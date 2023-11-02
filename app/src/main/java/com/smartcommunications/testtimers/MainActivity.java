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

import com.smartcommunications.testtimers.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private FloatingActionButton nextButton;

    private String activityName = "MainActivity: ";

    private final MediaBrowserTimer timer = new MediaBrowserTimer(activityName);

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        nextButton = findViewById(R.id.fab);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button clicked");
                startActivity();
            }
        });

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

    }

    private void startActivity() {
        Intent i = new Intent(this, MainActivity2.class)
                .putExtra(MediaBrowserTimer.INTENT_EXTRA_INITIAL_TIMEOUT, timer.getInitialTimeout());
        startActivity(i);
    }

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
        timer.initTimer(0);
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