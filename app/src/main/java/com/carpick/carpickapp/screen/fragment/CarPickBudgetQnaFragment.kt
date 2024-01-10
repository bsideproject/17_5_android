package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickBudgetQnaBinding
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarPickAnswerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarPickBudgetQnaFragment : BaseFragment<FragmentCarpickBudgetQnaBinding>() {
    private var nowPage = 2
    private var totalPage = 12
    private var answerAdapter : AnswerAdapter? = null
    private var selectAnswer = ""

    private val answerViewModel : CarPickAnswerViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }
    private fun initView() {
        totalPage = answerViewModel.apiResponse.size

        binding?.run {
            tvQnaTitle.text = answerViewModel.apiResponse[2].questionName
            titleLayout.clWish.isVisible = false
            tvTotalQnaPos.text = "/ $totalPage"

            if(answerViewModel.lastPage <= nowPage) {
                answerViewModel.saveLastPage(nowPage)
            }

            answerAdapter = AnswerAdapter()
            rvAnswer.adapter = answerAdapter
            roundProgressBar.progress = nowPage * 100 / totalPage
        }

        answerAdapter?.submitList(answerViewModel.apiResponse[nowPage].choices)

        answerViewModel.getBudgetResult()?.let {
            selectAnswer = it.content
            answerAdapter?.setSelectedItem(it)
        }

        answerAdapter?.setClickListener(object : ClickListener {
            override fun click(item: Choice) {
                selectAnswer = item.content

                binding?.clNoAnswer?.isVisible = false

                answerViewModel.saveBudgetResult(item)

                val newFragment = CarPickDetailQnaFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
    }

    private fun initListener() {
        binding?.run {
            btnNext.setOnSingleClickListener {
                if(selectAnswer != "") {
                    val newFragment = CarPickDetailQnaFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }else {
                    clNoAnswer.isVisible = true
                }
            }

            btnPrev.setOnSingleClickListener {
                val newFragment = AgeFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            ivClose.setOnSingleClickListener {
                clNoAnswer.isVisible = false
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                val newFragment = AgeFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickBudgetQnaBinding {
        return FragmentCarpickBudgetQnaBinding.inflate(layoutInflater)
    }
}