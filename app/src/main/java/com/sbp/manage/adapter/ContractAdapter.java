package com.sbp.manage.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sbp.manage.databinding.ContractItemBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.ui.ManageApplication;
import com.sbp.manage.ui.UpdateContractActivity;
import com.sbp.manage.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractAdapter extends
        ListAdapter<ContractDto.Contracts, ContractAdapter.ViewHolder> {

    private Context mContext;

    public ContractAdapter(Context context) {
        super(new DiffCallback());
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ContractItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<ContractDto.Contracts> {

        @Override
        public boolean areItemsTheSame(@NonNull ContractDto.Contracts oldItem,
                @NonNull ContractDto.Contracts newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ContractDto.Contracts oldItem,
                @NonNull ContractDto.Contracts newItem) {
            return false;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ContractItemBinding binding;

        public ViewHolder(@NonNull ContractItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitClient.getInstance()
                            .getApiClient()
                            .deleteContract(getItem(getAdapterPosition()).get_id())
                            .enqueue(new Callback<BaseDto>() {
                                @Override
                                public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                    if (response.body() != null) {
                                        if (response.body().getSuccess()) {
                                            Toast.makeText(mContext,
                                                    "Xoá hợp đồng thành công!",
                                                    Toast.LENGTH_SHORT).show();
                                            ManageApplication.sContractList.remove(
                                                    getItem(getAdapterPosition()));
                                            submitList(null);
                                            submitList(ManageApplication.sContractList);
                                        } else {
                                            Toast.makeText(mContext,
                                                    "Xoá hợp đồng không thành công, thử "
                                                            + "lại sau!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(mContext,
                                                "Xoá hợp đồng không thành công, thử lại "
                                                        + "sau!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<BaseDto> call, Throwable t) {
                                    Log.e("CheckApp", t.toString());
                                    Toast.makeText(mContext,
                                            "Xoá hợp đồng không thành công, thử lại sau!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });

            binding.imvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UpdateContractActivity.class);
                    intent.putExtra("data", getItem(getAdapterPosition()));
                    mContext.startActivity(intent);
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(ContractDto.Contracts contracts) {

            binding.tvTime.setText(contracts.getCreateAt());
            binding.tvTitle.setText(contracts.getContent());
            binding.tvDescription.setText(contracts.getDescriptionWork());
            binding.tvProjectBonus.setText(
                    Utility.currencyFormat(Double.parseDouble(contracts.getBonusProject())));
            binding.tvSalary.setText(
                    Utility.currencyFormat(Double.parseDouble(contracts.getSalary())));
            binding.tvSocialInsurance.setText(
                    Utility.currencyFormat(Double.parseDouble(contracts.getSocialInsurance())));

            String name = "";
            for (EmploymentDto.Employment employment : ManageApplication.sEmploymentList) {
                if (employment.get_id().equals(contracts.getEmploymentId())) {
                    name = employment.getName();
                    break;
                }
            }
            binding.tvName.setText(name);

        }
    }
}
