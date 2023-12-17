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
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentUserInfoQnaBinding
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.model.QnAListResponseModel
import com.carpick.carpickapp.model.QnAListResponseModelItem
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.screen.ComposeTestActivity
import com.carpick.carpickapp.screen.TestActivity
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel

class UserInfoQnAFragment : BaseFragment<FragmentUserInfoQnaBinding>(){
    private var sex = ""
    private var age = ""

    private var answerAdapter : AnswerAdapter? = null
    private val answerViewModel : CarpickAnswerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }
    private fun initView() {
        testModel()

        binding.tvMainTitle.text = answerViewModel.apiResponse[0].questionName

        if(answerViewModel.lastPage <= 1) {
            answerViewModel.saveLastPage(1)
        }

        answerAdapter = AnswerAdapter()
        answerAdapter?.setClickListener(object : ClickListener {
            override fun click(item: Choice) {
                age = item.content
                
                if(sex != "" && age != "") {
                    changeFragment()
                }
            }

        })
        binding.rvAge.adapter = answerAdapter

        answerAdapter?.submitList(answerViewModel.apiResponse[0].choices)
    }

    override fun onResume() {
        super.onResume()

        age = ""
        sex = ""
    }
    private fun initListener() {
        binding.run {
            val sexTextviewGroup = arrayListOf(tvMan, tvGirl)

            titleLayout.icWish.setOnSingleClickListener {
                startComposeActivityForResult()
            }

            // 연봉부터 누르고 성별 누른 경우 이동하기 위해 체크
            tvMan.setOnSingleClickListener {
                setClickStatus(sexTextviewGroup,tvMan)
                sex = "남자"

                if(age != "") {
                    changeFragment()
                }

            }
            tvGirl.setOnSingleClickListener {
                setClickStatus(sexTextviewGroup,tvGirl)
                sex = "여자"

                if(age != "") {
                    changeFragment()
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                val newFragment = CarPickStartFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    private fun changeFragment() {
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

    private fun setClickStatus(textviewGroup: ArrayList<AppCompatTextView>, clickTexView : AppCompatTextView) {
        for(view in textviewGroup) {
            view.setBackgroundColor(0)
            view.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_36364d))
        }

        clickTexView.setBackgroundResource(R.drawable.bg_round_3872ff)
        clickTexView.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentUserInfoQnaBinding {
        return FragmentUserInfoQnaBinding.inflate(layoutInflater)
    }

    private fun testModel() {
        val qnaListResponseModel = ArrayList<QnAListResponseModelItem>()
        qnaListResponseModel.clear()

        qnaListResponseModel.add(
            QnAListResponseModelItem(
                questionName = "성별과 연령대를 선택해주세요!",
                choices = listOf(
                    Choice(choiceCode = "20S_MEN", content = "20대 남성"),
                    Choice(choiceCode = "20S_WOMEN", content = "20대 여성"),
                    Choice(choiceCode = "20S_MEN", content = "30대 남성"),
                    Choice(choiceCode = "30S_MEN", content = "30대 여성"),
                    Choice(choiceCode = "30S_WOMEN", content = "40대 남성"),
                    Choice(choiceCode = "40S_MEN", content =  "40대 여성")
                )
            )
        )
        qnaListResponseModel.add(
            QnAListResponseModelItem(
                questionName = "가능한 예산은 얼마인가요?",
                choices = listOf(
                    Choice(choiceCode = "PRICE_10000_BELOW", content = "1억원 이하"),
                    Choice(choiceCode = "PRICE_15000_BELOW", content = "1.5억원 이하"),
                    Choice(choiceCode = "PRICE_20000_BELOW", content = "2억원 이하"),
                    Choice(choiceCode = "PRICE_20000_OVER", content =  "2억원 초과"),
                    Choice(choiceCode = "PRICE_2500_BELOW", content =  "2500먄원 이하"),
                    Choice(choiceCode = "PRICE_3500_BELOW", content =  "3500만원 이하")
                )
            )
        )

        qnaListResponseModel.add(
            QnAListResponseModelItem(
                questionName = "차량을 주로 어떤 용도로 많이 사용하실 건가요?",
                choices = listOf(
                    Choice(choiceCode = "ANYTHING_USAGE", content = "시내 장거리 모두 좋아요!"),
                    Choice(choiceCode = "DRIVING_AROUND_CITY", content = "시내 주행 (단거리 출퇴근, 아이 등하교 등)"),
                    Choice(choiceCode = "DRIVING_LONG_DISTANCE", content = "장거리 운행 (장거리 출퇴근, 장거리 여행)")
                )
            )
        )

        qnaListResponseModel.add(
            QnAListResponseModelItem(
                questionName = "차량에 주로 누구를 태우실 건가요?",
                choices = listOf(
                    Choice(choiceCode = "DRIVING_WITH_CHILD", content = "아이"),
                    Choice(choiceCode = "DRIVING_WITH_COWORKER", content = "직장동료/상사"),
                    Choice(choiceCode = "DRIVING_WITH_FRIENDS", content = "친구들")
                )
            )
        )
//        val responseModel1 = ArrayList<TestModel>()
//        responseModel1.add(TestModel(id= 1, testData = "18~24세"))
//        responseModel1.add(TestModel(id= 2, testData = "25~29세"))
//        responseModel1.add(TestModel(id= 3, testData = "30~34세"))
//        responseModel1.add(TestModel(id= 4, testData = "35~39세"))
//        responseModel1.add(TestModel(id= 5, testData = "40~44세"))
//        responseModel1.add(TestModel(id= 6, testData = "45~49세"))
//        responseModel1.add(TestModel(id= 7, testData = "50~54세"))
//        responseModel1.add(TestModel(id= 8, testData = "55~59세"))
//        responseModel1.add(TestModel(id= 9, testData = "60~64세"))
//        responseModel1.add(TestModel(id= 10, testData = "65~69세"))
//        responseModel1.add(TestModel(id= 11, testData = "70세 이상"))
//
//        val responseModel2 = ArrayList<TestModel>()
//        responseModel2.add(TestModel(id= 12, testData = "2500만원 이하"))
//        responseModel2.add(TestModel(id= 13, testData = "3000만원 이하"))
//        responseModel2.add(TestModel(id= 14, testData = "4000만원 이하"))
//        responseModel2.add(TestModel(id= 15, testData = "4500만원 이하"))
//        responseModel2.add(TestModel(id= 16, testData = "5000만원 이하"))
//        responseModel2.add(TestModel(id= 17, testData = "5500만원 이하"))
//        responseModel2.add(TestModel(id= 18, testData = "6000만원 이하"))
//        responseModel2.add(TestModel(id= 19, testData = "8000만원 이하"))
//        responseModel2.add(TestModel(id= 20, testData = "1억원 이하"))
//        responseModel2.add(TestModel(id= 21, testData = "1.5억원 이하"))
//        responseModel2.add(TestModel(id= 22, testData = "2억원 이하"))
//        responseModel2.add(TestModel(id= 23, testData = "2억원 초과"))
//
//        val responseModel3 = ArrayList<TestModel>()
//        responseModel3.add(TestModel(id=11, testData = "2페이지 밟으면 나가는 빠른 가속!"))
//        responseModel3.add(TestModel(id=22, testData = "2페이지 팝콘 터지는 배기음!"))
//
//        val responseModel4 = ArrayList<TestModel>()
//        responseModel4.add(TestModel(id=33, testData = "3페이지 시내 주행"))
//        responseModel4.add(TestModel(id=44, testData = "3페이지 장거리 운행"))
//
//        val responseModel5 = ArrayList<TestModel>()
//        responseModel5.add(TestModel(id=55, testData = "4페이지 첫번째"))
//        responseModel5.add(TestModel(id=66, testData = "4페이지 두번째"))
//
//        testApiResponse.clear()
//        testApiResponse.add(responseModel1)
//        testApiResponse.add(responseModel2)
//        testApiResponse.add(responseModel3)
//        testApiResponse.add(responseModel4)
//        testApiResponse.add(responseModel5)

        answerViewModel.setApiResponse(qnaListResponseModel)
    }
}