package ru.voronezhtsev.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static String FORECAST_DOWNLOADED = "ru.voronezhtsev.weatherapp.forecast.downloaded";
    public static String FORECAST_DOWNLOAD_FAILS = "ru.voronezhtsev.weatherapp.forecast.download.fails";
    //todo Dagger2 чтобы не создавать два раза инстанс хелпера, инжектить в презентере

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(FORECAST_DOWNLOADED);
            intentFilter.addAction(FORECAST_DOWNLOAD_FAILS);
            LocalBroadcastManager.getInstance(this).registerReceiver(mUpdateReceiver,
                    intentFilter);
            startService(new Intent(this, DownloadService.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mUpdateReceiver);
    }
    private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (FORECAST_DOWNLOADED.equals(intent.getAction())) {
                Toast.makeText(MainActivity.this,"Downloaded", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,"Download fails", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
