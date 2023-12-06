package com.carpick.carpickapp.screen.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.carpick.carpickapp.databinding.FragmentCarpickQnaBinding
import com.carpick.carpickapp.databinding.FragmentCarpickStartBinding

class CarpickQnAFragment : BaseFragment<FragmentCarpickQnaBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarpickQnaBinding {
        return FragmentCarpickQnaBinding.inflate(layoutInflater)
    }
}