package com.example.phonebroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneBroadcastReceiver extends BroadcastReceiver {

    // FIELDS
    public final static String TAG = "PhoneBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras != null){
            // VAMOS AVER EL ESTADO DEL MOVIL
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
                Log.d(TAG, "Intent INCOMING CALL recived");

        }
        Log.d(TAG, "Intent recived");
    }
}
