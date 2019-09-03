package com.example.abdullahrao.bitgoapp.StepCounter;


// Will listen to step alerts
public interface StepListener {

    public void step(long timeNs);

}