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
        binding.run {
            answerAdapter = AnswerAdapter()
            rvAnswer.adapter = answerAdapter
            roundProgressBar.progress = totalPage/nowPage
        }
        val ageModel = ArrayList<TestModel>()

        ageModel.add(TestModel(id= 1, testData = "2500만원 이하"))
        ageModel.add(TestModel(id= 2, testData = "3000만원 이하"))
        ageModel.add(TestModel(id= 3, testData = "4000만원 이하"))
        ageModel.add(TestModel(id= 4, testData = "4500만원 이하"))
        ageModel.add(TestModel(id= 5, testData = "5000만원 이하"))
        ageModel.add(TestModel(id= 6, testData = "5500만원 이하"))
        ageModel.add(TestModel(id= 7, testData = "6000만원 이하"))
        ageModel.add(TestModel(id= 8, testData = "8000만원 이하"))
        ageModel.add(TestModel(id= 9, testData = "1억원 이하"))
        ageModel.add(TestModel(id= 10, testData = "1.5억원 이하"))
        ageModel.add(TestModel(id= 11, testData = "2억원 이하"))
        ageModel.add(TestModel(id= 12, testData = "2억원 초과"))


        answerAdapter?.submitList(ageModel)

        answerAdapter?.setClickListener(object : ClickListener {
            override fun click(item: TestModel) {
                //viewmodel에 데이터저장해야됨
                selectAnswer = item.testData

                Log.e("ljy", "연봉 $item")
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
            /*
            케이스
            공통
            api response가 페이지마다 내려오는 경우 그때마다 api호출하면 됨, 뒤로가기시에도 써야됨
            -> 이경우엔 서버가 그만큼 api 호출하니 비용이 더 까이지않나?

            질문갯수가 고정인 경우 ->
            fragment를 그만큼 만들고 api response가 한번에 다 내려오는 경우엔 다음 넘어갈때마다 pos로 데이터 찍으면됨
            back키 눌렀을때, 위에 이전버튼을 눌렀을때 스택때문에 빡칠일은 없음

            질문갯수가 유동적인 경우 ->
            back키 눌렀을때, 아래 코드처럼 처리하면 되긴 할 것같은데 아직 더 테스트 해봐야됨
            api response가 한번에 다 내려오는 경우엔 지금 이렇게 던져주되, 현재 pos 구해서 그걸 데이터 찍으면 되겠지?
             */


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