package com.example.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");

        Intent receiverIntent = new Intent(context, ReceiverActivity.class);
        receiverIntent.putExtra("message", message);
        receiverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(receiverIntent);
    }
}




