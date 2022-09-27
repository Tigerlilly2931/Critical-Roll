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
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

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
    //private SharedPreferences mPrefs;
    private int randomNum;
    MediaPlayer mediaPlayer;
    final Handler handler = new Handler();
    pl.droidsonroids.gif.GifImageView gifImageView;
    int timer = 6000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crits = findViewById(R.id.critical);
        mediaPlayer = MediaPlayer.create(this, R.raw.dicerolling);
        gifImageView = findViewById(R.id.gifViewer);
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
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.dicerolling);
                mediaPlayer.start();
                rollDice();
            }
        });
    }

    private void rollDice(){
        randomNum = rng.nextInt(20) + 1;

        crits.setVisibility(TextView.INVISIBLE);
        imageViewDice.setVisibility(ImageView.INVISIBLE);
        gifImageView.setVisibility(GifImageView.VISIBLE);
        //mediaPlayer = MediaPlayer.create(this,R.raw.dicerolling);
        //mediaPlayer.start();
        switch (randomNum){
            case 1:
                gifImageView.setImageResource(R.drawable.dice0001);
                mediaPlayer = MediaPlayer.create(this, R.raw.manlaughing);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces1);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                        crits.setVisibility(TextView.VISIBLE);
                        mediaPlayer.start();
                        crits.setText(R.string.crit_miss);
                        crits.setTextColor(getColor(R.color.red));
                    }
                }, timer);
                break;
            case 2:
                gifImageView.setImageResource(R.drawable.dice0002);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces2);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer + 100);
                break;
            case 3:
                //imageViewDice.setImageResource(R.drawable.dicefaces3);
                gifImageView.setImageResource(R.drawable.dice0003);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces3);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 4:
                //imageViewDice.setImageResource(R.drawable.dicefaces4);
                gifImageView.setImageResource(R.drawable.dice0004);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces4);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 5:
                //imageViewDice.setImageResource(R.drawable.dicefaces5);
                gifImageView.setImageResource(R.drawable.dice0005);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces5);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer+100);
                break;
            case 6:
                //imageViewDice.setImageResource(R.drawable.dicefaces6);
                gifImageView.setImageResource(R.drawable.dice0006);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces6);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 7:
                //imageViewDice.setImageResource(R.drawable.dicefaces7);
                gifImageView.setImageResource(R.drawable.dice0007);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces7);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 8:
                //imageViewDice.setImageResource(R.drawable.dicefaces8);
                gifImageView.setImageResource(R.drawable.dice0008);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces8);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 9:
                //imageViewDice.setImageResource(R.drawable.dicefaces9);
                gifImageView.setImageResource(R.drawable.dice0009);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces9);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 10:
                //imageViewDice.setImageResource(R.drawable.dicefaces10);
                gifImageView.setImageResource(R.drawable.dice00010);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces10);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 11:
                //imageViewDice.setImageResource(R.drawable.dicefaces11);
                gifImageView.setImageResource(R.drawable.dice00011);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces11);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 12:
                //imageViewDice.setImageResource(R.drawable.dicefaces12);
                gifImageView.setImageResource(R.drawable.dice00012);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces12);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 13:
                //imageViewDice.setImageResource(R.drawable.dicefaces13);
                gifImageView.setImageResource(R.drawable.dice00013);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces13);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 14:
                //imageViewDice.setImageResource(R.drawable.dicefaces14);
                gifImageView.setImageResource(R.drawable.dice00014);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces14);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 15:
                //imageViewDice.setImageResource(R.drawable.dicefaces15);
                gifImageView.setImageResource(R.drawable.dice00015);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces15);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 16:
                //imageViewDice.setImageResource(R.drawable.dicefaces16);
                gifImageView.setImageResource(R.drawable.dice00016);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces16);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 17:
                //imageViewDice.setImageResource(R.drawable.dicefaces17);
                gifImageView.setImageResource(R.drawable.dice00017);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces17);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 18:
                //imageViewDice.setImageResource(R.drawable.dicefaces18);
                gifImageView.setImageResource(R.drawable.dice00018);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces18);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 19:
                //imageViewDice.setImageResource(R.drawable.dicefaces19);
                gifImageView.setImageResource(R.drawable.dice00019);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces19);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                    }
                }, timer);
                break;
            case 20:
                gifImageView.setImageResource(R.drawable.dice00020);
                mediaPlayer = MediaPlayer.create(this,R.raw.success);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gifImageView.setVisibility(GifImageView.INVISIBLE);
                        imageViewDice.setImageResource(R.drawable.dicefaces20);
                        imageViewDice.setVisibility(ImageView.VISIBLE);
                        crits.setVisibility(TextView.VISIBLE);
                        mediaPlayer.start();
                        crits.setText(R.string.crit_hit);
                        crits.setTextColor(getColor(R.color.green));
                    }
                }, timer);
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
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.dicerolling2);
                mediaPlayer.start();
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