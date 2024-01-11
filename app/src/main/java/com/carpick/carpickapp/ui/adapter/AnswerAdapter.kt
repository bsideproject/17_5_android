package com.carpick.carpickapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ItemAnswerBinding
import com.carpick.carpickapp.model.Choice

class AnswerAdapter(
    private val itemList: List<Choice>
) : ListAdapter<Choice, AnswerAdapter.AnswerViewHolder>(
    object : DiffUtil.ItemCallback<Choice>() {
        override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var listener: ClickListener? = null
    private var selectedItem: Choice? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position), selectedItem)
    }

    override fun getItemCount(): Int {
        return itemList.size / 2
    }

    fun setSelectedItem(selected: Choice) {
        selectedItem = selected
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    inner class AnswerViewHolder(
        private val binding: ItemAnswerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var item: Choice? = null

        private fun handleItemClick(clickedView: TextView) {
            clickedView.setBackgroundResource(R.drawable.bg_round_3872ff)
            clickedView.setTextColor(ContextCompat.getColor(binding.root.context,R.color.white))

            val clickedPosition: Int = if (clickedView == binding.tvAnswer1) {
                adapterPosition * 2
            } else {
                adapterPosition * 2 + 1
            }

            listener?.click(itemList[clickedPosition])
        }

        fun bind(item: Choice, selectedItem: Choice?) {
            this.item = item

            binding.tvAnswer1.setOnClickListener {
                handleItemClick(binding.tvAnswer1)
            }

            binding.tvAnswer2.setOnClickListener {
                handleItemClick(binding.tvAnswer2)
            }

            val position = adapterPosition

            val item1 = itemList.getOrNull(position * 2)
            val item2 = itemList.getOrNull(position * 2 + 1)

            binding.tvAnswer1.text = item1?.content ?: ""
            item2?.content?.let {
                binding.tvAnswer2.text = it
            } ?: run {
                binding.tvAnswer2.visibility = View.INVISIBLE
            }

            updateItemView(binding.tvAnswer1, item1, selectedItem)
            updateItemView(binding.tvAnswer2, item2, selectedItem)
        }

        private fun updateItemView(textView: TextView, item: Choice?, selectedItem: Choice?) {
            if (item != null) {
                textView.setBackgroundResource(
                    if (item == selectedItem) R.drawable.bg_round_3872ff
                    else R.drawable.bg_round_f2f2f6
                )

                textView.setTextColor(
                    ContextCompat.getColor(binding.root.context,
                        if (item == selectedItem) R.color.white
                        else R.color.color_36364d
                    )
                )
            }
        }
    }
}