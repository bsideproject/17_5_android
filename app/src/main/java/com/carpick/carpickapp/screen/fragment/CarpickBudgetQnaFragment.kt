package com.carpick.carpickapp.screen.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickBudgetQnaBinding
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.screen.ComposeTestActivity
import com.carpick.carpickapp.ui.CommonDialog
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarpickBudgetQnaFragment : BaseFragment<FragmentCarpickBudgetQnaBinding>() {
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

        answerAdapter?.submitList(answerViewModel.apiResponse[1].choices)

        answerViewModel.getBudgetResult()?.let {
            selectAnswer = it.content
            answerAdapter?.setSelectedItem(it)
        }

        answerAdapter?.setClickListener(object : ClickListener {
            override fun click(item: Choice) {
                selectAnswer = item.content

                binding.clNoAnswer.isVisible = false

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
                if(selectAnswer != "") {
                    val newFragment = CarpickDetailQnaFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }else {
                    clNoAnswer.isVisible = true
                }
            }

            ivClose.setOnSingleClickListener {
                clNoAnswer.isVisible = false
            }
            titleLayout.icWish.setOnSingleClickListener {
                startComposeActivityForResult()
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                val newFragment = UserInfoQnAFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    private val myActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val resultValue = data?.getIntExtra("page",1) ?: 1

                val newFragment = CarpickDetailQnaFragment.getInstance(resultValue)
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

    private fun startComposeActivityForResult() {
        val intent = Intent(requireContext(), ComposeTestActivity::class.java)

        intent.putExtra("page", answerViewModel.lastPage)

        myActivityResultLauncher.launch(intent)
    }


    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickBudgetQnaBinding {
        return FragmentCarpickBudgetQnaBinding.inflate(layoutInflater)
    }
}