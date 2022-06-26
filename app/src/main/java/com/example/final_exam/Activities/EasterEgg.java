package com.example.final_exam.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.final_exam.Locale.BaseActivity;
import com.example.final_exam.R;

public class EasterEgg extends BaseActivity implements SensorEventListener  {
    private SensorManager sensorManager;
    double ax,ay;
    double angle;
    float pivotX,pivotY;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter_egg);

        image = findViewById(R.id.imageView);
        pivotX = image.getPivotX();
        pivotY = image.getPivotY();
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ax=event.values[0];
            ay=event.values[1];

             angle = Math.atan2(ax, ay) / (Math.PI / 180);

            image.setRotation((float)angle);

        }
    }
}