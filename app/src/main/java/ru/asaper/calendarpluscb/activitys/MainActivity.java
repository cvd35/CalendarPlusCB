package ru.asaper.calendarpluscb.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.asaper.calendarpluscb.R;
import ru.asaper.calendarpluscb.activitys.base.ProgressActivity;
import ru.asaper.calendarpluscb.activitys.interfaces.IChangeCalendar;
import ru.asaper.calendarpluscb.fragments.base.AbsFragmentSyncValCurs;

public class MainActivity extends ProgressActivity implements IChangeCalendar {

    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.fab) FloatingActionButton mFAB;
    @Nullable AbsFragmentSyncValCurs fragmentCentrobank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (findViewById(R.id.fragment_right) != null) {
            fragmentCentrobank = (AbsFragmentSyncValCurs) getSupportFragmentManager().findFragmentById(R.id.fragment_right);
        }

        setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.fab)
    public void onClickFAB(View view) {

        // TODO: шарим текст или картинку

        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_graph) {

            // TODO: подменяем список графиком

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ===

    @Override
    public void onCalendarChanged(int y, int m, int d) {

        if (null == fragmentCentrobank) { // show as an Activity

            Bundle options = fragmentCentrobank.makeArgs(y, m, d);

            Intent intent = new Intent(this, CentrobankActivity.class);
            intent.putExtras(options);
            startActivity(intent);

        } else { // as a Fragment

            fragmentCentrobank.updateFragment(y, m, d);

        }

    }
}
