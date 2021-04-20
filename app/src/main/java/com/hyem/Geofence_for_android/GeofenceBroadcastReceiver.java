package com.hyem.Geofence_for_android;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;


public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    final String TAG = "GeofenceReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceStatusCodes
                    .getStatusCodeString(geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // Get the transition details as a String.
            String geofenceTransitionDetails = getGeofenceTransitionDetails(
                    this,
                    geofenceTransition,
                    triggeringGeofences
            );

            // Send notification and log the transition details.
            sendNotification(geofenceTransitionDetails);
            Log.i(TAG, geofenceTransitionDetails);
        } else {
            // Log the error.
            Log.e(TAG,"error");
//            Log.e(TAG, getString(R.string.geofence_transition_invalid_type,
//                    geofenceTransition));
        }
    }

    private String getGeofenceTransitionDetails(GeofenceBroadcastReceiver geofenceBroadcastReceiver, int geofenceTransition, List<Geofence> triggeringGeofences) {
        String transitionString = "";
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            transitionString = "IN-";
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            transitionString = "OUT-";
        }
//      else if(geofenceTransition==Geofence.GEOFENCE_TRANSITION_DWELL){}
        else {
            transitionString = "OTHER-";
        }

        Log.d(TAG,transitionString);
        return String.format("%s: %s", transitionString, TextUtils.join(", ", triggeringGeofences));
    }

    private void sendNotification(String notificationDetails) {

//        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//
//        builder.setColor(Notification.COLOR_DEFAULT)
//                .setContentTitle(notificationDetails)
//                .setContentText("Click notification to remove")
//                .setContentIntent(pendingIntent)
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setVibrate(new long[]{1000, 1000})
//                .setAutoCancel(true);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(generateRandom(), builder.build());
    }

}

