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
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel


class CarPickStartFragment : BaseFragment<FragmentCarpickStartBinding>() {
    private val answerViewModel : CarpickAnswerViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answerViewModel.saveLastPage(-1)

        binding.run {
            Glide.with(this@CarPickStartFragment)
                .asGif()
                .load(R.drawable.car_motion)
                .into(binding.videoView)

            tvStartTest.setOnSingleClickListener {
                val newFragment = UserInfoQnAFragment()
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
                val intent = Intent(binding.root.context, WishListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickStartBinding {
        return FragmentCarpickStartBinding.inflate(layoutInflater)
    }
}