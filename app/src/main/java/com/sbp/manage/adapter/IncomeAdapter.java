package com.sbp.manage.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sbp.manage.databinding.IncomeDetailItemBinding;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.EmploymentTimeDto;
import com.sbp.manage.utils.Utility;

public class IncomeAdapter extends
        ListAdapter<EmploymentTimeDto.DayAtCompnany, IncomeAdapter.ViewHolder> {

    private final ContractDto.Contracts mContracts;

    public IncomeAdapter(ContractDto.Contracts contracts) {
        super(new DiffCallback());
        this.mContracts = contracts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                IncomeDetailItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class DiffCallback extends
            DiffUtil.ItemCallback<EmploymentTimeDto.DayAtCompnany> {

        @Override
        public boolean areItemsTheSame(@NonNull EmploymentTimeDto.DayAtCompnany oldItem,
                @NonNull EmploymentTimeDto.DayAtCompnany newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull EmploymentTimeDto.DayAtCompnany oldItem,
                @NonNull EmploymentTimeDto.DayAtCompnany newItem) {
            return false;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final IncomeDetailItemBinding binding;

        public ViewHolder(@NonNull IncomeDetailItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void bind(EmploymentTimeDto.DayAtCompnany income) {
            binding.tvMonth.setText("Tháng " + income.getMonth() + ":");

            double totalSalary = Double.parseDouble(mContracts.getSalary()) + Double.parseDouble(
                    mContracts.getBonusProject());
            binding.tvRealSalary.setText(income.getRealSalary(mContracts.getEmploymentId()));
            binding.tvIncome.setText(Utility.currencyFormat(totalSalary));

            binding.tvCount.setText(income.getDays().size() + " ngày");
            binding.tvProjectBonus.setText(
                    Utility.currencyFormat(Double.parseDouble(mContracts.getBonusProject())));
            binding.tvSocialInsurance.setText(
                    Utility.currencyFormat(Double.parseDouble(mContracts.getSocialInsurance())));
        }
    }
}
