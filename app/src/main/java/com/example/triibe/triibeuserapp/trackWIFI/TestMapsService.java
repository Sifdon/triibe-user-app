//package com.example.triibe.triibeuserapp.trackWIFI;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.HandlerThread;
//import android.os.IBinder;
//import android.os.Looper;
//import android.os.Message;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.triibe.triibeuserapp.util.Globals;
//import com.google.android.gms.maps.model.IndoorBuilding;
//import com.google.android.gms.maps.model.IndoorLevel;
//import com.google.android.gms.maps.model.LatLng;
//
//import java.util.List;
//
//public class TestMapsService extends Service {
//
//    public TestMapsService() {}
//
//    private static final String TAG = "TestMapsService";
//
//    private Looper mServiceLooper;
//    private ServiceHandler mServiceHandler;
//
//    // Handler that receives messages from the thread
//    private final class ServiceHandler extends Handler {
//        public ServiceHandler(Looper looper) {
//            super(looper);
//        }
//        @Override
//        public void handleMessage(Message msg) {
//
//            if (Globals.mMap.isIndoorEnabled()) {
//                if (Globals.mMap.getFocusedBuilding() != null) {
//                    IndoorBuilding indoorBuilding = Globals.mMap.getFocusedBuilding();
//                    if (indoorBuilding.getLevels() != null) {
//                        List<IndoorLevel> levels = indoorBuilding.getLevels();
//                        for (int i = 0; i < levels.size(); i++) {
//                            Log.d(TAG, "Level " + levels.get(i).getName() + " exists.");
//                        }
//                        Log.d(TAG, "Current level: " + indoorBuilding.getActiveLevelIndex());
//                    }
//                }
//            } else {
//                Log.d(TAG, "Not indoor enabled");
//            }
//
//            LatLng location = Globals.mMap.getCameraPosition().target;
//            Log.d(TAG, "Location: " + location.latitude + ", " + location.longitude);
//        }
//    }
//
//    @Override
//    public void onCreate() {
//        // Start up the thread running the service.  Note that we create a
//        // separate thread because the service normally runs in the process's
//        // main thread, which we don't want to block.  We also make it
//        // background priority so CPU-intensive work will not disrupt our UI.
//        HandlerThread thread = new HandlerThread("ServiceStartArguments", Thread.MIN_PRIORITY);
//        thread.start();
//
//        // Get the HandlerThread's Looper and use it for our Handler
//        mServiceLooper = thread.getLooper();
//        mServiceHandler = new ServiceHandler(mServiceLooper);
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(this, "maps service starting", Toast.LENGTH_SHORT).show();
//
//        // For each start request, send a message to start a job and deliver the
//        // start ID so we know which request we're stopping when we finish the job
//        Message msg = mServiceHandler.obtainMessage();
//        msg.arg1 = startId;
//        mServiceHandler.sendMessage(msg);
//
//        // If we get killed, after returning from here, restart
//        return START_STICKY;
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // We don't provide binding, so return null
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        Toast.makeText(this, "maps service done", Toast.LENGTH_SHORT).show();
//    }
//}