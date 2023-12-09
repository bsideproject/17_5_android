package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carpick.carpickapp.databinding.FragmentCarpickQnaBinding
import com.carpick.carpickapp.databinding.FragmentCarpickStartBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.ui.adapter.AnswerAdapter

class CarpickQnAFragment : BaseFragment<FragmentCarpickQnaBinding>() {
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
    }
    private fun initView() {
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

        binding.rvAnswer.adapter = answerAdapter
        binding.roundProgressBar.progress = 1/10
        answerAdapter.submitList(ageModel)
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickQnaBinding {
        return FragmentCarpickQnaBinding.inflate(layoutInflater)
    }
}