package com.carpick.carpickapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ItemAnswerBinding
import com.carpick.carpickapp.model.TestModel

class AnswerAdapter : ListAdapter<TestModel, AnswerAdapter.AnswerViewHolder>(
    object: DiffUtil.ItemCallback<TestModel>() {
        override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var listener : ClickListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    private var selectedPosition = RecyclerView.NO_POSITION
    inner class AnswerViewHolder(
        private val binding : ItemAnswerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var item : TestModel? = null

        init {
            binding.root.setOnClickListener {
                val clickedPosition = adapterPosition

                if (selectedPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selectedPosition)
                }

                binding.tvAnswer.setBackgroundResource(R.drawable.bg_round_3872ff)
                binding.tvAnswer.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))

                // 선택된 항목의 위치 갱신
                selectedPosition = clickedPosition

                item?.let { listener?.click(it) }
            }
        }

        fun bind(item : TestModel) {
            this.item = item

            binding.tvAnswer.text = item.testData

            if (adapterPosition == selectedPosition) {
                binding.tvAnswer.setBackgroundResource(R.drawable.bg_round_3872ff)
                binding.tvAnswer.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            } else {
                binding.tvAnswer.setBackgroundColor(0)
                binding.tvAnswer.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_36364d))
            }
        }
    }
}