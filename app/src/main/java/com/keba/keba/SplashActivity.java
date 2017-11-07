package com.keba.keba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sparrow on 5/8/2017.
 * Shows a splash screen with the joyride logo during the times_s the main activity is built.
 * https://android.jlelse.eu/right-way-to-create-splash-screen-on-android-e7f1709ba154
 */
public class SplashActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start home activity
        startActivity(new Intent(SplashActivity.this, StartActivity.class));
        // close splash activity
        finish();
    }

}
