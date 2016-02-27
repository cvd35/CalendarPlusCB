package ru.asaper.calendarpluscb.network.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class ValCurs {
    @Attribute(name = "Date")
    public String date;

    @Attribute(name = "name")
    public String name;

    @ElementList(inline = true)
    public List<Valute> list;

    @Override
    public String toString() {
        return "ValCurs{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
