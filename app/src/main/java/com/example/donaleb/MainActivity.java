package com.example.donaleb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
//import com.google.android.gms.fitness.data.DataReadRequest;
//import com.google.android.gms.fitness.data.DataReadResult;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private StepCounter stepCounter;

    int steps1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView= findViewById(R.id.steps);
        final TextView textView1= findViewById(R.id.steps2);

        stepCounter = new StepCounter(this, new StepsEvents() {
            @Override
            public void onChange(int steps) {



                runOnUiThread(() -> {
                  //  Toast.makeText(MainActivity.this,"1111",Toast.LENGTH_LONG).show();

                    steps1+=steps;
                    textView.setText(String.valueOf(steps1));
                    textView1.setText(String.valueOf(steps));

                });
            }
        });


        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //    stepCounter.startCounting();
               // stepCounter.resetSteps();
                Toast.makeText(MainActivity.this,"aaa",Toast.LENGTH_LONG).show();
            }
        });


        stepCounter.startCounting();

    }


    @Override
    protected void onPause() {
        super.onPause();
        stepCounter.stopCounting();
    }

}

