package ru.asaper.calendarpluscb.fragments.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.asaper.calendarpluscb.R;
import ru.asaper.calendarpluscb.network.models.Valute;

public class ButterKnifeBasedValuteRecyclerViewAdapter extends RecyclerView.Adapter<ButterKnifeBasedValuteRecyclerViewAdapter.ViewHolder> {

    private final List<Valute> mList;
    private final int LAYOUT_RES;

    public ButterKnifeBasedValuteRecyclerViewAdapter(List<Valute> list, @LayoutRes int layoutRes) {
        this.mList = list;
        LAYOUT_RES = layoutRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_RES, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(mList.get(position));
        holder.fillView();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // ---

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Valute item;
        private final View view;

        @Bind(R.id.tvName) TextView tvName;
        @Bind(R.id.tvValue) TextView tvValue;

        public ViewHolder(View view)  {
            super(view);

            ButterKnife.bind(this, view);

            this.view = view;
        }

        public void setItem(Valute item) {
            this.item = item;
        }

        public void fillView() {
            tvName.setText(new StringBuffer(item.name)
                    .append('(')
                    .append(item.charCode)
                    .append(')')
                    .toString());
            tvValue.setText(String.valueOf(item.value.d));

        }

    }

}
