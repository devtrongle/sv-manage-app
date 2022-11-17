package com.sbp.manage.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sbp.manage.databinding.EmploymentItemBinding;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.dto.EmploymentTimeDto;
import com.sbp.manage.ui.ManageApplication;

public class EmploymentAdapter extends
        ListAdapter<EmploymentDto.Employment, EmploymentAdapter.ViewHolder> {

    private IOnClick mIOnClick;

    public void setOnClick(IOnClick IOnClick) {
        mIOnClick = IOnClick;
    }

    public EmploymentAdapter() {
        super(new DiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                EmploymentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<EmploymentDto.Employment> {

        @Override
        public boolean areItemsTheSame(@NonNull EmploymentDto.Employment oldItem,
                @NonNull EmploymentDto.Employment newItem) {
            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull EmploymentDto.Employment oldItem,
                @NonNull EmploymentDto.Employment newItem) {
            return true;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final EmploymentItemBinding binding;

        public ViewHolder(@NonNull EmploymentItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(v -> {
                if (mIOnClick != null) {
                    mIOnClick.onClick(getAdapterPosition(), getItem(getAdapterPosition()));
                }
            });

            binding.imvEdit.setOnClickListener(v -> {
                if (mIOnClick != null) {
                    mIOnClick.onLongClick(getAdapterPosition(), getItem(getAdapterPosition()));
                }
            });
        }

        public void bind(EmploymentDto.Employment employment) {
            binding.tvName.setText(employment.getName());
            binding.tvBirthday.setText(employment.getBirthday());
            binding.tvGender.setText(employment.isSex() ? "Nam" : "Nữ");
            EmploymentTimeDto.ListEmployment listEmployment = null;
            for (EmploymentTimeDto.ListEmployment l : ManageApplication.sEmploymentTime) {
                if (l.getIdEmployment().equals(employment.get_id())) {
                    listEmployment = l;
                }
            }
            if (listEmployment != null) {
                EmploymentTimeDto.DayAtCompnany latestDayAtCompany =
                        listEmployment.getDayAtCompany().get(
                                listEmployment.getDayAtCompany().size() - 1);
                binding.tvSalaryMonth.setText(
                        latestDayAtCompany.getRealSalary(employment.get_id()));
                binding.tvSalaryYear.setText(listEmployment.getRealSalaryYear());
            }
        }
    }

    public interface IOnClick {
        void onClick(int position, EmploymentDto.Employment employment);

        void onLongClick(int position, EmploymentDto.Employment employment);
    }
}
