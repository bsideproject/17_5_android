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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickQnaBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.screen.ComposeTestActivity
import com.carpick.carpickapp.ui.CommonDialog
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

        answerViewModel.getBudgetResult()?.let {
            selectAnswer = it.testData
            answerAdapter?.setSelectedItem(it)
        }

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
                    CommonDialog
                        .getInstance("테스트", "테스트1", "확인")
                        .show(childFragmentManager, null)
                }
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


    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickQnaBinding {
        return FragmentCarpickQnaBinding.inflate(layoutInflater)
    }
}