package com.fatel.mamtv1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Administrator on 4/11/2558.
 */
public class HttpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String temp = extras.getString("complete");
        if(temp==null){
            Log.i("null", "temp null");
        }
        else{
            Log.i("!null",temp);
        }
    }
}
