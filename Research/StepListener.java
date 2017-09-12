package com.example.danielle.pedometerapp;

/**
 * Created by Danielle on 9/10/2017.
 */

// Will listen to step alerts
public interface StepListener {

    public void step(long timeNs);

}