package com.example.criticalroll;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView xTextView, yTextView, zTextview;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private boolean isAccSenAvailable, notFirstTime = false;
    private float currentX, currentY, currentZ, lastX, lastY, lastZ, xDiff, yDiff, zDiff, shakeThresh = 5f;
    private Vibrator vibrator;
    private ImageView imageViewDice;
    private Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xTextView = findViewById(R.id.xTextView);
        //yTextView = findViewById(R.id.yTextView);
        //zTextview = findViewById(R.id.zTextView);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAccSenAvailable = true;
        } else{
            xTextView.setText("Accelerometer sensor is not available.");
            isAccSenAvailable = false;
        }

        imageViewDice = findViewById(R.id.DicePic);
        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });
    }

    private void rollDice(){
        int randomNum = rng.nextInt(6) + 1;
        switch (randomNum){
            case 1:
                imageViewDice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.dice6);
                break;
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //xTextView.setText((sensorEvent.values[0] + "m/s2"));
        //yTextView.setText((sensorEvent.values[1] + "m/s2"));
        //zTextview.setText((sensorEvent.values[2] + "m/s2"));

        currentX = sensorEvent.values[0];
        currentY = sensorEvent.values[1];
        currentZ = sensorEvent.values[2];

        if(notFirstTime){
            xDiff = Math.abs(lastX - currentX);
            yDiff = Math.abs(lastY - currentY);
            zDiff = Math.abs(lastZ = currentZ);

            if((xDiff > shakeThresh && yDiff > shakeThresh) || (xDiff > shakeThresh && zDiff > shakeThresh) || (zDiff > shakeThresh && yDiff > shakeThresh)){
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                rollDice();
            }
        }

        lastX = currentX;
        lastY = currentY;
        lastZ = currentZ;
        notFirstTime = true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isAccSenAvailable){
            sensorManager.registerListener(this,accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isAccSenAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}