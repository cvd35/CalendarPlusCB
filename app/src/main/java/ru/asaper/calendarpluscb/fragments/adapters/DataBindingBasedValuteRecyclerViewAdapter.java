package ru.asaper.calendarpluscb.fragments.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.asaper.calendarpluscb.databinding.ItemValuteHorBinding;
import ru.asaper.calendarpluscb.network.models.Valute;

public class DataBindingBasedValuteRecyclerViewAdapter extends RecyclerView.Adapter<DataBindingBasedValuteRecyclerViewAdapter.ItemValuteHorViewHolder> {

    private final List<Valute> mList;

    public DataBindingBasedValuteRecyclerViewAdapter(List<Valute> list) {
        this.mList = list;
    }

    @Override
    public ItemValuteHorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ItemValuteHorBinding itemValuteHorBinding = ItemValuteHorBinding.inflate(layoutInflater, parent, false);

        return new ItemValuteHorViewHolder(itemValuteHorBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(ItemValuteHorViewHolder holder, int position) {
        Valute valute = mList.get(position);

        holder.binding.setValute(valute);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // ---

    public class ItemValuteHorViewHolder extends RecyclerView.ViewHolder {
        ItemValuteHorBinding binding;

        public ItemValuteHorViewHolder(View view)  {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }

}
