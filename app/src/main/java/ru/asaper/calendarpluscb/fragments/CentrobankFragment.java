package ru.asaper.calendarpluscb.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Formatter;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.asaper.calendarpluscb.BuildConfig;
import ru.asaper.calendarpluscb.R;
import ru.asaper.calendarpluscb.activitys.base.ProgressActivity;
import ru.asaper.calendarpluscb.fragments.adapters.ButterKnifeBasedValuteRecyclerViewAdapter;
import ru.asaper.calendarpluscb.fragments.adapters.DataBindingBasedValuteRecyclerViewAdapter;
import ru.asaper.calendarpluscb.fragments.base.SyncFragmentArguments;
import ru.asaper.calendarpluscb.network.ServiceGenerator;
import ru.asaper.calendarpluscb.network.api.IValCursClient;
import ru.asaper.calendarpluscb.network.models.ValCurs;
import ru.asaper.calendarpluscb.utils.Utils;

public class CentrobankFragment extends SyncFragmentArguments {

    private final String TAG = getClass().getSimpleName();

    // --- Views ---

    @Bind(R.id.title)
    TextView mTitle;

    @Bind(android.R.id.list)
    RecyclerView mList;

    @Bind(android.R.id.empty)
    TextView mEmpty;

    // --- Data ---

    private Call<ValCurs> mCall;
    private ValCurs mValCurs;

    private int mColumnGridCount = 1;

    // === ===

    public CentrobankFragment() {
    }

    public static CentrobankFragment newInstance(int y, int m, int d) {
        Bundle args = makeArgs(y, m, d);
        CentrobankFragment fragment = new CentrobankFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    // ===

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_centrobank, container, false);

        ButterKnife.bind(this, rootView);

        // TODO: show TV for empty list
        //mList.setEmptyView(mEmpty);

        mColumnGridCount = getResources().getInteger(R.integer.column_count);

        if (mColumnGridCount <= 1) mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        else mList.setLayoutManager(new GridLayoutManager(getActivity(), mColumnGridCount));

        sync();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ProgressActivity.progressStop(getActivity());
        if ((null != mCall)
                && !mCall.isExecuted()
                && !mCall.isCanceled()) mCall.cancel();

        ButterKnife.unbind(this);
    }

    // ===

    private synchronized void sync() {
        if (BuildConfig.DEBUG) Log.d(TAG, "--> syncValCurs()");

        IValCursClient client = ServiceGenerator.createService(IValCursClient.class);

        String strDate = new Formatter().format("%1$02d/%2$02d/%3$04d", getD(), getM() + 1, getY()).toString();
        mCall = client.getCurses(strDate);

        mCall.enqueue(new Callback<ValCurs>() {
            @Override
            public void onResponse(Call<ValCurs> call, Response<ValCurs> response) {
                if (BuildConfig.DEBUG) Log.d(TAG, "--> call.onResponse()");

                if (response.isSuccess()) { // code 2xx..3xx

                    mValCurs = response.body();
                    if (BuildConfig.DEBUG) Log.i(TAG, "ValCurs=" + mValCurs);

                } else { // code 4xx, 5xx

                    String explain = "<none>";
                    try {
                        ResponseBody errorBody = response.errorBody();
                        explain = errorBody.string();
                    } catch (IOException e) {
                        explain = "Exception in getting error explanation's";
                        Log.e(TAG, explain, e);
                    }

                    Log.w(TAG, "errorBody=" + explain);

                    Utils.showErrorToast(getActivity(), explain);

                }

                // show results
                if (!call.isCanceled()) {
                    mTitle.setText(mValCurs.date);
                    showValCurs();
                }

                ProgressActivity.progressStop(getActivity());

                if (BuildConfig.DEBUG) Log.d(TAG, "<-- call.onResponse()");
            }

            @Override
            public void onFailure(Call<ValCurs> call, Throwable t) {
                if (BuildConfig.DEBUG) Log.d(TAG, "<-- call.onFailure()");

                Log.e(TAG, "Failure request!", t);

                Utils.showErrorToast(getActivity(), TAG + ".Some problem in getting data!");

                ProgressActivity.progressStop(getActivity());

                if (BuildConfig.DEBUG) Log.d(TAG, "--> call.onFailure()");
            }
        });

        ProgressActivity.progressStart(getActivity());

        if (BuildConfig.DEBUG) Log.d(TAG, "--> syncValCurs()");
    }

    protected void showValCurs() {


        if (false) {

            // FOR BUTTER KNIFE BASED ADAPTER
            mList.setAdapter(
                    new ButterKnifeBasedValuteRecyclerViewAdapter(
                            mValCurs.list,
                            mColumnGridCount <= 1 ? R.layout.item_valute_hor : R.layout.item_valute_vert));

        } else {

            // FOR DATA BINDING BASED ADAPTER
            mList.setAdapter(
                    new DataBindingBasedValuteRecyclerViewAdapter(
                            mValCurs.list));

        }

    }

    @Override
    public void updateFragment(int y, int m, int d) {
        super.updateFragment(y, m, d);

        sync();
    }
}
