package com.carpick.carpickapp.screen.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ActivityMainBinding
import com.carpick.carpickapp.screen.fragment.AgeFragment
import com.carpick.carpickapp.screen.fragment.CarPickBudgetQnaFragment
import com.carpick.carpickapp.screen.fragment.CarPickDetailQnaFragment
import com.carpick.carpickapp.screen.fragment.CarPickRankingFragment
import com.carpick.carpickapp.screen.fragment.CarPickStartFragment
import com.carpick.carpickapp.screen.fragment.CarPoorFragment
import com.carpick.carpickapp.screen.fragment.GenderFragment
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

                R.id.car_ranking_fragment -> {
                    changeFragment(CarPickRankingFragment())
                }
                R.id.car_poor_fragment -> {
                    changeFragment(CarPoorFragment())
                }
            }
            false
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
            when (currentFragment) {
                is CarPickStartFragment, is GenderFragment, is AgeFragment,
                is CarPickBudgetQnaFragment, is CarPickDetailQnaFragment -> {
                    binding.navBar.menu.findItem(R.id.car_recommend_fragment).isChecked = true
                }
                is CarPickRankingFragment -> binding.navBar.menu.findItem(R.id.car_ranking_fragment).isChecked = true
                is CarPoorFragment -> binding.navBar.menu.findItem(R.id.car_poor_fragment).isChecked = true
                // 다른 프래그먼트들에 대한 처리도 추가 가능
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}