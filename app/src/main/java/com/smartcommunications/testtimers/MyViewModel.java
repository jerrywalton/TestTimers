package com.smartcommunications.testtimers;

import androidx.lifecycle.ViewModel;

import java.util.Timer;

public class MyViewModel extends ViewModel {

    private MediaBrowserTimer mediaBrowserTimer;

    private String name = "MyViewModel: ";

    public MyViewModel() {
        super();
        mediaBrowserTimer = new MediaBrowserTimer(name);
        mediaBrowserTimer.initTimer(0);
    }

    public void test(String caller) {
        System.out.println("MyViewModel.test() called from: " + caller);
    }
}
