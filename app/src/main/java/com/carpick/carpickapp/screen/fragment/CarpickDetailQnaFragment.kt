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
import com.carpick.carpickapp.R
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

    private var answerPage = arrayListOf<Int>()

    private var answerList = HashMap<Int, TestModel>() // request용

    private val answerViewModel : CarpickAnswerViewModel by activityViewModels()
    private var apiResponse : ArrayList<ArrayList<TestModel>>?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        val page = arguments?.getInt("page") ?: -1

        apiResponse = answerViewModel.apiResponse

        answerViewModel.answerResult.map {
            answerList.put(it.key, it.value)
        }
        answerViewModel.getBudgetResult()?.let {
            answerList.put(1, it)
        }

        answerViewModel.saveLastPage(3)
        answerViewModel.saveAnswerResult(answerList)

        binding.run {
            answerLessAdapter = AnswerLessAdapter()
            rvAnswer.adapter = answerLessAdapter

            apiResponse?.let { apiResponse ->
                answerLessAdapter?.submitList(apiResponse[nowPage])
            }
            roundProgressBar.progress = nowPage * 100 / totalPage
            tvNowQnaPos.text = "$nowPage "
            answerPage.add(nowPage)
            answerLessAdapter?.setUiState(answerList, nowPage)

//            if(page != -1) {
//                apiResponse?.let { apiResponse ->
//                    answerLessAdapter?.submitList(apiResponse[nowPage])
//                    roundProgressBar.progress = totalPage / nowPage
//                    tvNowQnaPos.text = "$nowPage "
//                    answerPage.add(nowPage)
//                }
//            }else {
//                answerLessAdapter?.setUiState(answerList, nowPage)
//                answerLessAdapter?.submitList(testModel1)
//
//                roundProgressBar.progress = totalPage / nowPage
//                tvNowQnaPos.text = "$nowPage "
//                answerPage.add(nowPage)
//            }



            answerLessAdapter?.setClickListener(object : ClickListener {
                override fun click(item: TestModel) {
                    nowPage++

                    tvNowQnaPos.text = "$nowPage "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    answerList[nowPage-1] = item
                    Log.e("ljy", "adapter $nowPage")
                    answerViewModel.saveLastPage(nowPage)

                    answerViewModel.saveAnswerResult(answerList)
                    Log.e("ljy", "answer list $answerList")
                    apiResponse?.let { apiResponse ->
                        /* nowPage - 2인이유는 어댑터가 여기부터 새로운걸 쓰고 있음
                        *  hashmap을 사용중인데 hashmap을 이 페이지부터 저장하기에 앞에 성별, 연봉페이지를 빼야되서 -2
                        * */

                        if (nowPage - 2 < apiResponse.size - 2) {
                            answerLessAdapter?.setUiState(answerList, nowPage)
                            answerLessAdapter?.submitList(apiResponse[nowPage])
                        }else {
                            val newFragment = LoadingFragment()
                            val transaction = parentFragmentManager.beginTransaction()
                            transaction.replace(R.id.nav_host, newFragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
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
                    apiResponse?.let { apiResponse ->
                        answerLessAdapter?.submitList(apiResponse[nowPage])
                    }
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

                        answerViewModel.saveLastPage(nowPage)

                        val progressBarValue = nowPage * 100 / totalPage
                        roundProgressBar.progress = progressBarValue

                        answerLessAdapter?.setUiState(answerList, nowPage)
                        answerLessAdapter?.submitList(apiResponse?.get(nowPage))
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
                    answerLessAdapter?.submitList(apiResponse?.get(nowPage))
                }else {
                    val fragmentManager = requireActivity().supportFragmentManager

                    fragmentManager.popBackStackImmediate()
                }
            }
        }
    }
    companion object {
        @JvmStatic
        fun getInstance(page : Int) : CarpickDetailQnaFragment {
            return CarpickDetailQnaFragment().apply {
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