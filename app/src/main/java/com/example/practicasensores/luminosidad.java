package com.example.practicasensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class luminosidad extends AppCompatActivity {


    Button regresar;
    Sensor misensorr;
    SensorManager admistradorsensoress;
    SensorEventListener escuchuadordeEventoss;

    TextView etiquetaresultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luminosidad);
        setContentView(R.layout.activity_luminosidad);



        etiquetaresultados = findViewById(R.id.TXVresultado);

        admistradorsensoress = (SensorManager) getSystemService(SENSOR_SERVICE);

        misensorr = admistradorsensoress.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (misensorr == null) {
            Toast.makeText(luminosidad.this, "No se detecto el sensor ", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(luminosidad.this, "El sensor se a detectado", Toast.LENGTH_SHORT).show();
        }

        escuchuadordeEventoss = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {


                etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] );
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        inicializarSensor();

        regresar= findViewById(R.id.BTNregresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void inicializarSensor(){
        admistradorsensoress.registerListener(escuchuadordeEventoss,misensorr,SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void  detenerSensor(){
        admistradorsensoress.unregisterListener(escuchuadordeEventoss,misensorr);
    }

    @Override
    protected void onResume() {
        inicializarSensor();
        super.onResume();
    }

    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }
}