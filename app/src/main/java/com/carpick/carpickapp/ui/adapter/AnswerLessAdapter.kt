package com.carpick.carpickapp.ui.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ItemLessAnswerBinding
import com.carpick.carpickapp.model.TestModel

class AnswerLessAdapter : ListAdapter<TestModel, AnswerLessAdapter.AnswerViewHolder>(
    object : DiffUtil.ItemCallback<TestModel>() {
        override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    private var listener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            ItemLessAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    private var answerPage = arrayListOf<Int>()
    private var nowPage = -1
    private var pairTest = ArrayList<Pair<Int, Int>>()

    private var hashMapTest = HashMap<Int, Int>()

    fun pairTest(pairTest : ArrayList<Pair<Int,Int>>, nowPage: Int) {
        this.pairTest = pairTest
        this.nowPage = nowPage
    }

    fun hashMapTest(hashmap : HashMap<Int,Int>, nowPage: Int) {
        this.hashMapTest = hashmap
        this.nowPage = nowPage
    }


    fun setClickListener(param: ClickListener) {
        listener = param
    }

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class AnswerViewHolder(
        private val binding: ItemLessAnswerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var item: TestModel? = null

        init {
            binding.root.setOnClickListener {
                val clickedPosition = adapterPosition

                if (selectedPosition != RecyclerView.NO_POSITION) {

                    notifyItemChanged(selectedPosition)
                }

                binding.clAnswerList.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.color_eaf0ff))
                binding.tvAnswer.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_84abff))

                // 선택된 항목의 위치 갱신
                selectedPosition = clickedPosition

                item?.let { listener?.click(it) }
            }
        }

        fun bind(item: TestModel) {
            this.item = item

            binding.tvAnswer.text = item.testData

            binding.clAnswerList.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.color_d1deff))
            binding.tvAnswer.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_3872ff))

            if(hashMapTest.size > 0) {
                Log.e(
                    "ljy",
                    "pair $hashMapTest nowpage $nowPage second ${hashMapTest[nowPage]} ${item.id}"
                )
                if (hashMapTest[nowPage] == item.id) {
                    binding.clAnswerList.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.color_eaf0ff
                        )
                    )
                    binding.tvAnswer.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.color_84abff
                        )
                    )
                } else {
                    binding.clAnswerList.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.color_d1deff
                        )
                    )
                    binding.tvAnswer.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.color_3872ff
                        )
                    )
                }
            }
        }
    }
}