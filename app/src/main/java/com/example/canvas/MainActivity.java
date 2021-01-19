package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Bola pelota;
    int width, height;
    SensorManager sm;
    Sensor smRotacion;
    int goles;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //contador=0;
        pelota = new Bola(this);

        setContentView(pelota);
        mp = MediaPlayer.create(this, R.raw.gol);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        smRotacion = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, smRotacion, SensorManager.SENSOR_DELAY_FASTEST);

        Display Windows = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        height = Windows.getHeight();
        width = Windows.getWidth();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        pelota.axis -= Math.round(event.values[0]);
        pelota.x = String.format("%.2f", pelota.axis);
        if (pelota.axis < (pelota.size + pelota.border)) {
            pelota.axis = (pelota.size + pelota.border);
        } else if (pelota.axis > (width - (pelota.size + pelota.border))) {
            pelota.axis = width - (pelota.size + pelota.border);
        }

        pelota.axisy += Math.round(event.values[1]);
        pelota.y = String.format("%.2f", pelota.axisy);
        if (pelota.axisy < (pelota.size + pelota.border)) {
            pelota.axisy = (pelota.size + pelota.border);
        } else if (pelota.axisy > (height - pelota.size - 110)) {
            if (pelota.axis >= width / 2 - 100 && pelota.axis <= width / 2 + 100) {
                pelota.axisy = height - pelota.size - 110;
                pelota.goles += 1;
                pelota.axis = 0;
                pelota.axisy = 0;
                mp.start();
            } else {
                pelota.axisy = height - pelota.size - 110;
            }

        }

        pelota.axisz = event.values[2];
        pelota.z = String.format("%.2f", pelota.axisz);

        pelota.invalidate();


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, smRotacion, SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

}



