package ru.asaper.calendarpluscb.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DefaultDayViewAdapter;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.asaper.calendarpluscb.R;
import ru.asaper.calendarpluscb.activitys.interfaces.IChangeCalendar;

public class CalendarFragment extends Fragment implements CalendarPickerView.OnDateSelectedListener {

    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.calendar_view)
    @Nullable CalendarPickerView mCalendar;

    final Calendar mCalendarMin = Calendar.getInstance();
    final Calendar mCalendarMax = Calendar.getInstance();
    final Calendar mCalendarSelect = Calendar.getInstance();

    IChangeCalendar mCallback;

    public CalendarFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        mCalendarMin.add(Calendar.YEAR, -10);
        mCalendarMax.add(Calendar.DAY_OF_MONTH, 1);

        if (null != mCalendar) {
            mCalendar.setCustomDayView(new DefaultDayViewAdapter());
            mCalendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
            mCalendar.init(mCalendarMin.getTime(), mCalendarMax.getTime())
                    .inMode(CalendarPickerView.SelectionMode.SINGLE)
                    .withSelectedDate(/*mCalendarSelect.getTime()*/ new Date());

            mCalendar.setOnDateSelectedListener(this);
        }

        // получение выбранной даты mCalendar.getSelectedDate().getTime()

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    @Override
    public void onDateSelected(Date date) {
        mCalendarSelect.setTime(date);
        Log.d(TAG, "mCalendarSelect =" + mCalendarSelect);

        mCallback.onCalendarChanged(
                mCalendarSelect.get(Calendar.YEAR),
                mCalendarSelect.get(Calendar.MONTH),
                mCalendarSelect.get(Calendar.DAY_OF_MONTH)
        );

        //TODO call listener method onCalendarChanged(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onDateUnselected(Date date) {}

    // ===

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof IChangeCalendar) mCallback = (IChangeCalendar) context;
        else throw new RuntimeException(context.toString() + " must implement IChangeCalendar");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallback = null;
    }

    // ===

}
