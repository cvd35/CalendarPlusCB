package ru.asaper.calendarpluscb.network.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import ru.asaper.calendarpluscb.network.models.base.ExtraDouble;

@Root(name = "Valute")
public class Valute {
    @Attribute(name = "ID")
    private String id;

    @Element(name = "NumCode")
    private int numCode;

    @Element(name = "CharCode")
    public String charCode;

    @Element(name = "Nominal")
    public int nominal;

    @Element(name = "Name")
    public String name;

    @Element(name = "Value", type = ExtraDouble.class)
    public ExtraDouble value;

    @Override
    public String toString() {
        return "Valute{" +
                "id='" + id + '\'' +
                ", numCode=" + numCode +
                ", charCode='" + charCode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
