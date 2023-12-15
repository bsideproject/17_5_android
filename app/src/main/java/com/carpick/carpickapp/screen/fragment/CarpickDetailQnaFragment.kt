package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.databinding.FragmentCarpickDetailQnaBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.ui.CommonDialog
import com.carpick.carpickapp.ui.adapter.AnswerLessAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CarpickDetailQnaFragment : BaseFragment<FragmentCarpickDetailQnaBinding>() {
    private var nowPage = 2
    private var totalPage = 10 // api 나오면 수정
    private var answerLessAdapter: AnswerLessAdapter? = null
    private var testResult = ArrayList<ArrayList<TestModel>>()
    private var testModel1 = ArrayList<TestModel>()
    private var testModel2 = ArrayList<TestModel>()
    private var testModel3 = ArrayList<TestModel>()

    private var answerPage = arrayListOf<Int>()

    private var answerList = HashMap<Int, TestModel>() // request용

    private val answerViewModel : CarpickAnswerViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        answerViewModel.answerResult.map {
            answerList.put(it.key, it.value)
        }
        answerViewModel.getBudgetResult()?.let {
            answerList.put(1, it)
        }

        answerViewModel.saveLastPage(3)
        Log.e("ljy", "answer ${answerViewModel.lastPage}")
        answerViewModel.saveAnswerResult(answerList)

        testModel1.add(TestModel(id=11, testData = "2페이지 밟으면 나가는 빠른 가속!"))
        testModel1.add(TestModel(id=22, testData = "2페이지 팝콘 터지는 배기음!"))

        testModel2.add(TestModel(id=33, testData = "3페이지 시내 주행"))
        testModel2.add(TestModel(id=44, testData = "3페이지 장거리 운행"))

        testModel3.add(TestModel(id=55, testData = "4페이지 첫번째"))
        testModel3.add(TestModel(id=66, testData = "4페이지 두번째"))

        testResult.add(testModel1)
        testResult.add(testModel2)
        testResult.add(testModel3)

        binding.run {
            answerLessAdapter = AnswerLessAdapter()
            rvAnswer.adapter = answerLessAdapter
            answerLessAdapter?.setUiState(answerList, nowPage)
            answerLessAdapter?.submitList(testModel1)

            roundProgressBar.progress = totalPage / nowPage
            tvNowQnaPos.text = "$nowPage "

            answerPage.add(nowPage)
            answerLessAdapter?.setClickListener(object : ClickListener {
                override fun click(item: TestModel) {
                    nowPage++

                    tvNowQnaPos.text = "$nowPage "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    answerList[nowPage-1] = item

                    answerViewModel.saveAnswerResult(answerList)
                    Log.e("ljy", "answer list $answerList")
                    if(nowPage-2 < testResult.size) {
                        answerLessAdapter?.setUiState(answerList, nowPage)
                        answerLessAdapter?.submitList(testResult[nowPage - 2])
                    }
                }
            })
        }
    }

    private fun initListener() {
        binding.run {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                if(nowPage > 2) {
                    nowPage--
                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    tvNowQnaPos.text = "$nowPage "

                    answerLessAdapter?.setUiState(answerList, nowPage)
                    answerLessAdapter?.submitList(testResult[nowPage-2])
                }else {
                    val fragmentManager = requireActivity().supportFragmentManager

                    fragmentManager.popBackStackImmediate()
                }
            }


            btnNext.setOnSingleClickListener {
                if(answerList[nowPage] == null) {
                    CommonDialog
                        .getInstance("테스트", "테스트1", "확인")
                        .show(childFragmentManager, null)
                }else {
                    if (nowPage < totalPage) {
                        nowPage++
                        tvNowQnaPos.text = "$nowPage "

                        val progressBarValue = nowPage * 100 / totalPage
                        roundProgressBar.progress = progressBarValue

                        answerLessAdapter?.setUiState(answerList, nowPage)
                        answerLessAdapter?.submitList(testResult[nowPage - 2])
                    }
                }
            }

            btnPrev.setOnSingleClickListener {
                if (nowPage > 2) {
                    nowPage--
                    tvNowQnaPos.text = "$nowPage "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    answerLessAdapter?.setUiState(answerList, nowPage)
                    answerLessAdapter?.submitList(testResult[nowPage-2])
                }else {
                    val fragmentManager = requireActivity().supportFragmentManager

                    fragmentManager.popBackStackImmediate()
                }
            }
        }
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarpickDetailQnaBinding {
        return FragmentCarpickDetailQnaBinding.inflate(layoutInflater)
    }
}