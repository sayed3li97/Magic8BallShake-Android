package com.gdgmanama.magic8ballshake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String[] predictions;
    Random random;

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        predictions = new String[20];
        predictions[0] = "It is certain";
        predictions[1] = "It is decidedly so";
        predictions[2] = "Without a doubt";
        predictions[3] = "Yes - definitely";
        predictions[4] = "You may rely on it";
        predictions[5] = "As I see it, yes";
        predictions[6] = "Most likely";
        predictions[7] = "Outlook good";
        predictions[8] = "Yes";
        predictions[9] = "Signs point to yes";
        predictions[10] = "Reply hazy, try again";
        predictions[11] = "Ask again later";
        predictions[12] = "Better not tell you now";
        predictions[13] = "Cannot predict now";
        predictions[14] = "Concentrate and ask again";
        predictions[15] = "Don't count on it";
        predictions[16] = "My reply is no";
        predictions[17] = "My sources say no";
        predictions[18] = "Outlook not so good";
        predictions[19] = "Very doubtful";

        random = new Random();

        textView = (TextView) findViewById(R.id.textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(predictions[random.nextInt(20)]);
            }
        });

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
//                handleShakeEvent(count);
                textView.setText(predictions[random.nextInt(20)]);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
