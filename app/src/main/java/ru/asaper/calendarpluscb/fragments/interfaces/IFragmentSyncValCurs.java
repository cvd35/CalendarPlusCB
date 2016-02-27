package ru.asaper.calendarpluscb.fragments.interfaces;

public interface IFragmentSyncValCurs {
    int getY();
    int getM();
    int getD();

    void updateFragment(int y, int m, int d);
}
