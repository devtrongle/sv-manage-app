package com.sbp.manage.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbp.manage.R;
import com.sbp.manage.network.dto.ContractDto;

import java.util.ArrayList;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ContractDto.Contracts> list = new ArrayList<>();

    public ContractAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contract_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContractDto.Contracts data = list.get(position);
        holder.getTvId().setText(data.get_id());
        holder.getTvContent().setText(data.getContent());
        holder.getTvCreateAt().setText(data.getCreateAt());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvId;
        private final TextView tvContent;
        private final TextView tvCreateAt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.idContent);
            this.tvContent = itemView.findViewById(R.id.content);
            this.tvCreateAt = itemView.findViewById(R.id.time);
        }

        public TextView getTvId() {
            return tvId;
        }

        public TextView getTvContent() {
            return tvContent;
        }

        public TextView getTvCreateAt() {
            return tvCreateAt;
        }
    }

    public void setData(ArrayList<ContractDto.Contracts> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }
}
