package com.carpick.carpickapp.screen.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.carpick.carpickapp.databinding.FragmentWorkingBinding

class WorkingFragment : BaseFragment<FragmentWorkingBinding>(){
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWorkingBinding {
        return FragmentWorkingBinding.inflate(layoutInflater)
    }
}