package com.company.templateapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.company.templateapplication.R;
import com.company.templateapplication.entity.Dummy;

public class DummyAdapter extends PagedListAdapter<Dummy, DummyAdapter.DummyHolder> {
    OnItemClickListener onItemClickListener;

    public static final DiffUtil.ItemCallback<Dummy> DIFF_CALLBACK = new DiffUtil.ItemCallback<Dummy>() {
        @Override
        public boolean areItemsTheSame(@NonNull Dummy oldItem, @NonNull Dummy newItem) {
            return newItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dummy oldItem, @NonNull Dummy newItem) {
            return newItem.getName().equals(oldItem.getName()) &&
                    newItem.getPriority().equals(oldItem.getPriority());
        }
    };

    public DummyAdapter() {
        super(DIFF_CALLBACK);
    }

    public interface OnItemClickListener {
        void onItemClick(Dummy dummy);
    }

    public void setOnItemClickListerne(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    class DummyHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView priority;

        public DummyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            priority = itemView.findViewById(R.id.item_priority);
            name.setSelected(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public DummyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dummy, parent, false);
        return new DummyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DummyHolder holder, int position) {
        Dummy dummy = getItem(position);
        if (dummy != null) {
            holder.name.setText(dummy.getName());
            holder.priority.setText(String.valueOf(dummy.getPriority()));
        }
    }


    public Dummy getDummy(int position) {
        return getItem(position);
    }
}

