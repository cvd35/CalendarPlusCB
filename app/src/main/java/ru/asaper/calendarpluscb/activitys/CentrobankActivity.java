package ru.asaper.calendarpluscb.activitys;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.asaper.calendarpluscb.R;
import ru.asaper.calendarpluscb.activitys.base.ProgressActivity;

public class CentrobankActivity extends ProgressActivity {

    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centrobank);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

    }

    @OnClick(R.id.fab)
    public void onClickFAB(View view) {

        // TODO: шарим текст или картинку

        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

}
