package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroSensor;
    private SensorEventListener gyroEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroSensor == null) {
            Toast.makeText(this, "This device has no gyroscope!", Toast.LENGTH_SHORT).show();
            finish();
        }

        gyroEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroEventListener, gyroSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected  void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroEventListener);
    }
}
