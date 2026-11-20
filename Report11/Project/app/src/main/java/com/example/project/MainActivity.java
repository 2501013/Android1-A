package com.example.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final BroadcastReceiver dynamicReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");

            Toast.makeText(
                    context,
                    "동적 Receiver : " + message,
                    Toast.LENGTH_SHORT
            ).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter(
                "com.example.project.ACTION_DYNAMIC"
        );

        ContextCompat.registerReceiver(
                this,
                dynamicReceiver,
                filter,
                ContextCompat.RECEIVER_NOT_EXPORTED
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(dynamicReceiver);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button1) {
            sendDynamic();
        } else if (id == R.id.button2) {
            sendStatic();
        }
    }

    private void sendDynamic() {
        Intent intent = new Intent("com.example.project.ACTION_DYNAMIC");
        intent.putExtra("message", "FILE_DOWNLOADED Kyungbok.png");
        intent.setPackage(getPackageName());

        sendBroadcast(intent);
    }

    private void sendStatic() {
        Intent intent = new Intent(this, StaticReceiver.class);
        intent.setAction("com.example.project.ACTION_STATIC");
        intent.putExtra("message", "경복대학교.png");

        sendBroadcast(intent);
    }
}

