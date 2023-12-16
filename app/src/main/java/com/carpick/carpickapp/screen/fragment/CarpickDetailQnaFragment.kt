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
import com.carpick.carpickapp.databinding.FragmentCarpickDetailQnaBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.screen.ComposeTestActivity
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

        answerLessAdapter = AnswerLessAdapter()
        binding.rvAnswer.adapter = answerLessAdapter

        if(answerViewModel.lastPage < nowPage) {
            answerViewModel.saveLastPage(nowPage)
        }

        if(page == -1) {
            binding.run {
                apiResponse?.let { apiResponse ->
                    answerLessAdapter?.submitList(apiResponse[nowPage])
                }
                roundProgressBar.progress = nowPage * 100 / totalPage
                tvNowQnaPos.text = "$nowPage "
                answerLessAdapter?.setUiState(answerList, nowPage)
            }
        }else {
            nowPage = page
            answerViewModel.saveLastPage(nowPage)

            apiResponse?.let { apiResponse ->
                answerLessAdapter?.submitList(apiResponse[nowPage])
            }
            binding.roundProgressBar.progress = nowPage * 100 / totalPage
            binding.tvNowQnaPos.text = "$nowPage "
        }

        answerViewModel.answerResult.map {
            answerList.put(it.key, it.value)
        }
        answerViewModel.getBudgetResult()?.let {
            answerList.put(1, it)
        }

        answerViewModel.saveAnswerResult(answerList)
        
        binding.run {
            answerLessAdapter?.setClickListener(object : ClickListener {
                override fun click(item: TestModel) {
                    nowPage++

                    tvNowQnaPos.text = "$nowPage "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    answerList[nowPage-1] = item

                    if(answerViewModel.lastPage < nowPage) {
                        answerViewModel.saveLastPage(nowPage)
                    }

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
                    moveCarPickBudgetFragment()
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

                        if(answerViewModel.lastPage < nowPage) {
                            answerViewModel.saveLastPage(nowPage)
                        }
//                        answerViewModel.saveLastPage(nowPage)

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
                    moveCarPickBudgetFragment()
                }
            }

            titleLayout.icWish.setOnSingleClickListener {
                startComposeActivityForResult()
            }
        }
    }

    private fun moveCarPickBudgetFragment() {
        val newFragment = CarpickBudgetQnaFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
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
    companion object {
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