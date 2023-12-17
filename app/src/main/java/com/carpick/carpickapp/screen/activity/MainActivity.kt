package com.carpick.carpickapp.screen.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ActivityMainBinding
import com.carpick.carpickapp.screen.fragment.CarPickBudgetQnaFragment
import com.carpick.carpickapp.screen.fragment.CarPickDetailQnaFragment
import com.carpick.carpickapp.screen.fragment.CarPickStartFragment
import com.carpick.carpickapp.screen.fragment.UserInfoQnAFragment
import com.carpick.carpickapp.screen.fragment.WorkingFragment
import com.carpick.carpickapp.viewModel.CarpickAnswerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), ViewModelStoreOwner {
    private val answerViewModel : CarpickAnswerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeFragment(CarPickStartFragment())

        binding.navBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.car_recommend_fragment -> {
                    if(answerViewModel.lastPage == -1) {
                        changeFragment(CarPickStartFragment())
                    }else if(answerViewModel.lastPage == 0){
                        changeFragment(UserInfoQnAFragment())
                    } else if(answerViewModel.lastPage == 1){
                        changeFragment(CarPickBudgetQnaFragment())
                    } else {
                        changeFragment(CarPickDetailQnaFragment.getInstance(answerViewModel.lastPage))
                    }
                }

                R.id.car_ranking_fragment -> {
                    changeFragment(WorkingFragment())
                }
                R.id.car_poor_fragment -> {
                    changeFragment(WorkingFragment())
                }
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host, fragment)
            .commit()
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}