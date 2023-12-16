package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickStartBinding
import com.carpick.carpickapp.util.setOnSingleClickListener


class CarPickStartFragment : BaseFragment<FragmentCarpickStartBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickStartBinding {
        return FragmentCarpickStartBinding.inflate(layoutInflater)
    }
}