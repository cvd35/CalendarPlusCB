package ru.asaper.calendarpluscb.network.models.transformers;

import android.util.Log;

import org.simpleframework.xml.transform.Transform;

import ru.asaper.calendarpluscb.network.models.base.ExtraDouble;

public class ExtraDoubleTransformer implements Transform<ExtraDouble> {

    private static final String TAG = ExtraDoubleTransformer.class.getSimpleName();

    @Override
    public ExtraDouble read(String value) throws Exception {
        return new ExtraDouble(value);
    }

    @Override
    public String write(ExtraDouble value) throws Exception {
        return value.toString();
    }
}
