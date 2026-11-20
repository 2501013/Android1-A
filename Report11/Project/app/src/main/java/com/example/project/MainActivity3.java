package com.example.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity3 extends AppCompatActivity {

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                Toast.makeText(context, "전원 연결됨", Toast.LENGTH_SHORT).show();
            }
            else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                Toast.makeText(context, "전원 연결 해제됨", Toast.LENGTH_SHORT).show();
            }
            else if (Intent.ACTION_HEADSET_PLUG.equals(action)) {

                int state = intent.getIntExtra("state", -1);
                String name = intent.getStringExtra("name");
                int microphone = intent.getIntExtra("microphone", 0);

                if (state == 1) {
                    Toast.makeText(context, "Headset 연결되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else if (state == 0) {
                    Toast.makeText(context, "Headset 분리되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        ContextCompat.registerReceiver(
                this,
                receiver,
                filter,
                ContextCompat.RECEIVER_EXPORTED
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}