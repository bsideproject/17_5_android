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
import com.carpick.carpickapp.model.Choice

class AnswerAdapter : ListAdapter<Choice, AnswerAdapter.AnswerViewHolder>(
    object: DiffUtil.ItemCallback<Choice>() {
        override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var listener : ClickListener?= null
    private var selectedItem : Choice?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    fun setSelectedItem(selected : Choice) {
        selectedItem = selected
    }
    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    private var selectedPosition = RecyclerView.NO_POSITION
    inner class AnswerViewHolder(
        private val binding : ItemAnswerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var item : Choice? = null


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


        fun bind(item : Choice) {
            this.item = item

            binding.tvAnswer.text = item.content

            binding.tvAnswer.setBackgroundColor(0)
            binding.tvAnswer.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.color_36364d
                )
            )

            selectedItem?.let { selectedItem ->
                if(item.choiceCode == selectedItem.choiceCode) {
                    binding.tvAnswer.setBackgroundResource(R.drawable.bg_round_3872ff)
                    binding.tvAnswer.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                }
            } ?: run {
                if (adapterPosition == selectedPosition) {
                    binding.tvAnswer.setBackgroundResource(R.drawable.bg_round_3872ff)
                    binding.tvAnswer.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                } else {
                    binding.tvAnswer.setBackgroundColor(0)
                    binding.tvAnswer.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.color_36364d
                        )
                    )
                }
            }
        }
    }
}