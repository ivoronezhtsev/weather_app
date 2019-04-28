package ru.voronezhtsev.weatherapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.voronezhtsev.weatherapp.R;


public class MainActivity extends AppCompatActivity implements MainView {

    //public static String FORECAST_DOWNLOADED = "ru.voronezhtsev.weatherapp.forecast.downloaded";
    //public static String FORECAST_DOWNLOAD_FAILS = "ru.voronezhtsev.weatherapp.forecast.download.fails";
    private MainPresenter mMainPresenter = new MainPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*if(savedInstanceState == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(FORECAST_DOWNLOADED);
            intentFilter.addAction(FORECAST_DOWNLOAD_FAILS);
            LocalBroadcastManager.getInstance(this).registerReceiver(mUpdateReceiver,
                    intentFilter);
            startService(new Intent(this, DownloadService.class));
        }*/
        mMainPresenter.onAttachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(mUpdateReceiver);
    }
    /*private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (FORECAST_DOWNLOADED.equals(intent.getAction())) {
                Toast.makeText(MainActivity.this,"Downloaded", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,"Download fails", Toast.LENGTH_SHORT).show();
            }
        }
    };*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDetachView();
    }

    @Override
    public void showTemperature(int temp) {

    }
}
