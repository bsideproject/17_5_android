package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickQnaBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.ui.adapter.AnswerLessAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarpickBudgetQnaFragment : BaseFragment<FragmentCarpickQnaBinding>() {
    private var nowPage = 1
    private var totalPage = 10 // api 나오면 수정
    private var answerAdapter : AnswerAdapter? = null
    private var selectAnswer = ""

    private val answerViewModel : CarpickAnswerViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }
    private fun initView() {
        if(answerViewModel.lastPage <= 2) {
            answerViewModel.saveLastPage(2)
        }

        binding.run {
            answerAdapter = AnswerAdapter()
            rvAnswer.adapter = answerAdapter
            roundProgressBar.progress = totalPage/nowPage
        }

        answerAdapter?.submitList(answerViewModel.apiResponse[1])

        answerAdapter?.setClickListener(object : ClickListener {
            override fun click(item: TestModel) {
                selectAnswer = item.testData

                answerViewModel.saveBudgetResult(item)

                val newFragment = CarpickDetailQnaFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
    }

    private fun initListener() {
        binding.run {
            btnNext.setOnSingleClickListener {
                // 답변 선택안하고 클릭시 토스트 띄워야되는거로 앎, 기획서 봐야됨
                if(selectAnswer != "") {
                    val newFragment = CarpickDetailQnaFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }else {

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        answerViewModel.getBudgetResult()?.let {
            answerAdapter?.setSelectedItem(it)
        }
    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickQnaBinding {
        return FragmentCarpickQnaBinding.inflate(layoutInflater)
    }
}