package ru.asaper.calendarpluscb.fragments.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ru.asaper.calendarpluscb.fragments.interfaces.IFragmentSyncValCurs;

public abstract class AbsFragmentSyncValCurs extends Fragment implements IFragmentSyncValCurs {

    protected static final String ARG_Y = "Y";
    protected static final String ARG_M = "M";
    protected static final String ARG_D = "D";

    public static final Bundle makeArgs(int y, int m, int d) {
        Bundle args = new Bundle();
        args.putInt(ARG_Y, y);
        args.putInt(ARG_M, m);
        args.putInt(ARG_D, d);
        return args;
    }

}
