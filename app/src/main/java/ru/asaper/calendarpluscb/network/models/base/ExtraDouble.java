package ru.asaper.calendarpluscb.network.models.base;

import android.text.TextUtils;
import android.util.Log;

public class ExtraDouble {

    private static final String TAG = ExtraDouble.class.getSimpleName();

    public Double d = -1.0;

    public ExtraDouble() {
        Log.d(TAG, "ExtraDouble()");
    }

    public ExtraDouble(ExtraDouble ex) {
        Log.d(TAG, "ExtraDouble(ex)");
        Log.d(TAG, "ex=" + ex);
        this.d = ex.d;
    }

    public ExtraDouble(Double d) {
        Log.d(TAG, "ExtraDouble(Double d)");
        Log.d(TAG, "d=" + d);
        this.d = d;
    }

    public ExtraDouble(String s) {
        Log.d(TAG, "ExtraDouble(String s)");
        Log.d(TAG, "s=" + s);

        if (!TextUtils.isEmpty(s)) this.d = new Double(s.replace(',', '.'));
    }

    @Override
    public String toString() {
        return "ExtraDouble{" +
                "d=" + d +
                '}';
    }

}
