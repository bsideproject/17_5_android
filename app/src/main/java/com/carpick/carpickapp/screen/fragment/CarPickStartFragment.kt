package com.carpick.carpickapp.screen.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickStartBinding
import com.carpick.carpickapp.screen.WishListActivity
import com.carpick.carpickapp.util.setOnSingleClickListener
import com.carpick.carpickapp.viewModel.CarPickAnswerViewModel


class CarPickStartFragment : BaseFragment<FragmentCarpickStartBinding>() {
    private val answerViewModel : CarPickAnswerViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answerViewModel.saveLastPage(-1)

        answerViewModel.saveGenderResult(null)
        answerViewModel.saveAgeResult(null)
        answerViewModel.saveBudgetResult(null)
        answerViewModel.saveAnswerResult(null)

        binding?.run {
            Glide.with(this@CarPickStartFragment)
                .asGif()
                .load(R.drawable.car_motion)
                .into(videoView)

            tvStartTest.setOnSingleClickListener {
                val newFragment = GenderFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                val fragmentManager = requireActivity().supportFragmentManager
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                activity?.finish()
            }

            titleLayout.clWish.setOnSingleClickListener {
                val intent = Intent(root.context, WishListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickStartBinding {
        return FragmentCarpickStartBinding.inflate(layoutInflater)
    }
}