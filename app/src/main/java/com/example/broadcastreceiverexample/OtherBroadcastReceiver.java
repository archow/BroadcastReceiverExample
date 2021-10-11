package com.example.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.example.broadcastreceiverexample.Constants.ACTION_NOTIFY;

public class OtherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //dynamically receive our broadcast from another activity...how do?
        //(again, make sure you have the action/intent name correct)
        if (intent.getAction().equals(ACTION_NOTIFY)) {
            Toast.makeText(context,
                    "Broad cast from other app received!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
