package com.example.donaleb;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class StepCounter implements SensorEventListener {
    private static final double THRESHOLD =50 ;
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private int stepCount = 0;
    private  StepsEvents stepsEvents;

    public StepCounter(Context context,StepsEvents stepsEvents) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        this.stepsEvents=stepsEvents;
    }

    public void startCounting() {
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopCounting() {
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {





        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            stepsEvents.onChange(1);
        }

     //   stepsEvents.onChange(0);
//
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            float x = event.values[0];
//            float y = event.values[1];
//            float z = event.values[2];
//
//
//
//            float g = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
//
//            // Calculate magnitude of the acceleration
//            double magnitude = Math.sqrt(x * x + y * y + z * z);
//
//
//            // Detect steps based on the threshold
//            if (magnitude > calculateThreshold()) {
//                // Step detected
//                int  steps = calculateStepsCount(x,y,z);
//                stepsEvents.onChange(steps);
//            }
//
//        }
//

//        if (event != null) {
//            float x_acceleration = event.values[0];
//            float y_acceleration = event.values[1];
//            float z_acceleration = event.values[2];
//
//
//            if (event.values[0] > 6){
//                stepCount++;
//            }
//
//            stepsEvents.onChange(stepCount);
//        }
    }


    private int calculateStepsCount(float x, float y, float z) {

        // Initialize the steps count.
        int stepsCount = 0;

        // Get the magnitude of the acceleration vector.
        float magnitude = (float) Math.sqrt(x * x + y * y + z * z);

        // If the magnitude of the acceleration vector is greater than a threshold, then increment the steps count.
        if (magnitude > THRESHOLD) {
            stepsCount++;
        }

        // Return the steps count.
        return stepsCount;
    }


    public  void resetSteps(){

        sensorManager.unregisterListener(this);
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for step counter
    }

    public int getStepCount() {
        return stepCount;
    }

    private double calculateThreshold() {
        // Adjust these values based on your observations and testing
        double minAccelerationMagnitude = 3.0; // Minimum expected acceleration magnitude for walking steps
        double maxAccelerationMagnitude = 5.0; // Maximum expected acceleration magnitude for walking steps

        // Normalize the values based on the expected range
        double normalizedMin = minAccelerationMagnitude / SensorManager.GRAVITY_EARTH;
        double normalizedMax = maxAccelerationMagnitude / SensorManager.GRAVITY_EARTH;

        // Calculate the threshold as the average of the normalized values
        double threshold = (normalizedMin + normalizedMax) / 4.0;

        return threshold;
    }

}