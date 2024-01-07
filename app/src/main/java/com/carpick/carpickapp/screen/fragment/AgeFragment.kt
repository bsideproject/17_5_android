package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentAgeBinding
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.screen.activity.MainActivity
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel

class AgeFragment : BaseFragment<FragmentAgeBinding>() {
    private var nowPage = 1
    private var totalPage = 12
    private var answerAdapter: AnswerAdapter? = null
    private var selectAnswer = ""

    private val answerViewModel: CarpickAnswerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        binding?.run {
            totalPage = answerViewModel.apiResponse.size
            tvQnaTitle.text = answerViewModel.apiResponse[nowPage].questionName
            titleLayout.clWish.isVisible = false
            tvTotalQnaPos.text = "/ ${answerViewModel.apiResponse.size}"

            if (answerViewModel.lastPage <= 1) {
                answerViewModel.saveLastPage(1)
            }

            answerAdapter = AnswerAdapter()
            rvAnswer.adapter = answerAdapter
            roundProgressBar.progress = nowPage * 100 / totalPage

            answerAdapter?.submitList(answerViewModel.apiResponse[nowPage].choices)

            answerViewModel.getAgeResult()?.let {
                selectAnswer = it.content
                answerAdapter?.setSelectedItem(it)
            }

            answerAdapter?.setClickListener(object : ClickListener {
                override fun click(item: Choice) {
                    selectAnswer = item.content

                    clNoAnswer.isVisible = false

                    answerViewModel.saveAgeResult(item)


                    val newFragment = CarPickBudgetQnaFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            })
        }
    }

    private fun initListener() {
        binding?.run {
            btnNext.setOnSingleClickListener {
                if (selectAnswer != "") {
                    val newFragment = CarPickBudgetQnaFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                } else {
                    clNoAnswer.isVisible = true
                }
            }

            btnPrev.setOnSingleClickListener {
                val newFragment = GenderFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            ivClose.setOnSingleClickListener {
                clNoAnswer.isVisible = false
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                val newFragment = GenderFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAgeBinding {
        return FragmentAgeBinding.inflate(layoutInflater)
    }
}