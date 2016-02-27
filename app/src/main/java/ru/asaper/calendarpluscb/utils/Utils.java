package ru.asaper.calendarpluscb.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static void showErrorToast(final Context context, final String explain) {
        if (null != context && !TextUtils.isEmpty(explain)) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

                    try {
                        Log.w(TAG, "showErrorToast:" + explain);
                        showShortToast(context, explain);
                    } catch (Exception e) { // catching all
                        Log.e(TAG, "Error in showing toast!", e);
                    }

                }
            });

        } else Log.w(TAG, "showErrorToast.Some error in parameters Context or String");
    }

    public static void showShortToast(@NonNull Context context, @NonNull String message) {
        Log.w(TAG, "showShortToast:" + message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
