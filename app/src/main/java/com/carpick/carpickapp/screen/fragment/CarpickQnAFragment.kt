package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickQnaBinding
import com.carpick.carpickapp.databinding.FragmentCarpickStartBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener

class CarpickQnAFragment : BaseFragment<FragmentCarpickQnaBinding>() {
    private var nowPage = 1
    private var totalPage = 10 // api 나오면 수정

    private val answerAdapter by lazy {
        AnswerAdapter {
//             item ->
//                val intent = Intent(this@MovieIntroduceActivity, MovieDetailActivity::class.java)
//                intent.putExtra("item", item)
//                startActivity(intent)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }
    private fun initView() {
        binding.run {
            rvAnswer.adapter = answerAdapter
            roundProgressBar.progress = totalPage/nowPage
        }

        val ageModel = ArrayList<TestModel>()
        ageModel.add(TestModel(id= 1, testData = "2500만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "3000만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "4000만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "4500만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "5000만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "5500만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "6000만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "8000만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "1억원 이하"))
        ageModel.add(TestModel(id= 2, testData = "1.5억원 이하"))
        ageModel.add(TestModel(id= 2, testData = "2억원 이하"))
        ageModel.add(TestModel(id= 2, testData = "2억원 초과"))


        answerAdapter.submitList(ageModel)
    }

    private fun initListener() {
        binding.run {
            btnNext.setOnSingleClickListener {
                if (nowPage < totalPage) {
                    nowPage++
                    tvNowQnaPos.text = nowPage.toString()

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    btnPrev.setColorFilter(ContextCompat.getColor(root.context, if (nowPage == 1) R.color.color_b6b6cc else R.color.color_6b96ff))
                }
            }

            btnPrev.setOnSingleClickListener {
                if (nowPage > 1) {
                    nowPage--
                    tvNowQnaPos.text = nowPage.toString()

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    btnPrev.setColorFilter(ContextCompat.getColor(root.context, if (nowPage == 1) R.color.color_b6b6cc else R.color.color_6b96ff))
                }
            }
        }
    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickQnaBinding {
        return FragmentCarpickQnaBinding.inflate(layoutInflater)
    }
}