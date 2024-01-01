package com.carpick.carpickapp.screen.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carpick.carpickapp.databinding.FragmentWorkingBinding
import com.carpick.carpickapp.screen.WishListActivity
import com.carpick.carpickapp.screen.activity.MainActivity
import com.carpick.carpickapp.util.setOnSingleClickListener

class CarPoorFragment : BaseFragment<FragmentWorkingBinding>(){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleLayout.clWish.setOnSingleClickListener {
            val intent = Intent(binding.root.context, WishListActivity::class.java)
            startActivity(intent)
        }

    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWorkingBinding {
        return FragmentWorkingBinding.inflate(layoutInflater)
    }
}