package ru.asaper.calendarpluscb.fragments.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import ru.asaper.calendarpluscb.BuildConfig;

public class SyncFragmentArguments extends AbsFragmentSyncValCurs {

    private final String TAG = getClass().getSimpleName();;

    private int mParamY;
    private int mParamM;
    private int mParamD;

    // ===

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // from which data?
        Bundle bundle = (null != getArguments()) ? getArguments() : getActivity().getIntent().getExtras();;

        if (bundle != null
                && bundle.containsKey(ARG_Y)
                && bundle.containsKey(ARG_M)
                && bundle.containsKey(ARG_D))
        {
            mParamY = bundle.getInt(ARG_Y);
            mParamM = bundle.getInt(ARG_M);
            mParamD = bundle.getInt(ARG_D);

            if (BuildConfig.DEBUG) Log.d(TAG, "Have got date. Y=" + mParamY + ", M=" + mParamM + ", D=" + mParamD);
        } else {
            Log.w(TAG, "Try to init parameters manually");

            Calendar rightNow = Calendar.getInstance();
            mParamY = rightNow.get(Calendar.YEAR);
            mParamM = rightNow.get(Calendar.MONTH);
            mParamD = rightNow.get(Calendar.DAY_OF_MONTH);
        }

    }

    // ---

    @Override
    public int getY() {
        return mParamY;
    }

    @Override
    public int getM() {
        return mParamM;
    }

    @Override
    public int getD() {
        return mParamD;
    }

    @Override
    @CallSuper
    public void updateFragment(int y, int m, int d) {
        mParamY = y;
        mParamM = m;
        mParamD = d;
    }

    // ===

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mParamY = savedInstanceState.getInt(ARG_Y);
            mParamM = savedInstanceState.getInt(ARG_M);
            mParamD = savedInstanceState.getInt(ARG_D);
        }

        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_Y, mParamY);
        outState.putInt(ARG_M, mParamM);
        outState.putInt(ARG_D, mParamD);
    }

    // ===
}
