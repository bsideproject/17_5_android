package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentGenderBinding
import com.carpick.carpickapp.model.Choice
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel
import kotlinx.coroutines.launch


class GenderFragment : BaseFragment<FragmentGenderBinding>(){
    private var nowPage = 0
    private var totalPage = 12
    private var selectAnswer = ""

    private val answerViewModel : CarpickAnswerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViewModel()

        initListener()
    }
    private fun initView() {
        binding.run {
            tvQnaTitle.text = answerViewModel.apiResponse[nowPage].questionName
            titleLayout.clWish.isVisible = false

            if (answerViewModel.lastPage < 1) {
                answerViewModel.saveLastPage(nowPage)
            }

            roundProgressBar.progress = 1 * 100 / totalPage
        }

    }
    private fun initListener() {
        binding.run {
            val clGroup = arrayListOf(clMen, clWomen)
            val iconGroup = arrayListOf(ivMen, ivWomen)
            val textviewGroup = arrayListOf(tvMen, tvWomen)

            clMen.setOnSingleClickListener {
                selectAnswer = "man" // 변경예정 api 받은 content로
//                changeFragment()
                answerViewModel.saveBudgetResult(Choice("choice_code", "남성")) // 변경예정
                ivMen.setImageResource(R.drawable.select_icon_men)
                ivWomen.setImageResource(R.drawable.icon_women)
                setClickStatus(clGroup, textviewGroup, clMen, tvMen)
            }

            clWomen.setOnSingleClickListener {
                selectAnswer = "girl"

                answerViewModel.saveBudgetResult(Choice("choice_code", "남성")) // 변경예정


                ivMen.setImageResource(R.drawable.icon_men)
                ivWomen.setImageResource(R.drawable.select_icon_women)

                setClickStatus(clGroup, textviewGroup, clWomen, tvWomen)
            }

            btnNext.setOnSingleClickListener {
                if(selectAnswer != "") {
                    val newFragment = AgeFragment()
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

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                val newFragment = CarPickStartFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
    private fun setClickStatus(
        clGroup: ArrayList<ConstraintLayout>,
        textviewGroup: ArrayList<AppCompatTextView>,
        clickLayout: ConstraintLayout,
        clickTextView: AppCompatTextView
    ) {
        for(view in clGroup) {
            view.setBackgroundColor(0)
        }

        for(view in textviewGroup) {
            view.setTextColor(ContextCompat.getColor(binding.root.context, R.color.color_36364d))
        }

        clickLayout.setBackgroundResource(R.drawable.bg_round_3872ff_8dp)
        clickTextView.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
    }

    private fun changeFragment() {
        val newFragment = AgeFragment()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun initViewModel() {
        lifecycleScope.launch {
            answerViewModel.getQnaList().collect {
                answerViewModel.setApiResponse(it)

                totalPage = it.size
                initView()
            }
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGenderBinding {
        return FragmentGenderBinding.inflate(layoutInflater)
    }
}