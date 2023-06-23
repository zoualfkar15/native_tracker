package com.example.donaleb;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public interface StepsEvents {

    void onChange(int steps);

  //  void onAccuracyChanged(Sensor var1, int var2);

}

