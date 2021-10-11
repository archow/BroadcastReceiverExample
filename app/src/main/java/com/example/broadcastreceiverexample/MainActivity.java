package com.example.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import static com.example.broadcastreceiverexample.Constants.ACTION_NOTIFY;
import static com.example.broadcastreceiverexample.Constants.MY_PERMISSION;

public class MainActivity extends AppCompatActivity {
    private ExampleBroadcastReceiver mReceiver;
    private OtherBroadcastReceiver mOtherReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //register your broadcast receiver by:
        //creating an intent filter
        //creating an instance of your broadcast receiver
        //passing both to the .registerReciever method from this context
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);


        //let's do the same thing (register a broadcast receiver) using our other
        //activity's action
        IntentFilter otherIntentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_NOTIFY);
        mOtherReceiver = new OtherBroadcastReceiver();
        this.registerReceiver(mOtherReceiver, otherIntentFilter,
            MY_PERMISSION, null);

        mReceiver = new ExampleBroadcastReceiver();
        this.registerReceiver(mReceiver, intentFilter,
        //if i wanted to restrict which types of broadcasts i respond to:
                //i set the permissions required for it
                Manifest.permission.ACCESS_BACKGROUND_LOCATION, null);

        //to register local broadcast reciever:
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister your receiver in onPause( )
        if (mReceiver != null) {
            this.unregisterReceiver(mReceiver);

            //to unregister local broadcast reciever:
            LocalBroadcastManager.getInstance(this)
                    .unregisterReceiver(mReceiver);
        }
    }
}