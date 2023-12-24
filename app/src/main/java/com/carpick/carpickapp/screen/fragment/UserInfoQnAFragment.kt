package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentUserInfoQnaBinding
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.ui.adapter.AnswerAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel
import kotlinx.coroutines.launch

class UserInfoQnAFragment : BaseFragment<FragmentUserInfoQnaBinding>(){
    private var answerAdapter : AnswerAdapter? = null
    private val answerViewModel : CarpickAnswerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViewModel()

        initListener()
    }
    private fun initView() {
        val genderTextviewGroup = arrayListOf(binding.tvMan, binding.tvGirl)

        binding.tvAgeTitle.text = answerViewModel.apiResponse[0].questionName
        binding.titleLayout.clWish.isVisible = false

        if(answerViewModel.lastPage < 1) {
            answerViewModel.saveLastPage(0)
        }

        answerAdapter = AnswerAdapter()
        answerAdapter?.setClickListener(object : ClickListener {
            override fun click(item: Choice) {
                answerViewModel.saveAgeInfo(item)
                answerViewModel.saveUserInfo(item)

                if(answerViewModel.getGenderInfo() != "") {
                    changeFragment()
                }
            }

        })
        binding.rvAge.adapter = answerAdapter

        answerViewModel.getGenderInfo()?.let {
            if(it == "남자"){
                setClickStatus(genderTextviewGroup,binding.tvMan)
            }else {
                setClickStatus(genderTextviewGroup,binding.tvGirl)
            }
        }

        answerViewModel.getAgeInfo()?.let {
            answerAdapter?.setSelectedItem(it)
        }

        answerAdapter?.submitList(answerViewModel.apiResponse[0].choices)
    }

    private fun initListener() {
        binding.run {
            val genderTextviewGroup = arrayListOf(tvMan, tvGirl)

            // 연봉부터 누르고 성별 누른 경우 이동하기 위해 체크
            tvMan.setOnSingleClickListener {
                setClickStatus(genderTextviewGroup,tvMan)
//                answerViewModel.saveUserInfo(Choice("choie code",""))
                answerViewModel.saveGenderInfo("남자")

                if(!answerViewModel.getAgeInfo()?.choiceCode.isNullOrEmpty()) {
                    changeFragment()
                }

            }
            tvGirl.setOnSingleClickListener {
                setClickStatus(genderTextviewGroup,tvGirl)
//                answerViewModel.saveUserInfo(Choice("choie code",""))
                answerViewModel.saveGenderInfo("여자")

                if(!answerViewModel.getAgeInfo()?.choiceCode.isNullOrEmpty()) {
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
        val newFragment = CarPickBudgetQnaFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
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

    private fun initViewModel() {
        lifecycleScope.launch {
            answerViewModel.getQnaList().collect {
                answerViewModel.setApiResponse(it)
                initView()
            }
        }
    }
}
