package ru.asaper.calendarpluscb.activitys.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.asaper.calendarpluscb.BuildConfig;
import ru.asaper.calendarpluscb.R;
import ru.asaper.calendarpluscb.activitys.interfaces.IWindowProgress;

public class ProgressActivity extends AppCompatActivity implements IWindowProgress {

    public static class Constants {
        public static final String BROADCAST_SYNC_START = BuildConfig.APPLICATION_ID + ".broadcast_sync_start";
        public static final String BROADCAST_SYNC_END = BuildConfig.APPLICATION_ID + ".broadcast_sync_end";
    }

    private static final String TAG = ProgressActivity.class.getSimpleName();

    @Bind(R.id.progressBar)
    protected ProgressBar progressBar;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initProgress() {
        ButterKnife.bind(this);

        hideProgress();
    }

    @Override
    public void showProgress() {
        setState(true);
    }

    @Override
    public void hideProgress() {
        setState(false);
    }

    // ---

    private void setState(boolean state) {
        if (null != progressBar) progressBar.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
    }

    // ===

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(onEventStartProgtess,
                new IntentFilter(Constants.BROADCAST_SYNC_START));

        LocalBroadcastManager.getInstance(this).registerReceiver(onEventStopProgress,
                new IntentFilter(Constants.BROADCAST_SYNC_END));
    }

    @CallSuper
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onEventStartProgtess);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onEventStopProgress);

        super.onPause();
    }

    // ===

    private BroadcastReceiver onEventStartProgtess = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (BuildConfig.DEBUG) Log.d(TAG, "--> onEventStartProgtess");

                    showProgress();

                    if (BuildConfig.DEBUG) Log.d(TAG, "<-- onEventStartProgtess");
                }
            });

        }
    };

    private BroadcastReceiver onEventStopProgress = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (BuildConfig.DEBUG) Log.d(TAG, "--> onEventStopProgress");

                    hideProgress();

                    if (BuildConfig.DEBUG) Log.d(TAG, "<-- onEventStopProgress");
                }
            });

        }
    };

    // ===

    public static void progressStart(Context context) {
        progressBroadcastSender(context, Constants.BROADCAST_SYNC_START);
    }
    public static void progressStop(Context context) {
        progressBroadcastSender(context, Constants.BROADCAST_SYNC_END);
    }
    private static void progressBroadcastSender(Context context, String action) {
        LocalBroadcastManager
                .getInstance(context)
                .sendBroadcast(new Intent(action));

    }

    // ===

}
