package com.example.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity2 extends AppCompatActivity {

    private TextView textCount;
    private TextView textConnection;
    private TextView textStatus;
    private TextView textPercent;
    private ProgressBar progressBar;

    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int level = intent.getIntExtra(
                    BatteryManager.EXTRA_LEVEL, -1);

            int scale = intent.getIntExtra(
                    BatteryManager.EXTRA_SCALE, 100);

            int plugged = intent.getIntExtra(
                    BatteryManager.EXTRA_PLUGGED, 0);

            int status = intent.getIntExtra(
                    BatteryManager.EXTRA_STATUS, -1);

            int percent = scale > 0 ? level * 100 / scale : 0;

            String connection;

            if (plugged == 0) {
                connection = "Battery";
            } else if (plugged == BatteryManager.BATTERY_PLUGGED_AC) {
                connection = "AC";
            } else if (plugged == BatteryManager.BATTERY_PLUGGED_USB) {
                connection = "USB";
            } else if (plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                connection = "Wireless";
            } else {
                connection = "기타 전원";
            }

            String batteryStatus;

            switch (status) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    batteryStatus = "충전 중";
                    break;

                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    batteryStatus = "방전 중";
                    break;

                case BatteryManager.BATTERY_STATUS_FULL:
                    batteryStatus = "가득 충전";
                    break;

                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    batteryStatus = "충전 중 아님";
                    break;

                default:
                    batteryStatus = "알 수 없음";
                    break;
            }

            textCount.setText("수신 횟수 : 1");
            textConnection.setText("연결 : " + connection);
            textStatus.setText("상태 : " + batteryStatus);

            progressBar.setProgress(percent);
            textPercent.setText(percent + " %");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textCount = findViewById(R.id.textCount);
        textConnection = findViewById(R.id.textConnection);
        textStatus = findViewById(R.id.textStatus);
        textPercent = findViewById(R.id.textPercent);
        progressBar = findViewById(R.id.progressBar);

        textCount.setText("수신 횟수 : 1");
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED
        );

        ContextCompat.registerReceiver(
                this,
                batteryReceiver,
                filter,
                ContextCompat.RECEIVER_EXPORTED
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryReceiver);
    }
}
