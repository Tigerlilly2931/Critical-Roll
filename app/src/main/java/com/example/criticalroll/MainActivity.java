package com.example.criticalroll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
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
    private TextView crits;
    private SharedPreferences mPrefs;
    private int randomNum;
    MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crits = findViewById(R.id.critical);
        mediaPlayer = MediaPlayer.create(this, R.raw.dicerolling);
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
        randomNum = rng.nextInt(20) + 1;
        crits.setVisibility(TextView.INVISIBLE);
        mediaPlayer = MediaPlayer.create(this,R.raw.dicerolling);
        mediaPlayer.start();
        switch (randomNum){
            case 1:
                imageViewDice.setImageResource(R.drawable.dicefaces1);
                crits.setVisibility(TextView.VISIBLE);
                mediaPlayer = MediaPlayer.create(this,R.raw.manlaughing);
                mediaPlayer.start();
                crits.setText(R.string.crit_miss);
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.dicefaces2);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.dicefaces3);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.dicefaces4);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.dicefaces5);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.dicefaces6);
                break;
            case 7:
                imageViewDice.setImageResource(R.drawable.dicefaces7);
                break;
            case 8:
                imageViewDice.setImageResource(R.drawable.dicefaces8);
                break;
            case 9:
                imageViewDice.setImageResource(R.drawable.dicefaces9);
                break;
            case 10:
                imageViewDice.setImageResource(R.drawable.dicefaces10);
                break;
            case 11:
                imageViewDice.setImageResource(R.drawable.dicefaces11);
                break;
            case 12:
                imageViewDice.setImageResource(R.drawable.dicefaces12);
                break;
            case 13:
                imageViewDice.setImageResource(R.drawable.dicefaces13);
                break;
            case 14:
                imageViewDice.setImageResource(R.drawable.dicefaces14);
                break;
            case 15:
                imageViewDice.setImageResource(R.drawable.dicefaces15);
                break;
            case 16:
                imageViewDice.setImageResource(R.drawable.dicefaces16);
                break;
            case 17:
                imageViewDice.setImageResource(R.drawable.dicefaces17);
                break;
            case 18:
                imageViewDice.setImageResource(R.drawable.dicefaces18);
                break;
            case 19:
                imageViewDice.setImageResource(R.drawable.dicefaces19);
                break;
            case 20:
                imageViewDice.setImageResource(R.drawable.dicefaces20);
                crits.setVisibility(TextView.VISIBLE);
                mediaPlayer = MediaPlayer.create(this,R.raw.success);
                mediaPlayer.start();
                crits.setText(R.string.crit_hit);
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
        rollDice();
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