package com.smartcommunications.testtimers;

import android.content.Intent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MediaBrowserTimer {
    private Timer timer;

    private Date lastTimeout = new Date();

    private long defaultTimeout = 10000L;

    private String name;

    public static String INTENT_EXTRA_INITIAL_TIMEOUT = "initialTimeout";

    public MediaBrowserTimer(String name) {
        this.name = name;
    }

    public long getInitialTimeout() {
        Date now = new Date();
        long timeNow = now.getTime();
        long lasTimeoutTime = lastTimeout.getTime();
        long diffInMillies = Math.abs(timeNow - lasTimeoutTime);
        System.out.println("timeNow: " + timeNow + " - lasTimeoutTime: " + lasTimeoutTime + " ->  diffInMillies: " + diffInMillies);

        long diffExpiresTime = Math.abs(defaultTimeout - diffInMillies);
        long calcTimeout = Math.min(diffExpiresTime, defaultTimeout);
        System.out.println("diffExpiresTime: " + diffExpiresTime + " defaultTimeout: " + defaultTimeout + " -> calcTimeout: " + calcTimeout);
        return calcTimeout;
    }
    public void initTimer(long initialTimeout) {
        long timeout = initialTimeout > 0 ? initialTimeout : defaultTimeout;
        System.out.println(name + "initTimer with timeout: " + timeout);
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        timerTimeout();
                    }
                },
                timeout);
    }

    public void pauseTimer() {
        System.out.println(name + "pauseTimer");
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    private void timerTimeout() {
        lastTimeout = new Date();
        System.out.println(name + "******* Timer timeout ******* -> time: " + lastTimeout.getTime());

        //Schedule another heartbeat in 30 seconds
        if (timer != null) {
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            timerTimeout();
                        }
                    },
                    defaultTimeout);
        }
    }

}
