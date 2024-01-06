package com.carpick.carpickapp.screen.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickDetailQnaBinding
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.screen.activity.LoadingActivity
import com.carpick.carpickapp.ui.adapter.AnswerLessAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CarPickDetailQnaFragment : BaseFragment<FragmentCarpickDetailQnaBinding>() {
    private var nowPage = 3
    private var totalPage = 12
    private var answerLessAdapter: AnswerLessAdapter? = null

    private var answerList = HashMap<Int, Choice>() // request용

    private val answerViewModel: CarpickAnswerViewModel by activityViewModels()
    private var apiResponse: ArrayList<QnAListResponseModel>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        val page = arguments?.getInt("page") ?: -1

        apiResponse = answerViewModel.apiResponse
        totalPage = answerViewModel.apiResponse.size

        binding?.run {
            tvQnaTitle.text = apiResponse?.get(nowPage)?.questionName
            titleLayout.clWish.isVisible = false
            tvTotalQnaPos.text = "/ ${answerViewModel.apiResponse.size}"

            answerLessAdapter = AnswerLessAdapter()
            rvAnswer.adapter = answerLessAdapter

            // page == -1은 스텝 건너뛰고 온게 아닌, 정상 플로우로 들어온 경우
            if (page == -1) {
                if (answerViewModel.lastPage < nowPage) {
                    answerViewModel.saveLastPage(nowPage)
                }

                apiResponse?.let { apiResponse ->
                    answerLessAdapter?.submitList(apiResponse[nowPage].choices)
                }
                roundProgressBar.progress = (nowPage + 1) * 100 / totalPage
                tvNowQnaPos.text = "${nowPage + 1} "
                answerLessAdapter?.setUiState(answerList, nowPage)
            } else {
                nowPage = page
                if (answerViewModel.lastPage < nowPage) {
                    answerViewModel.saveLastPage(nowPage)
                }

                apiResponse?.let { apiResponse ->
                    answerLessAdapter?.submitList(apiResponse[nowPage].choices)
                }
                roundProgressBar.progress = (nowPage) * 100 / totalPage
                tvNowQnaPos.text = "${nowPage + 1} "
                answerLessAdapter?.setUiState(answerList, nowPage)
            }

            answerViewModel.answerResult.map {
                answerList.put(it.key, it.value)
            }

            answerViewModel.getGenderResult()?.let {
                answerList.put(0, it)
            }
            answerViewModel.getAgeResult()?.let {
                answerList.put(1, it)
            }
            answerViewModel.getBudgetResult()?.let {
                answerList.put(2, it)
            }

            answerViewModel.saveAnswerResult(answerList)

            binding.run {
                answerLessAdapter?.setClickListener(object : ClickListener {
                    override fun click(item: Choice) {
                        if (nowPage < totalPage) {
                            nowPage++
                            if (nowPage < 12) {
                                tvNowQnaPos.text = "${nowPage + 1} "
                            }
                            clNoAnswer.isVisible = false

                            val progressBarValue = (nowPage + 1) * 100 / totalPage
                            roundProgressBar.progress = progressBarValue

                            answerList[nowPage - 1] = item

                            if (answerViewModel.lastPage < nowPage) {
                                answerViewModel.saveLastPage(nowPage)
                            }

                            answerViewModel.saveAnswerResult(answerList)
                            Log.e("ljy", "answer list $answerList")
                            apiResponse?.let { apiResponse ->
                                /* nowPage - 2인이유는 어댑터가 여기부터 새로운걸 쓰고 있음
                        *  hashmap을 사용중인데 hashmap을 이 페이지부터 저장하기에 앞에 성별, 연봉페이지를 빼야되서 -2
                        * */

                                if (nowPage - 3 < apiResponse.size - 3) {
                                    tvQnaTitle.text = apiResponse[nowPage].questionName
                                    answerLessAdapter?.setUiState(answerList, nowPage)
                                    answerLessAdapter?.submitList(apiResponse[nowPage].choices)
                                } else {
                                    val answerList = ArrayList<String>()
                                    answerViewModel.answerResult.map {
                                        answerList.add(it.value.choiceCode)
                                    }

                                    lifecycleScope.launch {
                                        Log.e("ljy", "request list ${answerList.toList()}")
                                        answerViewModel.getRecommendCars(answerList.toList())
                                            .collect {
                                                val intent = Intent(
                                                    root.context,
                                                    LoadingActivity::class.java
                                                )
                                                intent.putExtra("response", it ?: RecommendCars(emptyList()))
                                                startActivity(intent)
                                            }
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    private fun initListener() {
        binding?.run {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                if (nowPage > 3) {
                    nowPage--
                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    tvNowQnaPos.text = "${nowPage + 1}"

                    answerLessAdapter?.setUiState(answerList, nowPage)
                    apiResponse?.let { apiResponse ->
                        answerLessAdapter?.submitList(apiResponse[nowPage].choices)
                    }
                } else {
                    moveCarPickBudgetFragment()
                }
            }


            btnNext.setOnSingleClickListener {
                if (answerList[nowPage] == null) {
                    clNoAnswer.isVisible = true
                } else {
                    if (nowPage < totalPage) {
                        nowPage++
                        tvNowQnaPos.text = "${nowPage + 1} "

                        if (answerViewModel.lastPage < nowPage) {
                            answerViewModel.saveLastPage(nowPage)
                        }

                        val progressBarValue = nowPage * 100 / totalPage
                        roundProgressBar.progress = progressBarValue

                        answerLessAdapter?.setUiState(answerList, nowPage)
                        answerLessAdapter?.submitList(apiResponse?.get(nowPage)?.choices)
                    }
                }
            }

            btnPrev.setOnSingleClickListener {
                if (nowPage > 3) {
                    nowPage--
                    tvNowQnaPos.text = "${nowPage + 1} "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    answerLessAdapter?.setUiState(answerList, nowPage)
                    answerLessAdapter?.submitList(apiResponse?.get(nowPage)?.choices)
                } else {
                    moveCarPickBudgetFragment()
                }
            }

            ivClose.setOnSingleClickListener {
                clNoAnswer.isVisible = false
            }
        }
    }

    private fun moveCarPickBudgetFragment() {
        val newFragment = CarPickBudgetQnaFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        fun getInstance(page: Int): CarPickDetailQnaFragment {
            return CarPickDetailQnaFragment().apply {
                arguments = Bundle().apply {
                    putInt("page", page)
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
