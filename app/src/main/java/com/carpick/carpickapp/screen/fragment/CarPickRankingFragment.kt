package com.carpick.carpickapp.screen.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentWorkingBinding
import com.carpick.carpickapp.screen.WishListActivity
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarPickAnswerViewModel

class CarPickRankingFragment : BaseFragment<FragmentWorkingBinding>(){
    private val answerViewModel : CarPickAnswerViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.titleLayout?.clWish?.setOnSingleClickListener {
            val intent = Intent(binding?.root?.context, WishListActivity::class.java)
            startActivity(intent)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if(answerViewModel.lastPage == -1) {
                changeFragment(CarPickStartFragment())
            } else if(answerViewModel.lastPage == 0){
                changeFragment(GenderFragment())
            } else if(answerViewModel.lastPage == 1) {
                changeFragment(AgeFragment())
            }else if(answerViewModel.lastPage == 2){
                changeFragment(CarPickBudgetQnaFragment())
            } else {
                changeFragment(CarPickDetailQnaFragment.getInstance(answerViewModel.lastPage))
            }
        }
    }
    private fun changeFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWorkingBinding {
        return FragmentWorkingBinding.inflate(layoutInflater)
    }
}