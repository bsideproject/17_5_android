package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentUserInfoQnaBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener

class UserInfoQnAFragment : BaseFragment<FragmentUserInfoQnaBinding>(){
    private var sex = ""
    private var age = ""

    private val answerAdapter by lazy {
        AnswerAdapter  { item ->
            age = item.testData
            if(sex != "" && age != "") {
                val newFragment = CarpickQnAFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }
    private fun initView() {
        val ageModel = ArrayList<TestModel>()
        ageModel.add(TestModel(id= 1, testData = "18~24세"))
        ageModel.add(TestModel(id= 2, testData = "25~29세"))
        ageModel.add(TestModel(id= 3, testData = "30~34세"))
        ageModel.add(TestModel(id= 4, testData = "35~39세"))
        ageModel.add(TestModel(id= 5, testData = "40~44세"))
        ageModel.add(TestModel(id= 5, testData = "45~49세"))
        ageModel.add(TestModel(id= 6, testData = "50~54세"))
        ageModel.add(TestModel(id= 7, testData = "55~59세"))
        ageModel.add(TestModel(id= 8, testData = "60~64세"))
        ageModel.add(TestModel(id= 9, testData = "65~69세"))
        ageModel.add(TestModel(id= 10, testData = "70세 이상"))


        binding.rvAge.adapter = answerAdapter

        answerAdapter.submitList(ageModel)
    }
    private fun initListener() {
        binding.run {
            val sexTextviewGroup = arrayListOf(tvMan, tvGirl)

            tvMan.setOnSingleClickListener {
                setClickStatus(sexTextviewGroup,tvMan)
                sex = "남자"

                if(sex != "" && age != "") {
                    val newFragment = CarpickQnAFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }

            }
            tvGirl.setOnSingleClickListener {
                setClickStatus(sexTextviewGroup,tvGirl)
                sex = "여자"

                if(sex != "" && age != "") {
                    val newFragment = CarpickQnAFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
        }
    }

    private fun setClickStatus(textviewGroup: ArrayList<AppCompatTextView>, clickTexView : AppCompatTextView) {
        for(view in textviewGroup) {
            view.setBackgroundColor(0)
            view.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_36364d))
        }

        clickTexView.setBackgroundResource(R.drawable.bg_round_3872ff)
        clickTexView.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentUserInfoQnaBinding {
        return FragmentUserInfoQnaBinding.inflate(layoutInflater)
    }
}