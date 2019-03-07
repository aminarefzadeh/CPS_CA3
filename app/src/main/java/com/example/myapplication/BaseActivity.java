package com.example.myapplication;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor gyroSensor;
    private Sensor gravitySensor;
    private Render render = new Render();
    private Boolean isGravity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String sensorType = intent.getStringExtra("type");
        double gravity, muk, mus;
        gravity = intent.getDoubleExtra("gravity", 10.0);
        muk = intent.getDoubleExtra("Muk", 0.1);
        mus = intent.getDoubleExtra("Mus", 0.2);
        render.setGravity(gravity);
        render.setMuk(muk);
        render.setMus(mus);
        setContentView(R.layout.activity_base);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorType.equals("gyro")) {

            gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

            if (gyroSensor == null) {
                Toast.makeText(this, "This device has no gyroscope!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else {

            this.isGravity = true;
            gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

            if (gravitySensor == null) {
                Toast.makeText(this, "This device has no Gravity Sensor !", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(this.isGravity) {
            sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        else {
            sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    protected  void onPause() {
        super.onPause();
        if(this.isGravity) {
            sensorManager.unregisterListener(this);
        }
        else {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (this.isGravity){
            System.out.println(event.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
