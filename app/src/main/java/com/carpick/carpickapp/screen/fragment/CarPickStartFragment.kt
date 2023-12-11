package com.carpick.carpickapp.screen.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.FragmentCarpickStartBinding
import com.carpick.carpickapp.screen.activity.MainActivity
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

            (activity as MainActivity).setSelectedTabListener(object : MainActivity.BottomNavigationListener{
                override fun showTextView() {
                    Toast.makeText(binding.root.context, "1", Toast.LENGTH_SHORT).show()
                }

            })
        }


    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickStartBinding {
        return FragmentCarpickStartBinding.inflate(layoutInflater)
    }
}